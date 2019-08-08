package executor.commandable.turtle;

import java.util.HashMap;
import java.util.Observable;

import executor.commandable.Commandable;

import java.util.Map;

/**
 * Observable code found here:
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Observable.java#Observable.notifyObservers%28java.lang.Object%29
 * 
 * Also used: https://docs.oracle.com/javase/7/docs/api/java/util/Observer.html
 * 
 * Implementation of the Turtle API Controls turtle movement and visibility
 * 
 * @author Natalie
 *
 */
public class StandardTurtle extends Observable implements Turtle, Commandable {
	private static final int HALF_CIRCLE = 180;
	public static final int FULL_CIRCLE = 360;
	public static final int ANGLE_ADJUST = 90;
	public static final double STARTING_ANGLE = 0;
	public static final int STARTING_TVIS = 1;
	public static final int STARTING_SHAPE = 0;
	public static final int STARTING_ACTIVE = 1;
	public static final int OFFSET = 80;
	
	private int max_x;
	private int max_y;
	private int id;

	private TurtlePoint coords = new TurtlePoint(0, 0);
	private double angle = STARTING_ANGLE;
	private int turtleVis = STARTING_TVIS;
	private int turtleShape = STARTING_SHAPE;
	private int active = STARTING_ACTIVE;
	private Map<String, Double> changes = new HashMap<String, Double>();

	public StandardTurtle(int X, int Y, int id) {
		max_x = X;
		max_y = Y-OFFSET;
		this.id = id;
	}
	
	@Override
	public double interpretQuery(String query){
		switch(query){
		case "XCoordinate":
			return coords.getX();
		case "YCoordinate":
			return coords.getY();
		case "Heading":
			return angle;
		case "Showing":
			return turtleVis;
		case "Shape":
			return turtleShape;
		}
		return 0;
	}
	
	private void checkValid(String s, double val) {
		if ((!(turtleVis == 0) && !(turtleVis == 1)) ||
				(!(active==0) && !(active==1))) {
			changes.put("bad input", 0.0);
			setChanged();
			return;
		}
		if (s.equals("coords")) {
			changes.put("x coord", coords.getX());
			changes.put("y coord", coords.getY());
		} else {
			changes.put(s, val);
		}

		setChanged();
	}

	@Override
	public double move(Double dist) {
		double x = dist * Math.cos(Math.toRadians(angle + ANGLE_ADJUST));
		double y = -dist * Math.sin(Math.toRadians(angle + ANGLE_ADJUST));

		coords.translate(x, y);

		fixCoords();
		checkValid("coords", 0.0);

		return dist;
	}

	@Override
	public double move(Double d1, Double d2) {
		TurtlePoint p = new TurtlePoint(d1, d2);
		double moved = coords.distance(p);
		coords.setLocation(p);
		
		fixCoords();
		checkValid("coords", 0.0);

		return moved;
	}

	@Override
	public double turn(Double degrees) {
		angle += degrees;
		fixAngle();
		checkValid("angle", angle);
		return degrees;
	}

	@Override
	public double absTurn(Double degrees) {
		double turned = degrees - angle;
		angle = degrees;
		fixAngle();
		checkValid("angle", angle);

		return turned;
	}

	@Override
	public double absTurn(Double d1, Double d2) {
		TurtlePoint p = new TurtlePoint(d1, d2);
		double angle = Math.toDegrees(Math.asin(-(p.getY() - coords.getY()) / (coords.distance(p))));

		if (p.getX() < coords.getX())
			angle = HALF_CIRCLE - angle;
		if (angle < 0)
			angle = FULL_CIRCLE + angle;
		
		return absTurn(angle-90);
	}

	private void fixAngle() {
		if (angle < 0)
			angle = FULL_CIRCLE + angle;
		if (angle >= FULL_CIRCLE)
			angle = angle - FULL_CIRCLE;
	}
	
	private void fixCoords(){
		if (coords.getX()>max_x)
			coords.setLocation(max_x, coords.getY());
		if (coords.getX()<-max_x)
			coords.setLocation(-max_x, coords.getY());
		if (coords.getY()>max_y)
			coords.setLocation(coords.getX(), max_y);
		if (coords.getY()<-max_y)
			coords.setLocation(coords.getX(), -max_y);
	}

	@Override
	public double turtleVis(Double vis) {
		turtleVis = vis.intValue();
		checkValid("tVis", turtleVis);
		return turtleVis;
	}

	@Override
	public double reset() {
		angle = STARTING_ANGLE;
		turtleVis = STARTING_TVIS;
		changes.put("reset", 0.0);
		setChanged();
		return move(0.0,0.0);
	}

	@Override
	public double getID() {
		return (double) id;
	}

	@Override
	public double turtleShape(Double index){
		turtleShape = index.intValue();
		checkValid("shape", turtleShape);
		return turtleShape;
	}
	
	@Override
	public double palette(Double index, Double r, Double g, Double b){
		checkValid("palette", index*Math.pow(256, 3) + r*256*256 + g*256 + b);
		return index;
	}
	
	@Override
	public double background(Double index){
		checkValid("background", index);
		return index;
	}
	
	@Override
	public double active(Double a){
		active = a.intValue();
		checkValid("active", active);
		return active;
	}
	
	@Override
	public void notifyObservers() {
		notifyObservers(new HashMap<String, Double>(changes));
	}

	@Override
	protected synchronized void clearChanged() {
		super.clearChanged();
		changes.clear();
	}

}
