package command;

import java.util.ResourceBundle;

/**
 * 
 * Packages commands from interpreter and translates 
 * into the appropriate turtle/pen methods from resources
 * file so that executor can call them using reflection.
 * 
 * @author Natalie
 *
 */
public class CommandUnit implements Command {
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.executor/";
    public static final String REFLECTION_RESOURCES = "Commandable";
    public static final String[] CHANGE_VAL = {"Home", "ID", "Backward", "Right", "Home"};
    public static final String[] CHANGE_VAL_VIS = {"ShowTurtle", "HideTurtle", "PenDown", "PenUp"};

    private ResourceBundle refResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + REFLECTION_RESOURCES);
    private String command;
    private Double[] value;

    public CommandUnit(String comm, Double... val) {
        command = comm;
        value = val;
        manip();
    }

    /**
     * Turns user command into turtle method string, adjusts values for select
     * commands
     *
     * @param val		value associated with command-- either 1 num for double or 2 for point
     */
    private void manip() {
        manipVal();
        command = refResources.getString(command);
    }

    /**
     * Creates point if necessary, adjusts values for select commands
     *
     * @param val		value associated with command-- either 1 num for double or 2 for point
     */
    private void manipVal() {
        if (command.equals(CHANGE_VAL[0]) || command.equals(CHANGE_VAL[1]))
            value = new Double[]{};
        if (command.equals(CHANGE_VAL[2]) || command.equals(CHANGE_VAL[3]))
            for (int i = 0; i < value.length; i++)
                value[i] *= -1;
        if (command.equals(CHANGE_VAL[4]))
            value = new Double[] {0.0,0.0};
        
        if (command.equals(CHANGE_VAL_VIS[0]) || command.equals(CHANGE_VAL_VIS[2]))
        	value = new Double[]{1.0};
        if (command.equals(CHANGE_VAL_VIS[1]) || command.equals(CHANGE_VAL_VIS[3]))
        	value = new Double[]{0.0};
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Object[] getValue() {
        return value;
    }

    @Override
    public Class[] getValType() {
        Class[] className = new Class[value.length];
        for (int i = 0; i < value.length; i++)
            className[i] = Double.class;
        return className;
    }

    @Override
    public String getCommandableType(){
        return refResources.getString(command);
    }
}
