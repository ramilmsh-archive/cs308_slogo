package interpreter.core.include;

import interpreter.core.Expression;
import interpreter.core.elements.Element;
import interpreter.core.elements.Function;
import interpreter.core.elements.List;
import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.WithCode;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

/**
 * Implements IfElse function
 *
 * @author ramilmsh
 */
public class IfElse extends Function {
    private Element expression;
    private List trueCode;
    private List falseCode;

    /**
     * Creates a new instance of IfElse function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public IfElse(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
        expression = Expression.parse(context);
        trueCode = List.process(context);
        falseCode = List.process(context);
    }

    @Override
    public Value resolve(Scope scope) {
        Value condition = expression.resolve(scope);
        if (Value.typeOf(condition) != Value.Type.NUMBER)
            return null;

        if ((expression.resolve(scope).getValue()).equals(1.))
            return WithCode.getLast(trueCode.resolve(scope));
        else
            return WithCode.getLast(falseCode.resolve(scope));
    }

    @Override
    public String toString() {
        return getName() + " " + expression + " " + trueCode + " " + falseCode;
    }
}
