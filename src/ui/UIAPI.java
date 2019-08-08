package ui;

import ui.commandOutput.CommandOutputInterface;
import ui.stateDisplay.StateDisplayInterface;
import ui.turtleGraphics.TurtleViewerInterface;


/**
 * This interface defines the methods that should be implemented 
 * by the View for the controller to use
 * @author cy122
 * @author Dan Sun
 *
 */
public interface UIAPI extends TurtleViewerInterface, 
StateDisplayInterface, CommandOutputInterface{
}
