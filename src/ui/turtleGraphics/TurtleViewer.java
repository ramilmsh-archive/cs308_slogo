package ui.turtleGraphics;


import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.turtleGraphics.pen.TurtlePen;
import ui.util.UIComponent;

/**
 * This class encapsulates turtle graphics the user
 * sees on the main UI
 * @author Dan Sun
 *
 * Inspired by Canvas Fireworks in Ensemble 8, a Javafx demo
 * The idea of overlapping canvas 
 */
public class TurtleViewer implements UIComponent, TurtleViewerInterface{
    private static int TURTLE_DEFAULT_SIZE = 50; 

    //private fields
    private Pane myPane;
    private Rectangle myBackgroundRectangle;
    private Color myBackground;
    private TurtlePen myTurtlePen;
    private static int DEFAULT_TURTLE_INDEX = 1;

    private int myViewerWidth;
    private int myViewerHeight;
    private Map<Integer, TurtleView> myTurtleViews;
    /**
     * constructor for TurtleViewer
     * @param viewerWidth Width of the view 
     * @param viewerHeight Height of the view
     */
    public TurtleViewer(int viewerWidth, int viewerHeight, Color backgroundColor, Color penColor) {
	myViewerWidth = viewerWidth;
	myViewerHeight = viewerHeight;
	myBackground = backgroundColor;
	myPane = new Pane();
	myPane.setPrefSize(viewerWidth, viewerHeight);
	myPane.setMaxSize(viewerWidth, viewerHeight);
	myPane.setMaxSize(viewerWidth, viewerHeight);
	myBackgroundRectangle = new Rectangle(0, 0, viewerWidth, viewerHeight);
	myBackgroundRectangle.setFill(backgroundColor);
	myPane.getChildren().add(myBackgroundRectangle);
	myTurtleViews = new HashMap<>();
	myTurtlePen = new TurtlePen();
	createNewTurtle(DEFAULT_TURTLE_INDEX);
    }

    @Override
    public Node getTopLevelNode() {
	return myPane;
    }


    @Override
    public void moveTurtleTo(int index, Double posX, Double posY) {
	if(!checkTurtleViewIndexExists(index)) return;
	myTurtleViews.get(index).moveTo(posX, posY);
    }

    @Override
    public void setTurtleVisibility(int index, Boolean visible) {
	if(!checkTurtleViewIndexExists(index)) return;
	myTurtleViews.get(index).setVisibility(visible);
    }

    @Override
    public void rotateTurtleTo(int index, Double angle) {
	if(!checkTurtleViewIndexExists(index)) return;
	myTurtleViews.get(index).rotateTo(angle);
    }

    @Override
    public void setPenColor(int index) {
	myTurtlePen.setPenColor(index);
    }

    @Override
    public void setBackgroundColor(int index) {
	Color bgColor = intToColor(index);
	changeBackgroundColor(bgColor);
    }

    public void changeBackgroundColor(Color backgroundColor) {
	myBackground = backgroundColor;
	myBackgroundRectangle.setFill(backgroundColor);
    }

    /**
     * Gets the current color of the background
     * @return The current color of the background
     */
    public Color getBackgroundColor() {
	return myBackground;
    }

    @Override
    public void setPenSize(double pixel) {
	myTurtlePen.setPenSize(pixel);

    }
    @Override
    public void setTurtleImage(int index) {
	assert (myTurtleViews!=null);
	String imagePath = indexToImagePath();
	changeTurtleImage(imagePath);
    }

    /**
     * Change the image of the turtle per user selection
     * @param path Path of the file
     */
    public void changeTurtleImage(String path) {
	assert(myTurtleViews != null);
	for(Integer i:myTurtleViews.keySet()) {
	    myTurtleViews.get(i).changeTurtleImage(path);
	}
    }

    @Override
    public void setPalette(int index, Color color) {
	// TODO Auto-generated method stub

    }
    @Override
    public int getPenColorInt() {
	return myTurtlePen.getPenColorInt();
    }

    /**
     * Gets the current color of the pen
     * @return The current color of the pen
     */
    @Override
    public Color getPenColor() {
	return myTurtlePen.getPenColor();
    }
    @Override
    public int getTurtleImage() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void setPenDown(Boolean down) {
	myTurtlePen.setPenDown(down);;
    }

    @Override
    public void clearScreen() {
	myPane.getChildren().clear();
	myPane.getChildren().add(myBackgroundRectangle);
	myTurtleViews.clear();
	createNewTurtle(DEFAULT_TURTLE_INDEX);
    }

    public void setPenColor(Color newColor) {
	myTurtlePen.setPenColor(newColor);
    }

    @Override
    public boolean createTurtle(int index) {
	if(checkTurtleViewIndexExists(index)) return false;
	createNewTurtle(index);
	return true;
    }

    @Override
    public boolean setActive(int index, boolean isActive) {
	// TODO Auto-generated method stub
	return false;
    }


    private String indexToImagePath() {
	// TODO Auto-generated method stub
	return null;
    }

    private Color intToColor(int index) {
	//TODO: implement this
	return Color.WHITE;
    }

    private boolean checkTurtleViewIndexExists(int index) {
	for(Integer i:myTurtleViews.keySet()) {
	    if(i.intValue() == index) {
		return true;
	    }
	}
	return false;
    }


    private void createNewTurtle(int index) {
	double horizontalOffset = myViewerWidth / 2 - TURTLE_DEFAULT_SIZE / 2;
	double verticalOffset = myViewerHeight / 2 - TURTLE_DEFAULT_SIZE / 2;
	TurtleView turtleView = new TurtleView(horizontalOffset,verticalOffset,
		TURTLE_DEFAULT_SIZE, myTurtlePen, myPane);
	myTurtleViews.put(index, turtleView);
    }




}
