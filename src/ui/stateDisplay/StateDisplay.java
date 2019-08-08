package ui.stateDisplay;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.util.UIComponent;

/**
 * This class displays variables and user-getDefinition functions
 * @author Dan Sun
 *
 */
public class StateDisplay implements StateDisplayInterface, UIComponent{
    private static String NAME_COLUMN_TEXT = "Name";
    private static String VALUE_COLUMN_TEXT = "Value";
    //private fields
    private int myDisplayWidth;
    private int myDisplayHeight;
    private TableView<UserDefinedValue> root;
    private ObservableList<UserDefinedValue> myData; 
    private ArrayList<UserDefinedValue> myVariables;
    private ArrayList<UserDefinedValue> myCommands;

    public StateDisplay(int displayWidth, int displayHeight) {
	myDisplayWidth = displayWidth;
	myDisplayHeight= displayWidth;
	myData = FXCollections.observableArrayList();
	myVariables = new ArrayList<>();
	myCommands = new ArrayList<>();
	createDisplay();

    }

    @Override
    public Node getTopLevelNode() {
	return root;
    }

    @Override
    public void updateVariables(Map<String, String> variables) {
	myVariables.clear();
	//iterate through a map adopted from https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
	for(Entry<String, String> entry: variables.entrySet()) {
	    String variableName = entry.getKey();
	    String variableValue = entry.getValue();
	    myVariables.add(new UserDefinedValue(variableName, variableValue));
	}
	refreshDisplay();
    }

    @Override
    public void updateUserMethods(Map<String, String> methods) {
	myCommands.clear();
	//iterate through a map adopted from https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
	for(Entry<String, String> entry: methods.entrySet()) {
	    String commandName = entry.getKey();
	    String commandContent = entry.getValue();
	    myCommands.add(new UserDefinedValue(commandName, commandContent));
	}
	refreshDisplay();
	
    }
    
    private void refreshDisplay() {
	myData.clear();
	myData.addAll(myVariables);
	myData.addAll(myCommands);
    }

    /**
     * example adopted from TableView example in Ensemble8
     * possibly uses TableVellFactory in Ensemble8 as well
     */
    @SuppressWarnings("unchecked")
    private void createDisplay() {
	TableColumn<UserDefinedValue, String> nameColumn = new TableColumn<UserDefinedValue, String>();
	nameColumn.setText(NAME_COLUMN_TEXT);
	nameColumn.setCellValueFactory(new PropertyValueFactory<UserDefinedValue, String>("name"));
	TableColumn<UserDefinedValue, String> valueColumn = new TableColumn<UserDefinedValue, String>();
	valueColumn.setText(VALUE_COLUMN_TEXT);
	valueColumn.setCellValueFactory(new PropertyValueFactory<UserDefinedValue, String>("value"));
	root = new TableView<UserDefinedValue>();
	//		root.setPrefWidth(myDisplayWidth);
	root.setPrefSize(myDisplayWidth, myDisplayHeight);
	//		root.setPrefHeight(myDisplayHeight);
	root.setItems(myData);
	root.getColumns().addAll(nameColumn, valueColumn);
	root.setPlaceholder(new Label("")); //Do not show default message, adopted from https://stackoverflow.com/questions/24765549/remove-the-default-no-content-in-table-text-for-empty-javafx-table
	//binding, adopted from https://stackoverflow.com/questions/10152828/javafx-2-automatic-column-width/10152992#10152992
	nameColumn.prefWidthProperty().bind(root.widthProperty().divide(3)); 		
	valueColumn.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2)); 	
	root.setPrefHeight(myDisplayHeight);
//	root.setMaxHeight(myDisplayHeight);
	root.setMinHeight(myDisplayHeight);

    }





}
