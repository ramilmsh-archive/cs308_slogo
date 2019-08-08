package interpreter.core.include;

import interpreter.core.elements.Element;
import interpreter.core.elements.Function;
import interpreter.core.elements.Value;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

/**
 * Implements Pi function
 *
 * @author ramilmsh
 */
public class Pi extends Function {
    private static final double PI = Math.PI;

    /**
     * Create a new instance of Pi function
     *
     * @param name:    function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Pi(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    public Value resolve(Scope scope) {
        return new Value<>(PI);
    }

    @Override
    public String toString() {
        return Double.toString(PI);
    }
}
