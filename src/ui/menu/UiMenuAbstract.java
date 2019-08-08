package ui.menu;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import ui.menuItem.UiMenuItemAbstract;
import ui.util.LocaleSpecificObject;

/**
 * Abstract class for all menu to extend
 * @author DanSun
 *
 */
public abstract class UiMenuAbstract extends LocaleSpecificObject{

    Menu myMenu;

    /**
     * Constructor for the class
     * @param resourceBundle The file to read label from
     * @param key the key of this menu
     */
    public UiMenuAbstract(ResourceBundle resourceBundle, String key) {
	super(resourceBundle);
	myMenu = new Menu(myResourceBundle.getString(key));
    }

    /**
     * Public method for adding an menu item to the menu
     * @param uiMenuItem The menu item to be added
     */
    public void addUiMenuItem(UiMenuItemAbstract uiMenuItem) {
	myMenu.getItems().addAll(uiMenuItem.getMenuItem());
    }

    /**
     * Getter method for the menu
     * @return The menu contained in this class
     */
    public Menu getMenu() {
	return myMenu;
    }
}
