package ui.toolbar;

import controller.Controller2ViewAPI;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import ui.toolbar.items.BackgroundColorPicker;
import ui.toolbar.items.CommandLanguageChooser;
import ui.toolbar.items.PenColorPicker;
import ui.toolbar.items.TurtleImageChooser;
import ui.turtleGraphics.TurtleViewer;
import ui.util.UIComponent;

/**
 * This class implements the tool bar seen at the top of the page
 * It creates everything needed and holds them
 * @author Dan Sun
 *
 */
public class UiToolBar implements UIComponent{

    private ToolBar myToolBar;
    BackgroundColorPicker myBackgroundColorPicker;
    PenColorPicker myPenColorPicker;
    TurtleImageChooser myTurtleImageChooser;
    CommandLanguageChooser myCommandLanguageChooser;
    public UiToolBar(TurtleViewer turtleViewer, 
	    Controller2ViewAPI controller2ViewApi) {
	myToolBar = new ToolBar();
	crateToolBar(turtleViewer, controller2ViewApi);
    }

    @Override
    public Node getTopLevelNode() {
	return myToolBar;
    }
    
    /**
     * getter method for other object to access 
     * and inquire about current language
     * @return
     */
    public CommandLanguageChooser getCommandLanguageChooser() {
	return myCommandLanguageChooser;
    }

    /**
     * adopted from ToolBar Example from Ensemble 8
     * @return Toolbar being created
     */
    private void crateToolBar(TurtleViewer turtleViewer,
	    Controller2ViewAPI controller2ViewApi) {
	myToolBar = new ToolBar();
	myBackgroundColorPicker = new BackgroundColorPicker(turtleViewer);
	myPenColorPicker = new PenColorPicker(turtleViewer);
	myTurtleImageChooser = new TurtleImageChooser(turtleViewer);
	myCommandLanguageChooser = new CommandLanguageChooser(controller2ViewApi);
	myToolBar.getItems().add(myBackgroundColorPicker.getTopLevelNode());
	myToolBar.getItems().add(myPenColorPicker.getTopLevelNode());
	myToolBar.getItems().add(myTurtleImageChooser.getTopLevelNode());
	myToolBar.getItems().add(myCommandLanguageChooser.getTopLevelNode());
    }












}
