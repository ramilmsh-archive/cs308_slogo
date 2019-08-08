package ui.page;

import java.util.Locale;
import java.util.Map;

import controller.Controller2ViewAPI;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.UIAPI;
import ui.commandInput.CommandInput;
import ui.commandOutput.CommandOutput;
import ui.controlPanel.ControlPanel;
import ui.stateDisplay.StateDisplay;
import ui.toolbar.UiToolBar;
import ui.turtleGraphics.TurtleViewer;
import ui.util.UIComponent;

/**
 * This class implements the workspace page
 * It creates all components in the page
 * @author Dan Sun
 *
 */
public class WorkspacePage extends PageAbstract implements UIComponent, UIAPI{
    //globals that define the overall layout
    private static int TURTLE_VIEWER_WIDTH = 800;
    private static int TURTLE_VIEWER_HEIGHT = 300;
    private static int STATE_DISPLAY_WIDTH = TURTLE_VIEWER_HEIGHT / 3;
    private static int STATE_DISPLAY_HEIGHT = 300;
    private static int COMMAND_INPUT_WIDTH = TURTLE_VIEWER_WIDTH - STATE_DISPLAY_WIDTH;
    private static int COMMAND_INPUT_HEIGHT = STATE_DISPLAY_HEIGHT / 4;
    private static int COMMAND_OUTPUT_WIDTH = COMMAND_INPUT_WIDTH;
    private static int COMMAND_OUTPUT_HEIGHT = STATE_DISPLAY_HEIGHT - COMMAND_INPUT_HEIGHT;
    
    private static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private static Color DEFAULT_PEN_COLOR = Color.BLACK;

    private static String TAB_TEXT_KEY = "TabWorkspace";
    //private fields
    private GridPane root;
    private UiToolBar myUiToolBar;
    private TurtleViewer myTurtleViewer;
    private StateDisplay myStateDisplay;
    private CommandInput myCommandInput;
    private CommandOutput myCommandOutput;
    private ControlPanel myControlPanel;
    private Controller2ViewAPI myController2ViewAPI;

    /**
     * The constructor for the class
     * 
     */
    public WorkspacePage(Locale locale, Controller2ViewAPI controller2ViewAPI) {
	super(locale, TAB_TEXT_KEY);
	
	myController2ViewAPI = controller2ViewAPI;
	createPage();
	myTab.setContent(root);
    }

    @Override
    public Node getTopLevelNode() {
	return root;
    }

    /**
     * adopted from http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
     * @param myTab
     * @return
     */
    private Pane createPage() {
	root = new GridPane();
	myTurtleViewer = new TurtleViewer(TURTLE_VIEWER_WIDTH, TURTLE_VIEWER_HEIGHT,
		DEFAULT_BACKGROUND_COLOR, DEFAULT_PEN_COLOR);
	myStateDisplay = new StateDisplay(STATE_DISPLAY_WIDTH, STATE_DISPLAY_HEIGHT);
	myCommandOutput = new CommandOutput(COMMAND_OUTPUT_WIDTH, COMMAND_OUTPUT_HEIGHT);
	myCommandInput = new CommandInput(COMMAND_INPUT_WIDTH, COMMAND_INPUT_HEIGHT, 
		myCommandOutput, myController2ViewAPI);
	myUiToolBar = new UiToolBar(myTurtleViewer, myController2ViewAPI);
	myControlPanel = new ControlPanel(myController2ViewAPI, myUiToolBar.getCommandLanguageChooser());
	root.add(myUiToolBar.getTopLevelNode(), 0, 0, 2, 1);
	root.add(myTurtleViewer.getTopLevelNode(), 0, 1, 2, 1);
	addStateDisplayToPane();
	addCommandInputToPane();
	addCommandOutputToPane();
	addControlPanelToPane();
	return root;
    }



    @Override
    public boolean setActive(int index, boolean isActive) {
	return myTurtleViewer.setActive(index, isActive);	
    }

    @Override
    public void moveTurtleTo(int index, Double posX, Double posY) {
	myTurtleViewer.moveTurtleTo(index, posX, posY);	
    }

    @Override
    public void setTurtleVisibility(int index, Boolean visible) {
	myTurtleViewer.setTurtleVisibility(index, visible);
    }

