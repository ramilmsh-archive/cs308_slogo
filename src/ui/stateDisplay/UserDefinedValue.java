package ui.stateDisplay;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is created to be used by state display
 * @author Dan Sun
 * adopted from TableView demo in Ensemble8
 */
public class UserDefinedValue {

	private StringProperty name;
	private StringProperty value;
	
	/**
	 * Constructor for UserDefinedValue
	 * @param name The name of the user getDefinition thing
	 * @param value The value of the user getDefinition thing
	 */
	public UserDefinedValue(String nameGiven, String valueGiven) {
		name = new SimpleStringProperty(nameGiven);
		value = new SimpleStringProperty(valueGiven);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty valueProperty() {
		return value;
	}
	
	

}
