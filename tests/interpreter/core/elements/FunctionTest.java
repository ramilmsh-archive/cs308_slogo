package interpreter.core.elements;

import interpreter.exceptions.InvalidSyntaxError;
import interpreter.exceptions.ValueException;
import interpreter.util.Context;
import interpreter.util.Scope;
import interpreter.util.Tokenizer;
import interpreter.util.Translator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTest {
    @Test
    void resolve() {
    }

    @Test
    void process() throws InvalidSyntaxError {
        try {
            Context context = new Context(null, null);
            context.update(new Scope(null));
            context.update(new Tokenizer("(sum -3 3 -3)"));
            assertEquals(Function.process(context).getName(), "Sum");
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    @Test
    void getName() {
    }

    @Test
    void getSelf() {
    }

}