import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author cy122
 *
 * This class is used to start the program. It should be as neat as possible, in order to emphasize its only one responsibility.
 *
 */
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller controller = new Controller(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
