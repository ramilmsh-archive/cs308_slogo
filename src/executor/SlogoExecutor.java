package executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import command.CommandUnit;
import controller.channel.PubSub;
import controller.channel.messages.Execute;
import controller.channel.messages.Query;
import controller.channel.messages.Message;
import executor.commandable.pen.StandardPen;
import executor.commandable.turtle.StandardTurtle;
import interpreter.core.elements.Value;

/**
 * Receives list of commands and carries them out by querying the turtle. Also
 * keeps and maintains the list of turtles.
 * 
 * Observer help from here:
 * https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
 * 
 * @author Natalie
 *
 */
public class SlogoExecutor implements Executor {
	private static final String COMMANDABLE_METHOD = "interpretQuery";
	public static final String TURTLE_CLASS = "executor.commandable.turtle.StandardTurtle";
	public static final String PEN_CLASS = "executor.commandable.pen.StandardPen";
	public static final String RESET_METHOD = "reset";
	public static final String REFLECTION_RESOURCES = "Query";

	private List<StandardTurtle> turtleHistory = new LinkedList<StandardTurtle>();
	private List<StandardTurtle> activeTurtles = new LinkedList<StandardTurtle>();
	private StandardPen pen = new StandardPen();
	private RootObserver observer = new RootObserver();
	private int windowX;
	private int windowY;
	private PubSub pubsub;

	public SlogoExecutor(int X, int Y, PubSub p) {
		this.windowX = X/2;
		this.windowY = Y/2;
		pubsub = p;
		setup();
	}
	
	private void setup(){
		pen.addObserver(observer);
		newTurtle(1);
		pubsub.subscribeSync(PubSub.Channel.EXECUTE, this::execute);
		pubsub.subscribeSync(PubSub.Channel.QUERY, this::convert);
	}

	private Value execute(Message m){
		Execute e = (Execute) m;
		return new Value<>(calc(e.getCommand()));
	}
	
	private Value convert(Message m){
		Query q = (Query) m;
		ResourceBundle refResources = ResourceBundle.getBundle(CommandUnit.DEFAULT_RESOURCE_PACKAGE + REFLECTION_RESOURCES);
		String type = refResources.getString(q.getQuery());
		
		double[] ret = commandableRef(type, COMMANDABLE_METHOD, new Class[]{String.class}, q.getQuery());
		return new Value<>(ret);
	}

	private double[] calc(CommandUnit c){
		double[] result;
		
		if (c.getCommand().equals("tell")) {
			result = new double[1];
			result[0] = setTurtles((Double[]) c.getValue());
		} else {
			result = commandableRef(c.getCommandableType(), c.getCommand(), c.getValType(), c.getValue());
		}

		for (StandardTurtle t : activeTurtles)
			t.notifyObservers();
		pen.notifyObservers();

		return result; // return the output of the last command
	}
	
	private double setTurtles(Double[] val){
		clearTurtles();
		for (double i:val){
			if (i > turtleHistory.size())
				for (int j = turtleHistory.size(); j <= i; j++){
					newTurtle(j);
				}
			else{
				turtleHistory.get((int)i-1).active(1.0);
				activeTurtles.add(turtleHistory.get((int)i-1));
			}
		}
		
		return val[val.length-1];
	}
	
	private void clearTurtles(){
		for (StandardTurtle t : activeTurtles){
			t.deleteObservers();
			t.active(0.0);
		}
		activeTurtles.clear();
		// clears out old turtles of observers, removes from active
	}

	private void newTurtle(int dex){
		StandardTurtle turtle = new StandardTurtle(windowX, windowY, dex);
		turtle.addObserver(observer);
		turtleHistory.add(turtle);
		activeTurtles.add(turtle);
	}

	@Override
	public void addObserver(Observer obs) {
		observer.addObserver(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		observer.removeObserver(obs);
	}

	private double[] commandableRef(String commandableType, String method, Class[] parameterTypes, Object... params){
		double[] result;
	
		try {
			Class<?> c = Class.forName(getClassName(commandableType));
			Method m = getDeclaredMethod(c, method, parameterTypes);
			if (commandableType.equals("Turtle")){
				result = new double[activeTurtles.size()];
				for (int i = 0; i < activeTurtles.size(); i++){
					result[i] = invoke(m, activeTurtles.get(i), params);
				}
			}
			else{
				result = new double[1];
				result[0] = invoke(m, pen, params);
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException("Bad input configuration", e);
		}

		return result;
	}
	
	private String getClassName(String type){
		return type.equals("Turtle")? TURTLE_CLASS:PEN_CLASS;
	}
	
	private Method getDeclaredMethod(Class c, String method, Class[] parameterTypes) throws NoSuchMethodException, SecurityException{
		return parameterTypes.length==0? c.getDeclaredMethod(method):c.getDeclaredMethod(method, parameterTypes);
	}
	
	private double invoke(Method m, Object o, Object[] params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return params.length==0? (double) m.invoke(o): (double)m.invoke(o, params);
	}

	@Override
	public double[] reset() {
		// erases history, but observers remain

		double[] ret = commandableRef(TURTLE_CLASS, RESET_METHOD, new Class[] { double.class }, new Object[] { 0 });
		commandableRef(PEN_CLASS, RESET_METHOD, new Class[] { double.class }, new Object[] { 0 });
		
		clearTurtles();
		newTurtle(turtleHistory.size() + 1);

		return ret;
	}

}
