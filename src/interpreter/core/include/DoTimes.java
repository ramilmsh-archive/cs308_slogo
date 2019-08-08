package interpreter.core.include;

import interpreter.core.Expression;
import interpreter.core.elements.Element;
import interpreter.core.elements.Value;
import interpreter.core.elements.Variable;
import interpreter.core.include.prototypes.WithCode;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

/**
 * Implements DoTimes function
 *
 * @author ramilmsh
 */
public class DoTimes extends WithCode {
    private Variable variable;
    private Element limit;

    /**
     * Create a new instance of dotimes function
     *
     * @param name:    function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public DoTimes(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
    }

    @Override
    protected void init() throws InvalidSyntaxError {
        String tmp = context.tokenizer.next();
        if (!tmp.equals("["))
            throw new InvalidSyntaxError("[ expected, " + tmp + " found");

        variable = Variable.process(context);
        limit = Expression.parse(context);

        tmp = context.tokenizer.next();
        if (!tmp.equals("]"))
            throw new InvalidSyntaxError("] expected, " + tmp + " found");
    }

    @Override
    public Value resolve(Scope scope) {
        int limit = ((Double) this.limit.resolve(scope).getValue()).intValue();
        Value result = new Value<>(0.);
        for (int i = 1; i <= limit; ++i) {
            scope.setValue(variable, new Value<>((double) i));
            result = code.resolve(scope);
        }
        return WithCode.getLast(result);
    }

    @Override
    public String toString() {
        return getName() + " [ " + variable + " " + limit + " ] " + super.toString();
    }
}
