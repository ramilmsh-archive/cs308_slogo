package ui.controlPanel;

import controller.Controller2ViewAPI;
import ui.toolbar.items.CommandLanguageChooser;

/**
 * The forward button
 * @author DanSun
 *
 */
public class ForwardButton extends ControlButtonAbstract {

    private static String ourCommand = "fd 100";
    private static String ourText = "FD 100";
    public ForwardButton(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	super(controller2ViewApi, ourText, ourCommand,
		commandLanguageChooser);
    }


}
