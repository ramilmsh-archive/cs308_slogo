package interpreter.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    void all() {
        Tokenizer tokenizer;
        String[] results;

        tokenizer = new Tokenizer("if not :a[fd 10    \n](sum 30#thisisforfun\n   \r\r40\n\r\t45)");
        results = new String[]{"if", "not", ":a", "[", "fd", "10", "]", "(", "sum", "30", "40", "45", ")"};

        // Simple test
        assertEquals(true, tokenizer.hasNext());
        for (String result : results)
            assertEquals(result, tokenizer.next());
        assertEquals(false, tokenizer.hasNext());

        // Reset
        tokenizer.reset();
        assertEquals(true, tokenizer.hasNext());
        for (String result : results) {
            System.out.printf("%s ", tokenizer.peek());
            assertEquals(result, tokenizer.next());
        }
        assertEquals(false, tokenizer.hasNext());

        tokenizer = new Tokenizer("");
        assertEquals(false, tokenizer.hasNext());
        assertThrows(NullPointerException.class, tokenizer::peek);
        assertThrows(NullPointerException.class, tokenizer::next);
    }

    @Test
    void add() {
        Tokenizer tokenizer = new Tokenizer("a b c");
        tokenizer.add("b");
        System.out.println(tokenizer);
    }
}