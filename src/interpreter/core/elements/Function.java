package interpreter.core.elements;

import interpreter.core.Expression;
import interpreter.core.interfaces.Named;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements Function
 */
public abstract class Function extends Element implements Named {
    private String name;

    /**
     * Create function with a name and context
     *
     * @param name: function name
     * @param context: context
     */
    public Function(String name, Context context) {
        super(context);
        this.name = name;
    }

    @Override
    abstract public Value resolve(Scope scope);

    /**
     * Create new Function from input
     *
     * @param context: context
     * @return new Function
     */
    public static Function process(Context context) {
        String name = context.tokenizer.next();
        if (name.equals("(")) {
            name = context.tokenizer.next();
            context.tokenizer.add("(");
        }

        name = context.translator.translate(name);
        try {
            return context.scope.getFunction(name, context);
        } catch (InvalidSyntaxError e) {
            context.console.error(e.toString());
            return null;
        }
    }

    /**
     * Get list of arguments, allowing for multiple inputs
     *
     * @param numVar: default argument length
     * @param context: context
     * @return argument list
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    protected static ArrayList<Element> readArguments(int numVar, Context context) throws InvalidSyntaxError {
        return readArguments(numVar, context, true);
    }

    /**
     * Get list of arguments
     *
     * @param numVar: default argument length
     * @param context: context
     * @param multiple: allow arbitrary argument length
     * @return a list of arguments
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    protected static ArrayList<Element> readArguments(int numVar, Context context, boolean multiple) throws InvalidSyntaxError {
        int counter = 0;

        if (numVar == 0)
            return new ArrayList<>();

        if (!multiple && context.tokenizer.peek().equals("("))
            throw new InvalidSyntaxError("Unexpected token (");

        if (multiple && context.tokenizer.peek().equals("(")) {
            context.tokenizer.next();
            numVar = -1;
        }
        System.out.println(context.tokenizer);

        ArrayList<Element> arguments = new ArrayList<>();
        while (context.tokenizer.hasNext()) {
            if (numVar != -1 && counter >= numVar)
                break;

            if (numVar == -1 && context.tokenizer.peek().equals(")"))
                break;

            try {
                arguments.add(Expression.parse(context));
            } catch (InvalidSyntaxError e) {
                throw new InvalidSyntaxError("");
            }

            counter++;
        }

        if (numVar == -1) {
            if (!context.tokenizer.peek().equals(")"))
                return null;

            context.tokenizer.next();
        }
        return arguments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Element getSelf() {
        return this;
    }
}
