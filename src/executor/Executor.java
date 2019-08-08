package executor;

import java.util.Observer;

/**
 * API for Executor. Carries out commands, maintains turtle list
 * 
 * @author Natalie
 *
 */
public interface Executor {

	/**
	 * Carries out commands that user enters
	 *
	 * @param c		packaged commands
	 * @return resulting value associated with commands
	 */
	//public double execute(Iterator<CommandUnit> commands);

	/**
	 * Clears locally stored data
	 *
	 * @return distance moved by turtle(s) to get back to center
	 */
	public double[] reset();

	/**
	 * Replaces current turtle with a new turtle
	 */
	//public void newTurtle();

	/**
	 * Adds observer to list of classes observing Turtle
	 * 
	 * @param obs		Observer to be added
	 */
	public void addObserver(Observer obs);

	/**
	 * Removes observer from list of classes observing Turtle
	 * 
	 * @param obs		Observer to be removed
	 */
	public void removeObserver(Observer obs);
	
	//public void addTurtle(int[] val);
}
