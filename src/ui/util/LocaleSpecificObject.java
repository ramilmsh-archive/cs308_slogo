package ui.util;

import java.util.ResourceBundle;

/**
 * This class is the super class for all objects
 * the are locale specfic
 * @author DanSun
 *
 */
public class LocaleSpecificObject {
    protected ResourceBundle myResourceBundle;
    
    /**
     * Constructor for the class
     * @param resourceBundle The resource bundle to be used
     */
    public LocaleSpecificObject(ResourceBundle resourceBundle) {
	myResourceBundle = resourceBundle;
    }

}
