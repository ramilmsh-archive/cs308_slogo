package controller;

import controller.channel.PubSub;
import controller.file.Reader;
import executor.SlogoExecutor;
import interpreter.Interpreter;
import javafx.stage.Stage;

import java.util.*;

/**
 * @author cy122
 * <p>
 * This class is for passing the information between the front-end and back-end.
 * The advantage of doing this is to prevent them from directly getting each other, and to encapsulate the function of front-end and back-end.
 */
public class Controller{

    private Vector<Interpreter> interpreters = new Vector<Interpreter>();
    private Vector<SlogoExecutor> executors = new Vector<SlogoExecutor>();
    private Reader reader = new Reader(Reader.getDefaultPath());

    public Controller(Stage primaryStage) {
    	new View(primaryStage, this, reader);
    }

	public void createBackEnd(Observer view, PubSub currentPubSub) {
        Interpreter interpreter = new Interpreter(currentPubSub);
        SlogoExecutor executor = new SlogoExecutor(Reader.getScreenWidth(), Reader.getScreenHeight(), currentPubSub);
        executor.addObserver(view);
        interpreters.add(interpreter);
        executors.add(executor);
	}
}
