package executor.commandable.turtle;

import java.awt.geom.Point2D;

/**
 * Rewrite of the Point class so movement commands can be done locally instead
 * of using getters/setters
 * 
 * @author Natalie
 *
 */

public class TurtlePoint extends Point2D.Double {

	/**
	 * @param x		x coordinate point
	 * @param y		y coordinate point
	 */
	public TurtlePoint(double x, double y) {
		super(x, y);
	}

	/**
	 * Moves point to same position as parameter
	 * 
	 * @param p		TurtlePoint object representing new location
	 */
	public void setLocation(TurtlePoint p) {
		setLocation(p.getX(), p.getY());
	}

	/**
	 * Adjusts point location by amount specified by parameters
	 * 
	 * @param dx	x shift
	 * @param dy	y shift
	 */
	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}
}
