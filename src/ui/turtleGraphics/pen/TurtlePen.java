package ui.turtleGraphics.pen;


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This class implements the pen object.
 * The pen object draws a line given its the coordinates
 * based on the current state of the pen
 * @author Dan Sun
 *
 */
public class TurtlePen implements TurtlePenInterface{

    private static boolean DEFAULT_PEN_DOWN = true;
    private static Color DEFAULT_COLOR = Color.BLACK;
    private static double DEFAULT_LINE_WIDTH = 5;
    private Color myPenColor;
    private boolean myPenDown;
    private double myLineWidth;

    public TurtlePen() {
	myPenDown = DEFAULT_PEN_DOWN;
	myPenColor = DEFAULT_COLOR;
	myLineWidth = DEFAULT_LINE_WIDTH;
    }

    /**
     * Draws a line using the current state of the pen
     * @param oldX x coordinate of the start of the line
     * @param oldY y coordinate of the start of the line
     * @param newX x coordinate of the end of the line
     * @param newY y coordinate of the end of the line
     * @return
     */
    public Line drawNewLine(double oldX, double oldY, double newX, double newY) {
	if(!myPenDown) return null;
	Line newLine = new Line(oldX, oldY, newX, newY);
	newLine.setFill(myPenColor);
	newLine.setStroke(myPenColor);
	newLine.setStrokeWidth(myLineWidth);
	return newLine;
    }

    @Override
    public void setPenDown(Boolean down) {
	myPenDown = down;
    }

    @Override
    public void setPenColor(int index) {
	Color penColor = intToColor(index);
	myPenColor = penColor;
    }
    /**
     * Sets the color of the pen
     * @param newColor An Color object that specifies the color of the pen
     */
    public void setPenColor(Color newColor) {
	myPenColor = newColor;
    }

    @Override
    public void setPenSize(double pixel) {
	myLineWidth = pixel;
	
    }

    @Override
    public int getPenColorInt() {
	int integer = colorToInt(myPenColor);
	return integer;
    }    
    
    
    @Override
    public Color getPenColor() {
	return myPenColor;
    }
    
    private int colorToInt(Color myPenColor2) {
	// TODO Auto-generated method stub
	return 0;
    }

    private Color intToColor(int index) {
	// TODO Auto-generated method stub
	return null;
    }


}
