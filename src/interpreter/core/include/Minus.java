package interpreter.core.include;

import interpreter.core.elements.Element;
import interpreter.core.elements.Value;
import interpreter.core.include.prototypes.PreDefined;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

import java.util.ArrayList;

/**
 * Implements Minus function
 *
 * @author ramilmsh
 */
public class Minus extends PreDefined<Double> {
    private ArrayList<Element> arguments;

    /**
     * Creates a new instance of Minus function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Minus(String name, Context context) throws InvalidSyntaxError {
        super(Value.Type.NUMBER, name, context, 1);
    }

    @Override
    protected Value evaluate(ArrayList<Value> inputs) {
        ArrayList<Value<Double>> result = new ArrayList<>();
        for (Value input : inputs)
            result.add(new Value<>(-(Double) input.getValue()));

        return new Value<>(result);
    }
}
