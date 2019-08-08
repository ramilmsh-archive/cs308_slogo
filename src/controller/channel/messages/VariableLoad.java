package controller.channel.messages;

import java.util.Map;

/**
 * 
 * @author cy122
 * 
 * This class helps the controller to implement the load workspace function,
 * in order to load the active variables, and user-defined commands from files and pass them to interpreter.
 * Interpreter is a subscriber of this, and controller is a publisher of this.
 * Also, Interpreter should publish these variables and user-defined commands on the VARIABLE_UPDATE Channel to
 * make the front-end and back-end on the same page of knowing these variables and commands, after controller publishes these on 
 * VARIABLE_LOAD Channel.
 *
 */
public class VariableLoad extends Message{
	public Map<String, String> functions;
	/* for any String value in variables, it presents the value of variable.*/
	public Map<String, String> variables;
	
	public VariableLoad(Map<String,String> functions, Map<String, String> variables){
		this.functions=functions;
		this.variables=variables;
	}
}
