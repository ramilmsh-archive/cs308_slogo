package executor.commandable.turtle;

/**
 * 
 * Turtle API Controls movement of turtle, as well as visibility of turtle and
 * pen
 * 
 * @author Natalie
 * 
 */
public interface Turtle {

	/**
	 * Move turtle given distance
	 * 
	 * @param dist		distance Turtle should move
	 * @return distance turtle moved
	 */
	public double move(Double dist);

	/**
	 * Move turtle to a given TurtlePoint
	 * 
	 * @param d1		xCoordinate of TurtlePoint turtle should move to
	 * @param d2		yCoordinate of TurtlePoint turtle should move to
	 * @return distance turtle moved
	 */
	public double move(Double d1, Double d2);

	/**
	 * Turn turtle a given number of degrees
	 * 
	 * @param degrees	 number of degrees turtle should turn
	 * @return number of degrees turtle turned
	 */
	public double turn(Double degrees);

	/**
	 * Turn turtle to a given degree
	 * 
	 * @param degrees		degree turtle should face
	 * @return number of degrees turtle turned
	 */
	public double absTurn(Double degrees);

	/**
	 * Turn turtle to face a given Point
	 * 
	 * @param d1		xCoordinate of point turtle should face
	 * @param d2		yCoordinate of point turtle should face
	 * @return number of degrees turtle turned
	 */
	public double absTurn(Double d1, Double d2);

	/**
	 * Set turtle visible or invisible
	 * 
	 * @param vis		whether turtle should be visible or invisible
	 * @return whether turtle is now visible or invisible
	 */
	public double turtleVis(Double vis);
	

	/**
	 * @return turtle ID value
	 */
	public double getID();
	
	/**
	 * Set turtle shape
	 * 
	 * @param index		index to set turtle shape to
	 * @return			index of turtle shape after method executes
	 */
	public double turtleShape(Double index);
	
	/**
	 * Set color at specific index
	 * 
	 * @param index		index to change
	 * @param r			red RGB value
	 * @param g			green RGB value
	 * @param b			blue RGB value
	 * @return index of palette after method executes
	 */
	public double palette(Double index, Double r, Double g, Double b);
	
	/**
	 * Set background color
	 * 
	 * @param index		index of color to set background to
	 * @return		index of background color after method executes
	 */
	public double background(Double index);
	
	/**
	 * Sets whether or not turtle is currently active
	 * 
	 * @param a		1 for active, 0 for not
	 * @return		whether or not turtle is active after method executes
	 */
	public double active(Double a);
}
