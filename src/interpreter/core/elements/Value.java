package interpreter.core.elements;

import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements values of multiple types
 *
 * @param <K> primary type
 * @author ramilmsh
 */
public class Value<K> extends Element {

    /**
     * Value types
     */
    public enum Type {
        NUMBER(Double.class), WORD(String.class), BOOLEAN(Boolean.class), LIST(ArrayList.class), ANY(null);

        private Class clazz;

        Type(Class clazz) {
            this.clazz = clazz;
        }

        public static Type getType(Class clazz) {
            for (Type type : Type.values())
                if (type.clazz == clazz) return type;
            return null;
        }

        public boolean equals(Type type) {
            return type == this || type == ANY;
        }
    }

    protected K value;
    private Class clazz;
    private Type type;

    /**
     * Create a new instance of Value
     *
     * @param value: value
     */
    public Value(K value) {
        super(null);
        this.value = value;
        clazz = value.getClass();
        this.type = Type.getType(value.getClass());
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public Value<K> resolve(Scope scope) {
        return this;
    }

    /**
     * Get a Value from input
     *
     * @param context: context
     * @return new Value
     */
    public static Value process(Context context) {
        String token = context.tokenizer.next();
        if (token.startsWith("\"")) return new Value<>(token.substring(1));

        try {
            return new Value<>(Double.parseDouble(token));
        } catch (NumberFormatException ignored) {
        }
        try {
            return new Value<>(Boolean.parseBoolean(token));
        } catch (NumberFormatException ignored) {
        }

        return null;
    }

    public K getValue() {
        return this.value;
    }

    public Value setValue(K value) {
        if (type != Type.getType(value.getClass()))
            return this;

        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        if (type == null)
            return value.toString();

        switch (type) {
            case LIST:
                StringBuilder sb = new StringBuilder("[ ");
                ArrayList list = ArrayList.class.cast(value);
                for (Object val : list)
                    sb.append(val.toString()).append(" ");
                sb.append("]");
                return sb.toString();

            case NUMBER:
                return Double.toString(Double.class.cast(value));

            case BOOLEAN:
                return Boolean.toString(Boolean.class.cast(value));
        }

        return value.toString();
    }

    /**
     * Get type of a Value
     *
     * @param value: value
     * @return type
     */
    public static Type typeOf(Value value) {
        return value.type;
    }
}
