package ui.menuBar;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import ui.menu.UiMenuAbstract;
import ui.util.UIComponent;

/**
 * This class encapsulates the menu bar seen on the view
 * @author Dan Sun
 *
 */
public class UiMenuBar implements UIComponent{

    MenuBar myMenuBar;
    
    /**
     * Constructor for the class
     */
    public UiMenuBar() {
	myMenuBar = new MenuBar();
    }
    
    @Override
    public Node getTopLevelNode() {
	return myMenuBar;
    }
    
    /**
     * Adds an menu to the menu bar
     * @param uiMenu The menu to be added
     */
    public void addUiMenu(UiMenuAbstract uiMenu) {
	myMenuBar.getMenus().add(uiMenu.getMenu());
    }
    
}
