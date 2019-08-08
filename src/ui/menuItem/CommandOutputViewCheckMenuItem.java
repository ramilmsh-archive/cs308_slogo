package ui.menuItem;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;
import ui.page.PageViewer;
/**
 * This class implements the CommandInputCheckMenuItem
 * Toggling makes CommandOutput area go away/reappear
 * @author Dan Sun
 *
 */
public class CommandOutputViewCheckMenuItem extends UiMenuItemAbstract {

    private static String myKey = "MenuItemCommandOutput";

    private CheckMenuItem myCheckMenuItem; 
    public CommandOutputViewCheckMenuItem(ResourceBundle resourceBundle, PageViewer pageViewer) {
	super(resourceBundle, myKey);
	myCheckMenuItem = new CheckMenuItem(myMenuItem.getText());
	myMenuItem = myCheckMenuItem;
	//adopted from https://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
	myCheckMenuItem.setSelected(true);
	myCheckMenuItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
	    public void changed(@SuppressWarnings("rawtypes") ObservableValue ov,
		    Boolean old_val, Boolean new_val) {
		pageViewer.getCurrentActiveWorkspace().setCommandOutputVisible(new_val);
	    }
	});
    }
}
