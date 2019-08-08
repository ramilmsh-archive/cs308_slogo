package interpreter.core.include;

import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

/**
 * Implements ArcTangent function
 *
 * @author ramilmsh
 */
public class ArcTangent extends PreDefined<Double> {
    /**
     * Creates a new instance of ArcTangent function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public ArcTangent(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.NUMBER, name, context, 1, false);
    }

    @Override
    protected Double apply(Double in) {
        return Math.atan(in * Math.PI / 180);
    }
}
