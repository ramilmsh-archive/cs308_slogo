package ui.page;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import ui.util.UIComponent;

/**
 * This class shows encapsulates the view of each page in 
 * the main UI, and contains the tabs related to them.
 * @author Dan Sun
 *
 */
public class PageViewer implements UIComponent{


    
    private TabPane myTabPane;
    private ArrayList<WorkspacePage> myWorkspaces;
    
    public PageViewer() {
	myWorkspaces = new ArrayList<>();
	createTabPane();
    }
    
    @Override
    public Node getTopLevelNode() {
	return myTabPane;
    }

    
    /**
     * public method for adding a page to the viewer
     * @param page The page to be added
     */
    public void addWorkspace(WorkspacePage page) {
	myWorkspaces.add(page);
	myTabPane.getTabs().add(page.getTab());
    }
    
    /**
     * adopted from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TabPane.html
     * @param root top level container in scene
     */
    private void createTabPane() {
	myTabPane = new TabPane();
	myTabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); //adopted from JavaFX sample: TabPane
    }

    /**
     * Gets the currently active workspace
     * @return The currently active workspace
     */
    public WorkspacePage getCurrentActiveWorkspace() {
	int activeIndex = myTabPane.getSelectionModel().getSelectedIndex();
	return myWorkspaces.get(activeIndex);
    }




}
