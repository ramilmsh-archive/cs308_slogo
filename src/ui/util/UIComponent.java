package ui.util;

import javafx.scene.Node;
/**
 * This interface is to be implemented by every UI component that is visible to the user
 * 
 * @author DanSun
 *
 */
public interface UIComponent {
    /**
     * Gets the top level node 
     * @return
     */
    public Node getTopLevelNode();
}
