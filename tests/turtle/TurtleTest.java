package turtle;

import org.junit.Test;

import executor.commandable.pen.StandardPen;
import executor.commandable.turtle.StandardTurtle;
import junit.framework.TestCase;

/**
 * Tests Turtle methods
 * 
 * @author Natalie
 *
 */
public class TurtleTest extends TestCase {
	private ObserverTest o = new ObserverTest();

	@Test
	public void testMove() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		s.addObserver(o);

		s.move(10.0);
		s.notifyObservers();
		assertEquals("Forward move, no angle", o.getX(), 0, 10e-4);
		assertEquals(o.getY(), -10, 10e-4);
		s.move(-20.0);
		s.notifyObservers();
		assertEquals("Backward move, no angle", o.getX(), 0, 10e-4);
		assertEquals(o.getY(), 10, 10e-4);

		s.move(0.0,0.0);
		s.turn(30.0);
		s.move(10.0);
		s.notifyObservers();
		assertEquals("Turn, 2nd quadrant y", o.getY(), -10 * Math.cos(Math.PI / 6), 10e-4);
		assertEquals("Turn, 2nd quadrant x", o.getX(), -10 * Math.sin(Math.PI / 6), 10e-4);

		s.reset();
		s.turn(-30.0);
		s.move(10.0);
		s.notifyObservers();
		assertEquals("Turn, 1st quadrant y", o.getY(), -10 * Math.cos(Math.PI / 6), 10e-4);
		assertEquals("Turn, 1st quadrant x", o.getX(), 10 * Math.sin(Math.PI / 6), 10e-4);

		s.reset();
		s.turn(150.0);
		s.move(10.0);
		s.notifyObservers();
		assertEquals("Turn, 3rd quadrant y", o.getY(), 10 * Math.cos(Math.PI / 6), 10e-4);
		assertEquals("Turn, 3rd quadrant x", o.getX(), -10 * Math.sin(Math.PI / 6), 10e-4);

		s.reset();
		s.turn(210.0);
		s.move(10.0);
		s.notifyObservers();
		assertEquals("Turn, 4th quadrant y", o.getY(), 10 * Math.cos(Math.PI / 6), 10e-4);
		assertEquals("Turn, 4th quadrant x", o.getX(), 10 * Math.sin(Math.PI / 6), 10e-4);

		assertTrue("Valid return, positive", s.move(10.0) == 10);
		assertTrue("Valid return, negative", s.move(-10.0) == -10);
		assertTrue("Valid return, 0", s.move(0.0) == 0);
	}

	@Test
	public void testTurn() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		s.addObserver(o);

		s.turn(30.0);
		s.notifyObservers();
		assertTrue("Basic, 1Q", o.getAngle() == 30);

		s.turn(-60.0);
		s.notifyObservers();
		assertTrue("Basic, 4Q", o.getAngle() == 330);

		s.turn(150.0);
		s.notifyObservers();
		assertTrue("Basic, 2Q", o.getAngle() == 120);

		s.turn(90.0);
		s.notifyObservers();
		assertTrue("Basic, 3Q", o.getAngle() == 210);

		s.absTurn(0.0);

		s.turn(60.0);
		s.turn(360.0);
		s.notifyObservers();
		assertTrue("Over 360", o.getAngle() == 60);

		s.turn(-120.0);
		s.notifyObservers();
		assertTrue("Negative angle", o.getAngle() == 300);

		s.absTurn(0.0);
		s.notifyObservers();

		assertTrue("Valid return, positive", s.turn(60.0) == 60);
		assertTrue("Valid return, negetive", s.turn(-60.0) == -60);
		assertTrue("Valid return, 0", s.turn(0.0) == 0);
	}

	@Test
	public void testHeading() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		s.addObserver(o);

		s.absTurn(90.0);
		s.notifyObservers();
		assertTrue("Basic", o.getAngle() == 90);
		assertTrue("Valid return, positive", s.absTurn(60.0) == -30);
		assertTrue("Valid return, negetive", s.absTurn(90.0) == 30);
		assertTrue("Valid return, 0", s.absTurn(90.0) == 0);

		s.absTurn(380.0);
		s.notifyObservers();
		assertTrue("Over 360", o.getAngle() == 20);

		s.absTurn(-60.0);
		s.notifyObservers();
		assertTrue("Negative", o.getAngle() == 300);

		s.absTurn(380.0);
		s.notifyObservers();
		assertTrue("Extra return check", s.absTurn(0.0) == -20);
	}

	@Test
	public void testTowards() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		s.addObserver(o);

		s.absTurn(10 * Math.cos(Math.PI / 6), 10 * Math.sin(Math.PI / 6));
		s.notifyObservers();
		assertEquals("basic", o.getAngle(), 240, 10e-4);
		assertEquals("return check", s.absTurn(0.0, 1.0), -60, 10e-4);
	}

	@Test
	public void testVisChange() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		StandardPen p = new StandardPen();
		s.addObserver(o);
		p.addObserver(o);

		p.penVis(0.0);
		p.notifyObservers();
		assertTrue("basic pen", o.getPVis() == 0);
		p.penVis(1.0);
		p.notifyObservers();
		assertTrue("basic pen", o.getPVis() == 1);
		p.penVis(1.0);
		p.notifyObservers();
		assertTrue("nonchange", o.getPVis() == 1);
		assertTrue("return check", p.penVis(0.0) == 0);

		s.turtleVis(0.0);
		s.notifyObservers();
		assertTrue("basic turtle", o.getTVis() == 0);
		s.turtleVis(1.0);
		s.notifyObservers();
		assertTrue("basic turtle", o.getTVis() == 1);
		s.turtleVis(1.0);
		s.notifyObservers();
		assertTrue("nonchange", o.getTVis() == 1);
		assertTrue("return check", s.turtleVis(0.0) == 0);
	}

	@Test
	public void home() {
		StandardTurtle s = new StandardTurtle(200, 200, 1);
		s.addObserver(o);

		s.move(10.0);
		s.reset();
		s.notifyObservers();
		assertTrue("basic", o.getX() == 0 && o.getY() == 0);

		s.move(-20.0);
		s.notifyObservers();
		assertTrue("return check", s.reset() == 20);
	}
}