    @Override
    public void setPenDown(Boolean down) {
	myTurtleViewer.setPenDown(down);
    }

    @Override
    public void rotateTurtleTo(int index, Double angle) {
	myTurtleViewer.rotateTurtleTo(index, angle);
    }

    @Override
    public void clearScreen() {
	myTurtleViewer.clearScreen();
    }

    @Override
    public void setPenColor(int index) {
	myTurtleViewer.setPenColor(index);
    }

    @Override
    public void setBackgroundColor(int index) {
	myTurtleViewer.setBackgroundColor(index);	
    }

    @Override
    public void setPenSize(double pixel) {
	myTurtleViewer.setPenSize(pixel);
    }

    @Override
    public void setTurtleImage(int index) {
	myTurtleViewer.setTurtleImage(index);
    }

    @Override
    public void setPalette(int index, Color color) {
	// TODO Auto-generated method stub
	//not implemented
    }

    @Override
    public int getPenColorInt() {
	return myTurtleViewer.getPenColorInt();
    }

    @Override
    public int getTurtleImage() {
	return myTurtleViewer.getTurtleImage();
    }


    @Override
    public void showError(String message) {
	myCommandOutput.showError(message);
    }

    @Override
    public boolean createTurtle(int index) {
	return myTurtleViewer.createTurtle(index);
    }

    @Override
    public Color getPenColor() {
	return myTurtleViewer.getPenColor();
    }

    @Override
    public void updateVariables(Map<String, String> variables) {
	myStateDisplay.updateVariables(variables);
    }

    @Override
    public void updateUserMethods(Map<String, String> methods) {
	myStateDisplay.updateUserMethods(methods);
    }
    
    /**
     * Sets the visibility of myStateDisplay
     * @param visible
     */
    public void setStateDisplayVisible(Boolean visible) {
	Node stateDisplayTopNode = myStateDisplay.getTopLevelNode();
	if(visible && !root.getChildren().contains(stateDisplayTopNode)) {
	    addStateDisplayToPane();
	}else if (!visible && root.getChildren().contains(stateDisplayTopNode) ) {
	    root.getChildren().remove(stateDisplayTopNode);
	}
	
    }

    /**
     * sets the visibility of command input area
     * @param visible
     */
    public void setCommandInputVisible(Boolean visible) {
	Node commandInputTopNode = myCommandInput.getTopLevelNode();
	if(visible && !root.getChildren().contains(commandInputTopNode)) {
	    addCommandInputToPane();
	}else if (!visible && root.getChildren().contains(commandInputTopNode) ) {
	    root.getChildren().remove(commandInputTopNode);
	}
    }
    
    /**
     * sets the visibility of the command output area
     * @param visible
     */
    public void setCommandOutputVisible(Boolean visible) {
	Node commandOutputTopNode = myCommandOutput.getTopLevelNode();
	if(visible && !root.getChildren().contains(commandOutputTopNode)) {
	    addCommandOutputToPane();
	}else if (!visible && root.getChildren().contains(commandOutputTopNode) ) {
	    root.getChildren().remove(commandOutputTopNode);
	}
    }
    
    /**
     * sets the visibility of the control panel
     * @param visible
     */
    public void setControlPanelVisible(Boolean visible) {
	Node controlPanelTopNode = myControlPanel.getTopLevelNode();
	if(visible && !root.getChildren().contains(controlPanelTopNode)) {
	    addControlPanelToPane();
	}else if (!visible && root.getChildren().contains(controlPanelTopNode) ) {
	    root.getChildren().remove(controlPanelTopNode);
	}
    }
    
    private void addControlPanelToPane() {
	root.add(myControlPanel.getTopLevelNode(), 2, 1, 1, 4);
	
    }

    private void addCommandOutputToPane() {
	root.add(myCommandOutput.getTopLevelNode(), 1, 2, 1, 1);
    }

    private void addCommandInputToPane() {
	root.add(myCommandInput.getTopLevelNode(), 1, 3, 1, 1);
    }

    private void addStateDisplayToPane() {
	root.add(myStateDisplay.getTopLevelNode(), 0, 2, 1, 2);
    }

}
