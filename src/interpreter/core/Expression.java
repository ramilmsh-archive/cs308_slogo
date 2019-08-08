package interpreter.core;

import interpreter.core.elements.*;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;

import java.util.regex.Pattern;

/**
 * Class that parses expressions
 *
 * @author ramilmsh
 */
public class Expression {

    /**
     * Expression types
     */
    public enum Type {
        BOOLEAN(Pattern.compile("True|true|False|false"), Value::process),
        FUNCTION(Pattern.compile("[a-zA-Z_]+(\\?)?|[*+-/%~]"), Function::process),
        VARIABLE(Pattern.compile(":[a-zA-Z_]+[a-zA-Z0-9_]*"), Variable::process),
        NUMBER(Pattern.compile("-?[0-9]+\\.?[0-9]*"), Value::process),
        STRING(Pattern.compile("\"[a-zA-Z0-9_]*"), Value::process),
        LIST_START(Pattern.compile("\\["), List::process),
        COMMAND_START(Pattern.compile("\\("), Function::process);

        private Pattern pattern;
        private java.util.function.Function<Context, Element> processor;

        /**
         * Create a type
         *
         * @param pattern: regular expression for the type
         * @param processor: processor that creates an Element from input
         */
        Type(Pattern pattern, java.util.function.Function<Context, Element> processor) {
            this.pattern = pattern;
            this.processor = processor;
        }

        /**
         * Match expression with type
         *
         * @param exp: expression
         * @return type of the expression
         */
        public static Type match(String exp) {
            for (Type type : Type.values()) {
                if (type.pattern.matcher(exp).matches()) return type;
            }

            return null;
        }
    }

    /**
     * Parse the input
     *
     * @param context: context
     * @return resolvable Element
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public static Element parse(Context context) throws InvalidSyntaxError {
        Type type = Type.match(context.tokenizer.peek());

        if (type == null)
            throw new InvalidSyntaxError("Unexpected token " + context.tokenizer.peek());

        return type.processor.apply(context);
    }
}
