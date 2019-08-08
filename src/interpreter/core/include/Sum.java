package interpreter.core.include;

import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

/**
 * Implements Sum function
 *
 * @author ramilmsh
 */
public class Sum extends PreDefined<Double> {

    /**
     * Creates a new instance of Sum function
     *
     * @param name:    function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Sum(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.NUMBER, name, context);
    }

    @Override
    protected Double apply(Double in1, Double in2) {
        return in1 + in2;
    }
}
