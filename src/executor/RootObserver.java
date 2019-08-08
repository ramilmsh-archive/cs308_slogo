package executor;

import java.util.HashSet;
import java.util.Set;
import java.util.Observable;
import java.util.Observer;

/**
 * Master observer class, is the only class that directly observes Turtle. If
 * other classes want to observe Turtle they must go through this class. This
 * ensures that if the user creates a new Turtle, or changes the active
 * Turtle(s), the observers don't all have to add themselves to the new Turtle
 * and/or delete themselves from the old Turtle. All other Observers experience
 * the Observation process as though they were actually watching the turtles.
 * 
 * @author Natalie
 *
 */
public class RootObserver implements Observer {
	private Set<Observer> observers = new HashSet<Observer>();
	
	@Override
	public void update(Observable arg0, Object arg1) {
		for (Observer o : observers)
			o.update(arg0, arg1);
	}

	/**
	 * @param obs		observer to add
	 */
	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	/**
	 * @param obs		observer to remove
	 */
	public void removeObserver(Observer obs) {
		observers.remove(obs);
	}

}
