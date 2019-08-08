package interpreter.core.include;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import controller.channel.messages.Message;
import interpreter.Interpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeVariableTest {
    @Test
    void resolve() {
        PubSub pubsub = new PubSub();
        Interpreter interpreter = new Interpreter(pubsub);
        pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("(make :a sum 7 5 :b :a)"));
    }
}