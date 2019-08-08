package ui.commandOutput;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import ui.util.UIComponent;

/**
 * This class implements the GUI that allows the user
 * to see history of commands as well as their outputs
 * @author Dan Sun
 *
 */
public class CommandOutput implements UIComponent, CommandOutputInterface{

	private TextArea root;
	/**
	 * Constructor for CommandOutput class 
	 * @param outputWidth The width of the output area the user sees
	 * @param outputHeight The height of the output area the user sees
	 */
	public CommandOutput(int outputWidth, int outputHeight) {
		root = new TextArea();
		root.setMaxSize(outputWidth, outputHeight);
		root.setEditable(false);
	}
	
	@Override
	public Node getTopLevelNode() {
	    return root;
	}
	
	
	/**
	 * Writes given command to the output area
	 * The idea of using font to change color comes from 
	 * TextFlow example from Ensemble 8
	 * @param command The command to be printed
	 */
	public void printCommand(String command) {
		root.appendText(">> " + command + "\n");
	}
	
	/**
	 * Writes error message to the output area
	 * @param error Error message to be printed
	 */
	@Override
	public void showError(String error) {
		root.appendText("ERROR: " + error + "\n");
	}


	
}
