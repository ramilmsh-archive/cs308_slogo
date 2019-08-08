package interpreter.core.include;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeUserInstructionTest {
    @Test
    void resolve() {
        PubSub pubsub = new PubSub();
        Interpreter interpreter = new Interpreter(pubsub);
        // pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("define f [:i] to foo [:i] [+ :i :i + 4 3] foo 1 foo 9"));
        pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("define f [:i]" +
                "to f [:i] [" +
                "  ifelse lessp :i 3 [:i][+ f - :i 1 f - :i 2]" +
                "]" +
                "f 10"));
    }
}