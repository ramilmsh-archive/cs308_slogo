package interpreter.core.include;

import interpreter.core.elements.Element;
import interpreter.core.elements.Function;
import interpreter.core.elements.Value;
import interpreter.core.elements.Variable;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements Define function
 *
 * @author ramilmsh
 */
public class Define extends Function {
    private String commandName;
    private ArrayList<Variable> arguments;

    /**
     * Creates a new instance of define function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Define(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
        commandName = context.tokenizer.next();
        arguments = CustomFunctionDefinition.process(context);
    }

    @Override
    public Value resolve(Scope scope) {
        try {
            CustomFunctionDefinition definition = scope.getDefinition(commandName);
            if (definition == null)
                scope.setFunction(new CustomFunctionDefinition(commandName, context, arguments, null));
            else
                return new Value<>(0.);
            return new Value<>(1.);
        } catch (InvalidSyntaxError e) {
            return new Value<>(0.);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getName()).append(" [ ");
        for (Element argument : arguments)
            sb.append(argument).append(" ");
        sb.append("]");

        return sb.toString();
    }
}
