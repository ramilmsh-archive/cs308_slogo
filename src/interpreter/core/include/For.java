package interpreter.core.include;

import interpreter.core.Expression;
import interpreter.core.elements.*;
import interpreter.core.include.prototypes.WithCode;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements For function
 *
 * @author ramilmsh
 */
public class For extends WithCode {
    private Variable variable;
    private Element start;
    private Element stop;
    private Element increment;

    /**
     * Creates a new instance of for function
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public For(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    protected void init() throws InvalidSyntaxError {
        String tmp = context.tokenizer.next();
        if (!tmp.equals("["))
            throw new InvalidSyntaxError("[ expected, " + tmp + " found");

        variable = Variable.process(context);
        start = Expression.parse(context);
        stop = Expression.parse(context);
        increment = Expression.parse(context);

        tmp = context.tokenizer.next();
        if (!tmp.equals("]"))
            throw new InvalidSyntaxError("] expected, " + tmp + " found");
    }

    @Override
    public Value resolve(Scope scope) {
        double start = (Double) this.start.resolve(scope).getValue(),
                stop = (Double) this.stop.resolve(scope).getValue(),
                increment = (Double) this.increment.resolve(scope).getValue();
        Value result = new Value<>(0.);

        for (double i = start; i <= stop; i += increment) {
            scope.setValue(variable, new Value<>(i));
            result = code.resolve(scope);
        }

        return WithCode.getLast(result);
    }

    @Override
    public String toString() {
        return getName() + " [ " + variable + " " + start + " " + stop + " " + increment + " ] " + super.toString();
    }
}
