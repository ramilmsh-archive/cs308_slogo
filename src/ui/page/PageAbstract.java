package ui.page;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Tab;

/**
 * This class defines an abstract page object
 * It should be used by the TabPane in the UI
 * @author Dan Sun
 *
 */
public abstract class PageAbstract{

    private static final String propertiesFile = "resources/ui/PageLabelsBundle";
    	
    protected Tab myTab;
    protected ResourceBundle myResourceBundle;
    
    	/**
    	 * Constructor for the class
    	 * 
    	 * @param resourceBundle The resource bundle used
    	 * @param tabTextKey Key for the text of the tab of this page
    	 */
	public PageAbstract(Locale locale, String tabTextKey) {
	    myResourceBundle = ResourceBundle.getBundle(propertiesFile, locale);
	    //	    adopted from https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TabPane.html
	    myTab = new Tab();
	    myTab.setText(myResourceBundle.getString(tabTextKey));
	}
	
	/**
	 * Getter method for accessing the tab in this object
	 * @return The tab contained in this object
	 */
	public Tab getTab() {
	    return myTab;
	}

}
