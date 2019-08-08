package controller.channel.messages;

public class LanguageChange extends Message{
    public String language;

    /**
     * Create a new message
     *
     * @param language: type of language of commands to be interpreted
     */
    public LanguageChange(String language) {
        this.language = language;
    }
}
