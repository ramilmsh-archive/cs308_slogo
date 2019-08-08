package ui.toolbar.items;

import controller.Controller2ViewAPI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CommandLanguageChooser extends ToolBarItemAbstract {

    private static final String DEFAULT_LANGUAGE = "English";
    private ComboBox<String> myComboBox;
    private Controller2ViewAPI myController2ViewApi;
    private String[] supportedLanguages = new String[] {
	    "Chinese",
	    "English",
	    "French",
	    "German",
	    "Italian",
	    "Portuguese",
	    "Russian",
	    "Spanish"
    };

    public CommandLanguageChooser(Controller2ViewAPI controller2ViewApi) {
	myController2ViewApi = controller2ViewApi;
	myText = new Label("Command Language");
	createCommandLanguageSelectionBox();
	combineNodeWithLabelIntoLayout(myComboBox);
    }
    /**
     * Getter for current command language
     * @return a string specifying the current language
     */
    public String getLanguage() {
	return myComboBox.getValue();
    }

    /**
     * adopted from https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
     * @return The top level node for selecting command language
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void createCommandLanguageSelectionBox() {
	myComboBox = new ComboBox<String>();
	myComboBox.getItems().addAll(supportedLanguages);
	myComboBox.setValue(DEFAULT_LANGUAGE);
	myComboBox.setOnAction(new EventHandler() {
	    @Override public void handle(Event t) {
		String language = (String) myComboBox.getValue();
		myController2ViewApi.changeLanguage(language);
	    }
	});

    }
}
