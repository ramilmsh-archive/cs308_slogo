package ui.controlPanel;

import controller.Controller2ViewAPI;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import ui.toolbar.items.CommandLanguageChooser;
import ui.util.UIComponent;

/**
 * This class provide buttons that allow the user
 * to interact with the turtle by pressing buttons
 * instead of typing commands
 * @author Dan Sun
 *
 */
public class ControlPanel implements UIComponent{
    
    GridPane myGridPane;
    public ControlPanel(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	myGridPane = new GridPane();
	addControlButtons(controller2ViewApi, commandLanguageChooser);
    }
    

    @Override
    public Node getTopLevelNode() {
	return myGridPane;
    }
    
    private void addControlButtons(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	ForwardButton fd = new ForwardButton(controller2ViewApi, commandLanguageChooser);
	BackwardButton bk = new BackwardButton(controller2ViewApi, commandLanguageChooser);
	LeftTurnButton lt = new LeftTurnButton(controller2ViewApi, commandLanguageChooser);
	RightTurnButton rt = new RightTurnButton(controller2ViewApi, commandLanguageChooser);
	myGridPane.add(fd.getTopLevelNode(), 0, 0);
	myGridPane.add(bk.getTopLevelNode(), 0, 1);
	myGridPane.add(lt.getTopLevelNode(), 1, 0);
	myGridPane.add(rt.getTopLevelNode(), 1, 1);
    }

}
