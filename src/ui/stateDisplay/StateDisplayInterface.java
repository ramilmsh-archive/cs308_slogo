package ui.stateDisplay;

import java.util.Map;
/**
 * This interface specifies functions needs to be accomplished
 * by the StateDisplay class
 * @author Dan Sun
 * @author cy122
 */
public interface StateDisplayInterface {
    
    /**
     * update the variable lists to the user
     * @param variables
     */
    public void updateVariables(Map<String, String> variables);

    /**
     * update the user-getDefinition method names to the user
     * @param variables
     */
    public void updateUserMethods(Map<String, String> methods);
}
