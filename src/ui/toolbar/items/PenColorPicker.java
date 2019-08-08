package ui.toolbar.items;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ui.turtleGraphics.TurtleViewer;
import ui.util.UIComponent;

public class PenColorPicker extends ToolBarItemAbstract implements UIComponent 
 {
    
    private ColorPicker myColorPicker; 
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PenColorPicker(TurtleViewer turtleViewer) {
	myColorPicker = new ColorPicker(turtleViewer.getPenColor());
	myText = new Label("Pen");
	combineNodeWithLabelIntoLayout(myColorPicker);
	myColorPicker.setOnAction(new EventHandler() {
	    @Override public void handle(Event t) {
		Color newColor = myColorPicker.getValue();
		turtleViewer.setPenColor(newColor);
	    }
	});
    }



}
