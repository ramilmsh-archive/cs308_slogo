package interpreter.util;

import controller.channel.PubSub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScopeTest {
    @Test
    void exists() {
        // DoubleInput declaration
        PubSub pubsub = new PubSub();
        Scope scope = new Scope(pubsub);
        Scope scop1e = new Scope(pubsub);
    }

    @Test
    void defaults() {

    }

}