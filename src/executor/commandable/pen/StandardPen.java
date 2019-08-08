package executor.commandable.pen;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import executor.commandable.Commandable;

/**
 * Class for pen object, holds and maintains pen values
 * Has observers, which are informed of pen changes
 * 
 * @author Natalie
 *
 */
public class StandardPen extends Observable implements Pen, Commandable{
	public static final int STARTING_PVIS = 0;
	public static final int STARTING_PCOLOR = 0;
	
	private int penVis = STARTING_PVIS;
	private int penColor = STARTING_PCOLOR;
	private Map<String, Double> changes = new HashMap<String, Double>();
	
	@Override
	public double interpretQuery(String query){
		switch(query){
		case "PenDown":
			return penVis;
		case "PenColor":
			return penColor;
		}
		return 0;
	}
	
	private void checkValid(String s, double val) {
		if (!(penVis == 0) && !(penVis == 1)) {
			changes.put("bad input", 0.0);
			setChanged();
			return;
		}
		else {
			changes.put(s, val);
		}

		setChanged();
	}
	
	@Override
	public double penVis(Double vis) {
		penVis = vis.intValue();
		checkValid("pVis", penVis);
		return vis;
	}
	
	@Override
	public double penColor(Double index){
		penColor = index.intValue();
		checkValid("pencolor", penColor);
		return index;
	}
	
	@Override
	public double penSize(Double pixels){
		checkValid("pensize", pixels);
		return pixels;
	}
	
	@Override
	public double reset(){
		penVis = STARTING_PVIS;
		penColor = STARTING_PCOLOR;
		changes.put("reset", 0.0);
		setChanged();
		return penColor;
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
