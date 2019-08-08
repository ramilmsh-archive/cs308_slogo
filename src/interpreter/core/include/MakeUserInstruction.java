package interpreter.core.include;

import interpreter.core.elements.Element;
import interpreter.core.elements.Value;
import interpreter.core.elements.Variable;
import interpreter.core.include.prototypes.WithCode;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements MakeUserInstruction function
 *
 * @author ramilmsh
 */
public class MakeUserInstruction extends WithCode {
    private String commandName;
    private ArrayList<Variable> arguments;

    /**
     * Create a new instance of MakeUserInstruction
     *
     * @param name:function name
     * @param context:      context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public MakeUserInstruction(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    protected void init() throws InvalidSyntaxError {
        commandName = context.tokenizer.next();
        arguments = CustomFunctionDefinition.process(context);
    }

    @Override
    public Value resolve(Scope scope) {
        try {
            CustomFunctionDefinition definition = scope.getDefinition(commandName);
            if (definition == null)
                scope.setFunction(new CustomFunctionDefinition(commandName, context, arguments, code));
            else if (definition.code == null) {
                scope.implementFunction(commandName, code);
            } else {
                return new Value<>(0.);
            }
            return new Value<>(1.);
        } catch (InvalidSyntaxError e) {
            return new Value<>(0.);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getName()).append(" ").append(commandName).append(" [ ");
        for (Element argument : arguments)
            sb.append(argument).append(" ");
        sb.append("] ").append(super.toString());

        return sb.toString();
    }
}
