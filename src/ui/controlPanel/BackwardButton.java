package ui.controlPanel;

import controller.Controller2ViewAPI;
import ui.toolbar.items.CommandLanguageChooser;
/**
 * The backward button
 * @author DanSun
 *
 */
public class BackwardButton extends ControlButtonAbstract {

    private static String ourCommand = "bk 100";
    private static String ourText = "BK 100";
    public BackwardButton(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	super(controller2ViewApi, ourText, ourCommand,
		commandLanguageChooser);
    }


}
