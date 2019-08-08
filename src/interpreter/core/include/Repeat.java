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
 * Implements Repeat function
 *
 * @author ramilmsh
 */
public class Repeat extends WithCode {
    private Element times;

    /**
     * Create a new instance of Repeat function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Repeat(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    protected void init() throws InvalidSyntaxError {
        times = Expression.parse(context);
    }

    @Override
    public Value resolve(Scope scope) {
        int times = ((Double) this.times.resolve(scope).getValue()).intValue();
        Value result = new Value<>(0.);
        for (int i = 0; i < times; ++i)
            result = code.resolve(scope);
        return result;
    }

    @Override
    public String toString() {
        return getName() + " " + times + " " + super.toString();
    }
}
