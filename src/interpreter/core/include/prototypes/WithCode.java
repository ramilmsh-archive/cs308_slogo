package interpreter.core.include.prototypes;

import interpreter.core.elements.Function;
import interpreter.core.elements.List;
import interpreter.core.elements.Value;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

import java.util.ArrayList;

public abstract class WithCode extends Function {
    protected List code;

    /**
     * Create function with a name and context
     *
     * @param name    : function name
     * @param context : context
     */
    protected WithCode(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
        init();
        code = List.process(context);
    }

    protected abstract void init() throws InvalidSyntaxError;

    public static Value getLast(Value value) {
        if (Value.typeOf(value) == Value.Type.LIST) {
            ArrayList list = (ArrayList) value.getValue();
            return (Value) (list).get(list.size() - 1);
        }
        return value;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}
