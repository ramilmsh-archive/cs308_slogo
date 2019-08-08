package ui.menuItem;

import java.util.ResourceBundle;

import javafx.scene.control.MenuItem;
import ui.util.LocaleSpecificObject;
/**
 * Abstract class for menu items
 * @author Dan Sun
 *
 */
public abstract class UiMenuItemAbstract extends LocaleSpecificObject{
    protected MenuItem myMenuItem;
    
    /**
     * constructor for the class
     * @param text Text to be displayed on the menu
     */
    public UiMenuItemAbstract(ResourceBundle resourceBundle, String key) {
	super(resourceBundle);
	myMenuItem = new MenuItem(myResourceBundle.getString(key));
    }
    
    /**
     * Getter for MenuItem
     * @return The MenuItem in this class
     */
    public MenuItem getMenuItem() {
	return myMenuItem;
    };
    
}
