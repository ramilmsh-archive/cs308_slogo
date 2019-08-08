package ui;

import java.util.Locale;
import java.util.Map;

import controller.Controller2ViewAPI;
import controller.file.Reader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.helpWindow.HelpWindow;
import ui.menuBar.UiMenuBar;
import ui.menuBar.UiMenuBarCreater;
import ui.page.PageViewer;
import ui.page.WorkspacePage;

/**
 * A class servers as the top-most level of UI
 * sets up the initial UI components
 * handles communication task between the UI and the controller
 * @author Dan Sun
 *
 */

public class MainUI implements UIAPI {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    //scene, container of all elements
    private Scene myScene;
    //used for organizing layout
    private Pane root;
    //menu
    private UiMenuBar myUiMenuBar;
    //PageViewer, contains pages and their tabs
    private PageViewer myPageViewer;
    //help window
    private HelpWindow myHelpWindow;

    
    /**
     * constructor for MainUI
     * @param myStage the Stage
     * @param controller2ViewAPI Controller interface
     * @param reader 
     */
    public MainUI(Stage myStage, Controller2ViewAPI controller2ViewAPI, Reader reader) {
	root = new VBox();
	//create scene to hold UI
	myScene = new Scene(root);
	myHelpWindow = new HelpWindow();
	myPageViewer = createPageViewer(controller2ViewAPI);
	
	myUiMenuBar = createUiMenuBar();
	root.getChildren().add(myUiMenuBar.getTopLevelNode());
	root.getChildren().add(myPageViewer.getTopLevelNode());
	myStage.setResizable(false); //do not allow user to mess with layout for now
    }
    private PageViewer createPageViewer(Controller2ViewAPI controller2ViewAPI) {
	PageViewer pageViewer = new PageViewer();
	WorkspacePage workspacePage = new WorkspacePage(DEFAULT_LOCALE, controller2ViewAPI);
	pageViewer.addWorkspace(workspacePage);
	return pageViewer;
    }
    /**
     * MakeUserInstruction be used the the top level stage to set scene
     * @return holder of all objects
     */
    public Scene getScene() {
	return myScene;
    }

    private UiMenuBar createUiMenuBar() {
	UiMenuBarCreater uiMenuBarFactory = new UiMenuBarCreater(DEFAULT_LOCALE);
	return uiMenuBarFactory.createUiMenuBar(myHelpWindow, myPageViewer);
    }
    
    @Override
    public boolean createTurtle(int index) {
	return myPageViewer.getCurrentActiveWorkspace().createTurtle(index);
    }
    
    @Override
    public boolean setActive(int index, boolean isActive) {
	return myPageViewer.getCurrentActiveWorkspace().setActive(index, isActive);
    }
    
    @Override
    public void moveTurtleTo(int index, Double posX, Double posY) {
	myPageViewer.getCurrentActiveWorkspace().moveTurtleTo(index, posX, posY);
    }
    
    @Override
    public void setTurtleVisibility(int index, Boolean visible) {
	myPageViewer.getCurrentActiveWorkspace().setTurtleVisibility(index, visible);
    }
    
    @Override
    public void setPenDown(Boolean down) {
	myPageViewer.getCurrentActiveWorkspace().setPenDown(down);
    }
    
    @Override
    public void rotateTurtleTo(int index, Double angle) {
	myPageViewer.getCurrentActiveWorkspace().rotateTurtleTo(index, angle);
    }
    
    @Override
    public void clearScreen() {
	myPageViewer.getCurrentActiveWorkspace().clearScreen();
    }
    
    @Override
    public void setPenColor(int index) {
	myPageViewer.getCurrentActiveWorkspace().setPenColor(index);
    }
    
    @Override
    public void setBackgroundColor(int index) {
	myPageViewer.getCurrentActiveWorkspace().setBackgroundColor(index);
    }
    
    @Override
    public void setPenSize(double pixel) {
	myPageViewer.getCurrentActiveWorkspace().setPenSize(pixel);
    }
    
    @Override
    public void setTurtleImage(int index) {
	myPageViewer.getCurrentActiveWorkspace().setTurtleImage(index);
    }
    
    @Override
    public void setPalette(int index, Color color) {
	myPageViewer.getCurrentActiveWorkspace().setPalette(index, color);
    }
    
    @Override
    public int getPenColorInt() {
	return myPageViewer.getCurrentActiveWorkspace().getPenColorInt();
    }
    
    @Override
    public int getTurtleImage() {
	return myPageViewer.getCurrentActiveWorkspace().getTurtleImage();
    }
    

    @Override
    public void showError(String message) {
	myPageViewer.getCurrentActiveWorkspace().showError(message);
    }
    
    @Override
    public Color getPenColor() {
	myPageViewer.getCurrentActiveWorkspace().getPenColor();
	return null;
    }
    @Override
    public void updateVariables(Map<String, String> variables) {
	myPageViewer.getCurrentActiveWorkspace().updateVariables(variables);
	
    }
    @Override
    public void updateUserMethods(Map<String, String> methods) {
	myPageViewer.getCurrentActiveWorkspace().updateUserMethods(methods);
	
    }


    
    
}
