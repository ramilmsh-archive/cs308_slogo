package ui.toolbar.items;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ui.util.UIComponent;

/**
 * This class is the abstract ToolBarItem class
 * Every tool bar item should extend this class
 * @author Dan Sun
 *
 */
public abstract class ToolBarItemAbstract implements UIComponent{
    
    protected VBox layout;
    protected Label myText;
    
    public ToolBarItemAbstract() {
	// do nothing
    }
    
    protected void combineNodeWithLabelIntoLayout(final Node node1) {
	layout = new VBox();
	layout.getChildren().addAll(node1, myText);
	layout.setAlignment(Pos.CENTER);
    }
    
    @Override
    public Node getTopLevelNode() {
	return layout;
    }

}
