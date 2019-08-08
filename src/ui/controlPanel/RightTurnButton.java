package ui.controlPanel;

import controller.Controller2ViewAPI;
import ui.toolbar.items.CommandLanguageChooser;
/**
 * The Right Turn Button
 * @author DanSun
 *
 */
public class RightTurnButton extends ControlButtonAbstract {

    private static String ourCommand = "rt 90";
    private static String ourText = "RT 90";
    public RightTurnButton(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	super(controller2ViewApi, ourText, ourCommand,
		commandLanguageChooser);
    }


}
