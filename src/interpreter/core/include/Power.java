package interpreter.core.include;

import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

/**
 * Implements Power function
 *
 * @author ramilmsh
 */
public class Power extends PreDefined<Double> {

    /**
     * Create a new instance of Power function
     *
     * @param name:    function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Power(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.NUMBER, name, context, 2, false);
    }

    @Override
    protected Double apply(Double in1, Double in2) {
        return Math.pow(in1, in2);
    }
}
