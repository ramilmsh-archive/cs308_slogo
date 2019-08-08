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
 * Implements If function
 *
 * @author ramilmsh
 */
public class If extends WithCode {
    private Element expression;

    /**
     * Creates a new instance of if function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public If(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    protected void init() throws InvalidSyntaxError {
        expression = Expression.parse(context);
    }

    @Override
    public Value resolve(Scope scope) {
        Value result = new Value<>(0.);
        Value condition = expression.resolve(scope);
        if (Value.typeOf(condition) != Value.Type.NUMBER)
            return null;

        if ((expression.resolve(scope).getValue()).equals(1.))
            result = code.resolve(scope);
        return WithCode.getLast(result);
    }

    @Override
    public String toString() {
        return getName() + " " + expression + " " + super.toString();
    }
}
