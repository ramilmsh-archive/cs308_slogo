package turtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import command.CommandUnit;
import controller.channel.PubSub;
import controller.channel.messages.Execute;
import controller.channel.messages.Message;
import controller.channel.messages.Query;
import executor.SlogoExecutor;
import junit.framework.TestCase;

/**
 * Tests observation
 * 
 * @author Natalie
 *
 */
public class ObserverTest extends TestCase implements Observer {
	private double angle;
	private double x;
	private double y;
	private int pVis;
	private int tVis;
	private int resetNum;

	@Test
	public void testMultiple(){
		SlogoExecutor sE = new SlogoExecutor(200,200,new PubSub());
		sE.addObserver(this);
		
		Execute e = new Execute(new CommandUnit("Forward", 10.0));
		sE.execute(e);
		
		e = new Execute(new CommandUnit("Tell", 1.0, 2.0));
		sE.execute(e);
		Message m = new Query("YCoordinate");
		double[] ycoors = (double[]) sE.convert(m).getValue();

		assertEquals("query check1 ", ycoors[0], -10.0);
		assertEquals("query check 2", ycoors[1], 0.0);
	}
	
	@Test
	public void testObserve() {
		SlogoExecutor sE = new SlogoExecutor(200, 200, new PubSub());
		sE.addObserver(this);

		Execute e = new Execute(new CommandUnit("Forward", 10.0));
		sE.execute(e);
		assertEquals("move check", y, -10.0);
		
		e = new Execute(new CommandUnit("Left", 30.0));
		sE.execute(e);
		assertEquals("turn check", angle, 30.0);
		
		e = new Execute(new CommandUnit("PenDown", 1.0));
		sE.execute(e);
		assertEquals("pVis check", pVis, 1);
		
		e = new Execute(new CommandUnit("HideTurtle", 0.0));
		sE.execute(e);
		assertEquals("tVis check", tVis, 0);
		
		e = new Execute(new CommandUnit("ClearScreen"));
		sE.execute(e);
		assertEquals("reset check", resetNum, 1);
		
		e = new Execute(new CommandUnit("ID"));
		double[] id = (double[]) sE.execute(e).getValue();
		assertEquals("id check", id[0], 1.0);

		e = new Execute(new CommandUnit("Tell", new Double[]{2.0}));
		id = (double[]) sE.execute(e).getValue();
		assertEquals("id check 2", id[0], 2.0);

		e = new Execute(new CommandUnit("SetPosition", 3.0, 5.0));
		sE.execute(e);
		assertEquals("position check x", x, 3.0);
		assertEquals("position check y", y, 5.0);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Map<String, Double> s = (HashMap) arg1;

		for (String key : s.keySet()) {
			parse(key, s.get(key));
		}
	}

	private void parse(String s, double val) {
		switch (s) {
		case "x coord":
			x = val;
			break;
		case "y coord":
			y = val;
			break;
		case "angle":
			angle = val;
			break;
		case "pVis":
			pVis = (int) val;
			break;
		case "tVis":
			tVis = (int) val;
			break;
		case "reset":
			resetNum++;
			break;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAngle() {
		return angle;
	}

	public int getPVis() {
		return pVis;
	}

	public int getTVis() {
		return tVis;
	}
}
