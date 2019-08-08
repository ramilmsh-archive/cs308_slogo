package interpreter.core.include;

import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

import java.util.ArrayList;

/**
 *
 */
public class Equal extends PreDefined<Double> {

    public Equal(String name, Context context) throws InvalidSyntaxError {
        // TODO change to ANY
        super(Value.Type.NUMBER, name, context, 2, false);
    }

    @Override
    protected Value evaluate(ArrayList<Value> inputs) {
        return new Value<>(inputs.get(0).getValue().equals(inputs.get(1).getValue()) ? 1. : 0.);
    }
}
