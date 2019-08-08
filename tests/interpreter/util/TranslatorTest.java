package interpreter.util;

import interpreter.exceptions.ValueException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {
    private Console console;
    private Translator translator;

    @BeforeEach
    void init() {
        console = new Console(System.in, System.out, System.err);
        try {
            translator =  new Translator(null);
        } catch (ValueException ignored) {}
    }

    @Test
    void setLanguage() {
    }

    @Test
    void translate() {
        assertEquals("Sine", translator.translate("sin"));
        assertEquals(null, translator.translate("abra"));
        assertEquals("SetBackground", translator.translate("setbackground"));
        assertEquals("SetBackground", translator.translate("setbg"));
        assertEquals("IsPenDown", translator.translate("pendown?"));
    }

}