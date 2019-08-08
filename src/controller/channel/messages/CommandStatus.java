package controller.channel.messages;

/**
 * 
 * @author cy122
 * 
 * This class is related to the return status of command.
 * Interpreter will be a publisher of the COMMAND_STATUS channel, and Controller will subscribe to it.
 *
 */

public class CommandStatus extends Message{
	/*success is true when the command runs successfully, is false when the command has syntax error*/
	public Boolean success = true;
	/* this is the return value of the command, for example, if controller publishes 'fd fd fd fd 5' in INTERPRET Channel,
	 * controller will subscribe to the COMMAND_STATUS Channel, and get a CommandStatus Object contains returnValue=5.0, success=true.
	 */
	public Double returnValue = 0.0;
	
	public CommandStatus(Boolean success, Double returnValue){
		this.success=success;
		this.returnValue=returnValue;
	}
	
	public CommandStatus(Boolean success){
		this.success=success;
	}
	
	public CommandStatus(Double returnValue){
		this.returnValue=returnValue;
	}
}
