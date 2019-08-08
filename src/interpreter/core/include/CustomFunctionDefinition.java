package interpreter.core.include;

import interpreter.core.elements.List;
import interpreter.core.elements.Variable;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

import java.util.ArrayList;

/**
 * Custom function definition
 *
 * @author ramilmsh
 */
public class CustomFunctionDefinition {
    public String name;
    public Context context;
    public ArrayList<Variable> argumentVariables;
    public List code;

    /**
     * Create a function definition
     *
     * @param name: function name
     * @param context: context
     * @param argumentVariables: list of argument variables
     * @param code: the code to be executed
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public CustomFunctionDefinition(String name, Context context, ArrayList<Variable> argumentVariables, List code) throws InvalidSyntaxError {
        this.name = name;
        this.context = context;
        this.argumentVariables = argumentVariables;
        this.code = code;
    }

    /**
     * Process input into list of variables
     *
     * @param context: context
     * @return list of variables
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public static ArrayList<Variable> process(Context context) throws InvalidSyntaxError {
        String tmp = context.tokenizer.next();
        if (!tmp.equals("["))
            throw new InvalidSyntaxError("[ expected " + tmp + " found");
        ArrayList<Variable> arguments = new ArrayList<>();

        while (context.tokenizer.hasNext() && !context.tokenizer.peek().equals("]"))
            arguments.add(Variable.process(context));
        tmp = context.tokenizer.next();
        if (!tmp.equals("]"))
            throw new InvalidSyntaxError("] expected " + tmp + " found");

        return arguments;
    }
}
