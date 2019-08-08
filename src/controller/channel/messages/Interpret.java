package controller.channel.messages;

/**
 * Interpret message
 *
 * @author ramilmsh
 */
public class Interpret extends Message {
    public String code;

    /**
     * Create a new message
     *
     * @param code: code to be interpreted
     */
    public Interpret(String code) {
        this.code = code;
    }
}
