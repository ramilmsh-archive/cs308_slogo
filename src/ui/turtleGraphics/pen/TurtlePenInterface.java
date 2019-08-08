package ui.turtleGraphics.pen;

import javafx.scene.paint.Color;

/**
 * This interface defines the functionality of the pen
 * @author Dan Sun
 *
 */
public interface TurtlePenInterface {
    /**
     * change the pen up or down.
     * @param down True means pen is down.
     */
    public void setPenDown(Boolean down);
    
    /**
     * sets color of the pen to that represented by index
     * @param index
     */
    public void setPenColor(int index);
    
    /**
     * sets size of the pen to be pixels thickness
     * @param pixel
     */
    public void setPenSize(double pixel);
    
    /**
     * returns pen's current color index
     * @return
     */
    public int getPenColorInt();
    
    /**
     * Gets the color of the pen
     * @return Color of the pen
     */
    public Color getPenColor();
}
