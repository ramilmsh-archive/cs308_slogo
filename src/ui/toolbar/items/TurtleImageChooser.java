package ui.toolbar.items;

import java.io.File;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import ui.turtleGraphics.TurtleViewer;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * This class implements the turtle image chooser
 * It allows the user to browse through his/her file
 * system and select and picture to use for the turtle
 * @author DanSun
 *
 */
public class TurtleImageChooser extends ToolBarItemAbstract{

    private Button myButton;
    private TurtleViewer myTurtleViewer;
    public TurtleImageChooser(TurtleViewer turtleViewer) {
	myTurtleViewer = turtleViewer;
	myText = new Label("Turtle Image");
	createTurtleImageChooseBox();
	combineNodeWithLabelIntoLayout(myButton);
    }


    /**
     * adopted from FileChooser class example
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void createTurtleImageChooseBox() {
	myButton = new Button("Choose");
	myButton.setOnAction(new EventHandler() {
	    @Override public void handle(Event t) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
		// get current windows learned from https://stackoverflow.com/questions/13585590/how-to-get-parent-window-in-fxml-controller
		Window currentWindow = ((Node)t.getTarget()).getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(currentWindow);
		if (selectedFile != null) {
		    String imagePath = selectedFile.getAbsolutePath();
		    myTurtleViewer.changeTurtleImage(imagePath);
		}
	    }
	});

    }
}
