package command;

/**
 * Outline class for CommandUnit
 * 
 * @author Natalie
 *
 */
public interface Command {

	/**
	 * @return command that user entered
	 */
	public String getCommand();

	/**
	 * @return value associated with command
	 */
	public Object getValue();

	/**
	 * @return returns class type of value
	 */
	public Class[] getValType();
	
	/**
	 * @return which commandable object the 
	 * method corresponds to
	 */
	public String getCommandableType();
}
