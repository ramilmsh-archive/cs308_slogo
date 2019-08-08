package ui.menuItem;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.helpWindow.HelpWindow;

/**
 * class for the menu item that shows the help window 
 * when pressed
 * @author DanSun
 *
 */
public class CommandDocumentItem extends UiMenuItemAbstract{

    private static String myKey = "MenuItemCommandDocument";


    /**
     * constructor for the class
     * @param helpWindow The help window to be shown
     */
    public CommandDocumentItem(ResourceBundle resourceBundle, HelpWindow helpWindow) {
	super(resourceBundle, myKey);
	myMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent t) {
		if(helpWindow.isShowing()) {
		    helpWindow.toFront();
		    return;
		}
		helpWindow.show();
	    }
	});        
    }


}
