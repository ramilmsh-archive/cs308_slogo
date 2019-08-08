package controller.channel.messages;

/**
 * Variable update
 */
public class VariableUpdate extends Message {

    public String key, value;

    public VariableUpdate(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " " + value;
    }
}
