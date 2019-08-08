package ui.controlPanel;

import controller.Controller2ViewAPI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import ui.toolbar.items.CommandLanguageChooser;
import ui.util.UIComponent;

/**
 * This class is to be extended by all buttons
 * that is a substitute for commands
 * @author DanSun
 *
 */
public abstract class ControlButtonAbstract implements UIComponent {

    protected Button myButton;
    protected String myText;
    protected String myCommand;
    protected Controller2ViewAPI myController2ViewApi;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ControlButtonAbstract(Controller2ViewAPI controller2ViewApi,
	    String text, String command, CommandLanguageChooser commandLanguageChooser) {
	myController2ViewApi = controller2ViewApi;
	myText = text;
	myCommand = command;
	myButton = new Button(myText);
	myButton.setOnAction(new EventHandler() {
	    @Override public void handle(Event t) {
		buttonPressed(commandLanguageChooser);
	    }
	});
    }
    
    @Override
    public Node getTopLevelNode() {
	return myButton;
    }


    protected void buttonPressed(CommandLanguageChooser commandLanguageChooser) {
	String oldLanguage = commandLanguageChooser.getLanguage();
	myController2ViewApi.changeLanguage("English");
	myController2ViewApi.runCommand(myCommand);
	myController2ViewApi.changeLanguage(oldLanguage);
    }

}
