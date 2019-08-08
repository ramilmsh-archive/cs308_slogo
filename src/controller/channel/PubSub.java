package controller.channel;

import controller.channel.messages.Execute;
import controller.channel.messages.Interpret;
import controller.channel.messages.Message;
import controller.channel.messages.LanguageChange;
import controller.channel.messages.VariableLoad;
import controller.channel.messages.VariableUpdate;
import controller.channel.messages.FunctionUpdate;
import controller.channel.messages.CommandStatus;
import controller.channel.messages.Query;
import interpreter.core.elements.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class that implements publish/subscribe pattern
 *
 * @author ramilmsh
 */
public class PubSub {
    /**
     * Channel list
     */
    public enum Channel {
        VARIABLE_UPDATE(VariableUpdate.class),
        FUNCTION_UPDATE(FunctionUpdate.class),
        VARIABLE_LOAD(VariableLoad.class),
        EXECUTE(Execute.class),
        INTERPRET(Interpret.class),
        LANGUAGE_CHANGE(LanguageChange.class),
        COMMAND_STATUS(CommandStatus.class),
    	QUERY(Query.class);

        Class<? extends Message> clazz;

        Channel(Class<? extends Message> clazz) {
            this.clazz = clazz;
        }
    }

    private HashMap<Channel, ArrayList<Consumer<Message>>> callbacks;
    private HashMap<Channel, Function<Message, Value>> callbacksSync;

    /**
     * Create a new instance of PubSub
     */
    public PubSub() {
        callbacks = new HashMap<>();
        callbacksSync = new HashMap<>();
    }

    /**
     * Subscribe to a synchronous channel
     *
     * @param channel: channel
     * @param callback: callback that processes message
     */
    public void subscribe(Channel channel, Consumer<Message> callback) {
        if (!callbacks.containsKey(channel))
            callbacks.put(channel, new ArrayList<>());

        callbacks.get(channel).add(callback);
    }

    /**
     * Subscribe to a synchronous channel
     * @param channel: channel
     * @param callback: callback that processes message
     */
    public void subscribeSync(Channel channel, Function<Message, Value> callback) {
        callbacksSync.put(channel, callback);
    }

    /**
     * Publish to an asynchronous channel
     *
     * @param channel: channel
     * @param msg: message
     */
    public void publish(Channel channel, Message msg) {
        if (msg.getClass() != channel.clazz || callbacks == null || callbacks.get(channel) == null)
            return;

        for (Consumer<Message> callback : callbacks.get(channel))
            if (callback != null)
                callback.accept(msg);
    }

    /**
     * Publish to a synchronous channel
     *
     * @param channel: channel
     * @param msg: message
     * @return result of processing
     */
    public Value publishSync(Channel channel, Message msg) {
        if (callbacksSync.containsKey(channel))
            return callbacksSync.get(channel).apply(msg);
        return null;
    }
}
