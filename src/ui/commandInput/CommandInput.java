package ui.commandInput;

import controller.Controller2ViewAPI;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.commandOutput.CommandOutput;
import ui.util.UIComponent;

/**
 * This class implements the GUI that allows the user
 * to input command via typing
 * @author Dan Sun
 *
 */
public class CommandInput implements UIComponent{

    private StringProperty commandBufferProperty;
    private TextArea root;
    private Controller2ViewAPI myController2ViewApi;

    /**
     * The constructor for CommandInput class
     * @param inputWidth The width of the input area the user sees
     * @param inputHeight The height of the input area the user sees
     */
    public CommandInput(int inputWidth, int inputHeight,
	    CommandOutput commandOutput, Controller2ViewAPI controller2ViewApi) {
	root = new TextArea("Type your command here");
	root.setMaxSize(inputWidth, inputHeight);
	commandBufferProperty = new SimpleStringProperty("");
	myController2ViewApi = controller2ViewApi;
	setSpecialActions();
	linkActionToUserInput(commandOutput);
    }

    @Override
    public Node getTopLevelNode() {
	return root;
    }

    /**
     * Getter methods for upper level class to add listener on
     * @return the commandBufferProperty, which is a buffer for command yet to be sent
     */
    public StringProperty getCommandBufferProperty() {
	return commandBufferProperty;
    }

    /**
     * public method for clearing the command buffer after 
     * upper level class knows its value
     */
    public void clearCommandBuffer() {
	commandBufferProperty.set("");
    }

    /**
     * adopted from https://stackoverflow.com/questions/9969375/adding-a-listener-to-a-variable-in-java-javafx-which-gets-called-on-variable-cha
     */
    private void linkActionToUserInput(CommandOutput commandOutput) {
	this.getCommandBufferProperty().addListener(new ChangeListener<Object>() {
	    @Override
	    public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
		// changeLister for String adopted from http://java-buddy.blogspot.com/2014/08/add-listener-to-stringproperty-to.html
		if(((String)newValue).equals("")) {
		    return;
		}
		String command = (String) newValue;
		commandOutput.printCommand(command);
		clearCommandBuffer();
		myController2ViewApi.runCommand(command);
	    }

	});
    }

    /**
     * adopted from KeyEvent from Ensemble 8
     * pressing enter sends the command
     * pressing control+enter creates a new line
     */
    private void setSpecialActions() {
	root.setOnKeyReleased((KeyEvent ke) -> {
	    if(ke.getCode()== KeyCode.ENTER) {
		if(ke.isControlDown()) {
		    root.insertText(root.getCaretPosition(), "\n");
		    return;
		}
		sendCommand();
	    }
	});

    }

    private void sendCommand() {
	String commandText = root.getText().trim();
	root.clear();
	commandBufferProperty.set(commandText);

    }


}
