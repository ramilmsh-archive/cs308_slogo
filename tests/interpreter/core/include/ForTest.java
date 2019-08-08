package interpreter.core.include;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForTest {
    @Test
    void resolve() {
        PubSub pubsub = new PubSub();
        Interpreter interpreter = new Interpreter(pubsub);
        //pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("for [:i 1.9 5 1.5] [:i]"));
        pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("% 4 5"));
    }
}