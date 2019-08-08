package ui.controlPanel;

import controller.Controller2ViewAPI;
import ui.toolbar.items.CommandLanguageChooser;
/**
 * The Left Turn Button
 * @author DanSun
 *
 */
public class LeftTurnButton extends ControlButtonAbstract {

    private static String ourCommand = "lt 90";
    private static String ourText = "LT 90";
    public LeftTurnButton(Controller2ViewAPI controller2ViewApi,
	    CommandLanguageChooser commandLanguageChooser) {
	super(controller2ViewApi, ourText, ourCommand,
		commandLanguageChooser);
    }


}
