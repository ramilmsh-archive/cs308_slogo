package interpreter.core.include;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfTest {
    @Test
    void resolve() {
        PubSub pubsub = new PubSub();
        Interpreter interpreter = new Interpreter(pubsub);
        pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("if 5 [4]"));
    }
}