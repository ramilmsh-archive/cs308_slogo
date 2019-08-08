package interpreter.core.elements;

import interpreter.util.Context;
import interpreter.util.Scope;

/**
 * Base class for every object within SLogo
 *
 * @author ramilmsh
 */
public abstract class Element {
    protected Context context;

    /**
     * Create an Element in the context
     *
     * @param context: context
     */
    protected Element(Context context) {
        this.context = context;
    }

    /**
     * Resolve an element into a Value
     *
     * @param scope: scope
     * @return new Value that the Element returns
     */
    public abstract Value resolve(Scope scope);

    @Override
    public abstract String toString();
}
