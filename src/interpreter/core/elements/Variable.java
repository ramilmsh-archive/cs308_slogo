package interpreter.core.elements;

import interpreter.core.interfaces.Named;
import interpreter.util.Context;
import interpreter.util.Scope;

/**
 * Implements variables
 */
public class Variable extends Element implements Named {
    private String name;

    /**
     * Create a new variable instance
     *
     * @param name: variable name
     * @param context: context
     */
    public Variable(String name, Context context) {
        super(context);
        this.name = name;
    }

    @Override
    public Value resolve(Scope scope) {
        return scope.getValue(this);
    }

    @Override
    public String toString() {
        return ":" + name;
    }

    /**
     * Get Variable from input
     *
     * @param context: context
     * @return new Variable
     */
    public static Variable process(Context context) {
        String name = context.tokenizer.next();
        if (!name.startsWith(":"))
            return null;

        return new Variable(name.substring(1), context);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Variable getSelf() {
        return this;
    }
}
