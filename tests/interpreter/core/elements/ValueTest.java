package interpreter.core.elements;

import interpreter.exceptions.ValueException;
import interpreter.util.Context;
import interpreter.util.Scope;
import interpreter.util.Tokenizer;
import interpreter.util.Translator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueTest {
    @Test
    void getClazz() {
    }

    @Test
    void resolve() {
    }

    @Test
    void process() {
        try {
            Context context = new Context(null, null);
            context.update(new Scope(null));
            context.update(new Tokenizer("[3 [4 true] 7]"));
            System.out.println(List.process(context).resolve(context.scope));
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    @Test
    void getValue() {
    }

}