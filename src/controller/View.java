package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ui.MainUI;
import ui.UIAPI;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import controller.channel.PubSub;
import controller.channel.messages.FunctionUpdate;
import controller.channel.messages.Interpret;
import controller.channel.messages.LanguageChange;
import controller.channel.messages.VariableLoad;
import controller.channel.messages.VariableUpdate;
import controller.file.Reader;
import controller.file.WorkspaceLoader;
import controller.file.WorkspaceSaver;
import executor.commandable.turtle.StandardTurtle;

/**
 * 
 * @author cy122
 * 
 * This class is to encapsulate the information from the back-end, reorganize it and pass the information to front-end.
 * A type of glue code to glue the front-end with back-end.
 *
 */

public class View implements Controller2ViewAPI, Observer{
	private MainUI mainUI;
	private PubSub pubsub;
	private Controller controller;
	private Map<PubSub, Map<String, String>> variables = new HashMap<PubSub, Map<String, String>>();
	private Map<PubSub, Map<String, String>> functions = new HashMap<PubSub, Map<String, String>>();
	private Vector<PubSub> pubsubs = new Vector<PubSub>();
	
	public View(Stage primaryStage, Controller controller, Reader reader) {
		mainUI = new MainUI(primaryStage, (Controller2ViewAPI)this, reader);
		this.controller=controller;
		createWorkspace();
		primaryStage.setScene(((MainUI) mainUI).getScene());
        primaryStage.show();       
	}

	@Override
    public void update(Observable o, Object arg) {
        Map<String, Double> turtleStatus = new HashMap<String, Double>((Map) arg);
        if (turtleStatus.containsKey("bad input")) {
            mainUI.showError("wrong command!");
        } else if (turtleStatus.containsKey("reset")) {
            mainUI.clearScreen();
        } else {
            if (turtleStatus.containsKey("angle")) {
            	int turtleID=new Double(((StandardTurtle)o).getID()).intValue();
            	mainUI.rotateTurtleTo(turtleID, 360 - turtleStatus.get("angle"));
            }
            if ((turtleStatus.containsKey("x coord")) && (turtleStatus.containsKey("y coord"))) {
            	int turtleID=new Double(((StandardTurtle)o).getID()).intValue();
            	double coordinateX = turtleStatus.get("x coord");
                double coordinateY = turtleStatus.get("y coord");
                mainUI.moveTurtleTo(turtleID,coordinateX, coordinateY);
            }
            if (turtleStatus.containsKey("pVis")) {
                if (turtleStatus.get("pVis") == 0.0) {
                    mainUI.setPenDown(false);
                } else {
                    mainUI.setPenDown(true);
                }
            }
            if (turtleStatus.containsKey("tVis")) {
            	int turtleID=new Double(((StandardTurtle)o).getID()).intValue();
            	if (turtleStatus.get("tVis") == 0.0) {
                    mainUI.setTurtleVisibility(turtleID,false);
                } else {
                    mainUI.setTurtleVisibility(turtleID,true);
                }
            }
            if(turtleStatus.containsKey("palette")){
            	double value = turtleStatus.get("palette");
            	Double index = value/(256*256*256);
            	String colorString = String.format("#%06X", value%(256*256*256));
            	mainUI.setPalette(index.intValue(), Color.web(colorString));
            }
            if(turtleStatus.containsKey("background")){
            	mainUI.setBackgroundColor(turtleStatus.get("background").intValue());
            }
            if(turtleStatus.containsKey("pensize")){
            	mainUI.setPenSize(turtleStatus.get("pensize").intValue());
            }
            if(turtleStatus.containsKey("pencolor")){
            	mainUI.setPenColor(turtleStatus.get("pencolor").intValue());
            }
            if(turtleStatus.containsKey("shape")){
            	mainUI.setTurtleImage(turtleStatus.get("shape").intValue());
            }
            if(turtleStatus.containsKey("active")){
            	int turtleID=new Double(((StandardTurtle)o).getID()).intValue();
            	Boolean flag=true;
            	if(turtleStatus.get("active")==1.0){
            		flag=mainUI.setActive(turtleID, true);
            	}else{
            		flag=mainUI.setActive(turtleID, false);
            	}
            	if(flag==false){
            		mainUI.createTurtle(turtleID);
            	}
            }
        }
    }

	@Override
	public void runCommand(String command) {
		pubsub.publish(PubSub.Channel.INTERPRET, new Interpret(command));
	}

	@Override
	public void changeLanguage(String language) {
		pubsub.publish(PubSub.Channel.LANGUAGE_CHANGE, new LanguageChange(language));
	}
	
	public void updateVariables(VariableUpdate variable){
		this.variables.get(pubsub).put(variable.key, variable.value);
		mainUI.updateVariables(variables.get(pubsub));
	}
	
	public void updateFunctions(FunctionUpdate function){
		this.functions.get(pubsub).put(function.key, function.value);
		mainUI.updateUserMethods(variables.get(pubsub));
	}

	@Override
	public void createWorkspace() {
		PubSub newPubSub = new PubSub();
		pubsub=newPubSub;
		pubsubs.add(newPubSub);
		functions.put(pubsub, new HashMap<String,String>());
		variables.put(pubsub, new HashMap<String,String>());
		pubsub.subscribe(PubSub.Channel.FUNCTION_UPDATE, e -> updateFunctions((FunctionUpdate)e));
		pubsub.subscribe(PubSub.Channel.VARIABLE_UPDATE, e -> updateVariables((VariableUpdate)e));
		controller.createBackEnd(this,newPubSub);
	}

	@Override
	public void switchToWorkSpace(int index) {
	    pubsub = pubsubs.get(index);
	}

	@Override
	public void save(String absolutePath) {
		new WorkspaceSaver(absolutePath).saveVariables(variables.get(pubsub));
		new WorkspaceSaver(absolutePath).saveMethods(functions.get(pubsub));
	}

	@Override
	public void load(String absolutePath) {
		Map<String, String> variables = new WorkspaceLoader(absolutePath).loadVariables();
		Map<String, String> functions = new WorkspaceLoader(absolutePath).loadMethods();
		this.variables.put(pubsub, variables);
		this.functions.put(pubsub, functions);
		pubsub.publish(PubSub.Channel.VARIABLE_LOAD, new VariableLoad(functions, variables));
	}
}
