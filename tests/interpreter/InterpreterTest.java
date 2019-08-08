package interpreter;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import controller.channel.messages.Message;
import controller.channel.messages.VariableUpdate;
import org.junit.jupiter.api.Test;

class InterpreterTest {
    @Test
    void process() {
        Interpreter interpreter = new Interpreter(new PubSub());
    }

    @Test
    void reset() {
    }

    @Test
    void changeLanguage() {
    }

    @Test
    void linearTime() {
        PubSub pubsub = new PubSub();
        Interpreter interpreter = new Interpreter(pubsub);
        pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("cos 90"));
        // pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("and [true false true false] [true true false false]"));
        // pubsub.publish(PubSub.Channel.INTERPRET, new Interpret("define foo [:a] to foo [:a][ifelse equalp :a 1 [1][+ 1 foo - :a 1]] foo 6"));
    }

}