package controller.channel;

import controller.channel.messages.Message;
import controller.channel.messages.VariableUpdate;
import interpreter.core.elements.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PubSubTest {
    @Test
    void publish() {
        PubSub pubsub = new PubSub();
        pubsub.subscribe(PubSub.Channel.VARIABLE_UPDATE, (msg)->{
            VariableUpdate message = (VariableUpdate) msg;
            System.out.println(msg);
        });
    }

    @Test
    void publishSync() {
        PubSub pubsub = new PubSub();
        pubsub.subscribeSync(PubSub.Channel.VARIABLE_UPDATE, this::lambda);
        System.out.println(pubsub.publishSync(PubSub.Channel.VARIABLE_UPDATE, new VariableUpdate("sfs", "4")));
    }

    Value lambda(Message msg) {
        return null;
    }
}