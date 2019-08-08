package interpreter.core.elements;

import interpreter.core.Expression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {
    @Test
    void match() {
        assertEquals(Expression.Type.VARIABLE, Expression.Type.match(":variable_123"));
        assertEquals(Expression.Type.VARIABLE, Expression.Type.match(":_variable_123"));
        assertEquals(null, Expression.Type.match(":123variable_123"));

        assertEquals(Expression.Type.FUNCTION, Expression.Type.match("function"));

        assertEquals(Expression.Type.NUMBER, Expression.Type.match("-1.23"));
        assertEquals(null, Expression.Type.match("-1.23.0"));
    }
}

