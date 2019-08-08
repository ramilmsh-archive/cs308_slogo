package ui.turtleGraphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import ui.turtleGraphics.pen.TurtlePen;

/**
 * This class implements functionality
 * needed by the turtle at the front end
 * @author DanSun
 *
 */
public class TurtleView {
    //default turtle image taken from https://creativemarket.com/BekBlack/1471055-Turtle-Logo-Template
    private static String DEFAULT_IMAGE_PATH = "/resources/ui/turtle0.jpg";  
    private static boolean DEFAULT_TURTLE_VISIBLE = true;
    //private properties

    private double myHorizontalOffset;
    private double myVerticalOffset;

    private int mySize;

    private String myImagePath;

    private boolean myVisibility;
    private Image myImage; 
    //the X and Y value should correspond to the that on the UI
    private TurtlePen myPen;
    private List<Line> myLines; //X and Y values are actual values on canvas
    private ImageView myImageView;
    private Pane myUpperLevelPane;

    /**
     * constructor for the class
     * @param initialHOffset Initial horizontal offset for the canvas
     * @param initialVOffset Initial vertical offset for the canvas
     * @param size The size of the turtle
     */
    public TurtleView(double initialHOffset, double initialVOffset, 
	    int size, TurtlePen turtlePen, Pane pane) {
	myHorizontalOffset = initialHOffset;
	myVerticalOffset = initialVOffset;
	mySize = size;
	myUpperLevelPane = pane;
	myPen = turtlePen;
	myImagePath = DEFAULT_IMAGE_PATH;
	myVisibility = DEFAULT_TURTLE_VISIBLE;
	myLines = new ArrayList<>();
	loadTurtleImage();
    }

    /**
     * changes the image of the turtle
     * @param path The path to the file
     */
    public void changeTurtleImage(String path) {
	myImagePath = path;
	InputStream is = null;
	try {
	    is = new FileInputStream(myImagePath);
	} catch (FileNotFoundException e) {	  
	    //TODO: use better way to handle file not found exception
	    System.out.println("Turtle image file not found");
	    e.printStackTrace();
	    return;
	}
	myImage = new Image(is);
	updateTurtleImageView();
    }

    /**
     * Updates the coordinates of the turtle to match those given by the back end
     * @param posX The x coordinate stored in the back end
     * @param posY THe y coordiante stored in the back end
     */
    public void updateTurtleCoordinates(double posX, double posY) {
	myImageView.setX(posX + myHorizontalOffset);
	myImageView.setY(posY + myVerticalOffset);
    }

    /**
     * Allows changes to whether the turtle is visible or not
     * @param visible True if the canvas wants the turtle to be visible
     */
    public void setVisibility(boolean visible) {
	myVisibility = visible;
	myImageView.setVisible(visible);
    }


    /**
     * sets the rotation of the turtle to match the back end
     * @param angle The angle provided by the back end
     */
    public void rotateTo(Double angle) {
	myImageView.setRotate(angle);
    }
    /**
     * Accesses the x coordinate of center of the turtle
     * @return The x Coordinate of the center of turtle
     */
    public double getCenterX() {
	return getCanvasTopLeftX() + mySize / 2;
    }

    /**
     * Accesses the y coordinate of center of the turtle
     * @return The y Coordinate of the center of turtle
     */
    public double getCenterY() {
	return getCanvasTopLeftY() + mySize / 2;
    }


    /**
     * Allows outside access to whether the turtle should be visible
     * @return True if turtle should be visible on the canvas
     */
    public boolean isVisible() {
	return myVisibility;
    }
    /**
     * Get an image object that can be drawn on canvas
     * adopted from https://stackoverflow.com/questions/40059836/rotating-image-in-javafx
     * @return An Image object to be drawn on canvas
     */
    public Image currentImageOnCanvas() {
	SnapshotParameters params = new SnapshotParameters();
	params.setFill(Color.TRANSPARENT);
	return myImageView.snapshot(params, null);
    }

    /**
     * Gets the y coordinates of the top-left point on the canvas
     * @return the y coordinate of the top-left corner
     */
    public double getCanvasTopLeftY() {
	return myImageView.getY();
    }
    /**
     * Gets the x coordinates of the top-left point on the canvas
     * @return the x coordinate of the top-left corner
     */
    public double getCanvasTopLeftX() {
	return myImageView.getX();
    }

    /**
     * Moves turtle to the specified location
     * adds offset before doing so
     * @param posX X coordinate in the back end
     * @param posY Y coordinate in the back end
     */
    public void moveTo(Double posX, Double posY) {
	double lineStartX = getCenterX();
	double lineStartY = getCenterY();
	updateTurtleCoordinates(posX, posY);
	double lineEndX = getCenterX();
	double lineEndY = getCenterY();
	Line newLine = myPen.drawNewLine(lineStartX, lineStartY, 
		lineEndX, lineEndY);
	if(newLine!= null) {
	    myLines.add(newLine);
	    myUpperLevelPane.getChildren().add(newLine);
	}
	setImageViewToTop();
    }

    private void updateTurtleImageView() {
	double turtleX = myHorizontalOffset;
	double turtleY = myVerticalOffset;
	double turtleAngle = 0;

	if(myImageView != null) {
	    turtleX = myImageView.getX();
	    turtleY = myImageView.getY();
	    turtleAngle = myImageView.getRotate();
	    myUpperLevelPane.getChildren().remove(myImageView);
	}
	initializeNewImageView();
	myImageView.setX(turtleX);
	myImageView.setY(turtleY);
	myImageView.setRotate(turtleAngle);
	myUpperLevelPane.getChildren().add(myImageView);
    }

    private void initializeNewImageView() {
	myImageView = new ImageView(myImage);
	myImageView.setFitWidth(mySize);
	myImageView.setFitHeight(mySize);
    }

    private void loadTurtleImage() {
	//use of image adopted from Image Creation example in Ensemble 8
	InputStream is = TurtleViewer.class.getResourceAsStream(myImagePath);
	myImage = new Image(is);
	updateTurtleImageView();
    }

    private void setImageViewToTop() {
	myUpperLevelPane.getChildren().remove(myImageView);
	myUpperLevelPane.getChildren().add(myImageView);
    }

}
