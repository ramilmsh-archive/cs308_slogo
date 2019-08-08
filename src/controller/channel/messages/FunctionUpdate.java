package controller.channel.messages;

//update the functions

public class FunctionUpdate extends Message {
    public String key, value;

    public FunctionUpdate(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
