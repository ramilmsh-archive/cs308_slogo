package ui.turtleGraphics;

import javafx.scene.paint.Color;
import ui.turtleGraphics.pen.TurtlePenInterface;
/**
 * This interface specifies methods needs to be implemented by 
 * the TurtleViewer class
 * @author cy122
 * @author Dan Sun
 *
 */
public interface TurtleViewerInterface extends TurtlePenInterface{

    /**
     * 
     * @param index The index assigned to the turtle to be created
     * @return True if the turtle is created; false if a turtle with a given index exists
     */
    public boolean createTurtle(int index);
    /**
     * 
     * @param index - sets an active turtle that will follow commands hereafter
     * @param isActive True if the turtle is set active; false if it is set to be inactivce
     * @return True if the turtle corresponding to the index exists; false otherwise.
     */
    public boolean setActive(int index, boolean isActive);
    
    /**
     * 
     * @param index - the index of a turtle which will update visibility
     * @param posX - the coordinates of X where the turtle should be at, 0.0 means the center, negative value means left of the center.
     * @param posY - the coordinates of Y where the turtle should be at, 0.0 means the center, negative value means upper of the center.
     */
    public void moveTurtleTo(int index, Double posX, Double posY);

    /**
     * change the turtle visibility.
     * @param index - the index of a turtle which will update visibility
     * @param visible - True if the turtle should be visible
     */
    public void setTurtleVisibility(int index, Boolean visible);

    /**
     * 
     * @param index - the index of a turtle which will rotate
     * @param angle - the degree of angles that the turtle should be at after moving. 0.0 means head to north. 90.0 means head to east.
     */
    public void rotateTurtleTo(int index, Double angle);

    /**
     * clear the screen and make all the turtle's position back to the center of screen
     */
    public void clearScreen();
   
    /**
     * sets background color of screen to that represented by index
     * @param index
     */
    public void setBackgroundColor(int index);
    
    /**
     * sets shape of turtle to that represented by index
     * @param index
     */
    public void setTurtleImage(int index);
    
    /**
     * sets color corresponding at given index to given color
     * @param index
     * @param color
     */
    public void setPalette(int index, Color color);
    
    /**
     * returns turtle's current shape index
     * @return
     */
    public int getTurtleImage();

}
