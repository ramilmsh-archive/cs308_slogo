package controller.channel.messages;

import command.CommandUnit;

/**
 * Execute message
 *
 * @author ramilmsh
 */
public class Execute extends Message {
    private CommandUnit command;

    /**
     * Create execute message
     *
     * @param command: command to be executed
     */
    public Execute(CommandUnit command) {
        this.command = command;
    }

    public CommandUnit getCommand() {
        return command;
    }
}
