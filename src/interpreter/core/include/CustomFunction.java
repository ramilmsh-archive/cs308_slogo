package interpreter.core.include;

import interpreter.core.elements.*;
import interpreter.core.include.prototypes.WithCode;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements user defined functions
 *
 * @author ramilmsh
 */
public class CustomFunction extends Function {
    private CustomFunctionDefinition definition;
    private ArrayList<Element> arguments;

    /**
     * Create custom function from function definition
     *
     * @param customFunctionDefinition: definition
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public CustomFunction(CustomFunctionDefinition customFunctionDefinition) throws InvalidSyntaxError {
        super(customFunctionDefinition.name, customFunctionDefinition.context);
        definition = customFunctionDefinition;
        arguments = Function.readArguments(definition.argumentVariables.size(), context, false);
    }

    @Override
    public Value resolve(Scope scope) {
        scope = new Scope(context.pubsub, getName(), scope);
        for (int i = 0; i < definition.argumentVariables.size(); ++i)
            scope.setValue(definition.argumentVariables.get(i), arguments.get(i).resolve(scope));

        return WithCode.getLast(definition.code.resolve(scope));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("( ").append(getName()).append(" ");
        for (Element argument : arguments)
            sb.append(argument).append(" ");
        sb.append(")");

        return sb.toString();
    }
}
