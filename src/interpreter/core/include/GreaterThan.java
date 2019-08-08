package interpreter.core.include;

import interpreter.core.Expression;
import interpreter.core.elements.Element;
import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 *
 */
public class GreaterThan extends PreDefined<Double> {

    public GreaterThan(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.NUMBER, name, context, 2, false);
    }

    @Override
    protected Value evaluate(ArrayList<Value> inputs) {
        return new Value<>((Double) inputs.get(0).getValue() > (Double) inputs.get(1).getValue() ? 1. : 0.);
    }
}
