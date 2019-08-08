package interpreter.core.include;

import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

/**
 * Implements Or function
 *
 * @author ramilmsh
 */
public class Or extends PreDefined<Double> {
    /**
     * Create a new instance of Or function
     *
     * @param name:    function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Or(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.BOOLEAN, name, context);
    }

    @Override
    protected Double apply(Double in1, Double in2) {
        return in1.equals(1.0) | in2.equals(1.0) ? 1. : 0.;
    }
}
