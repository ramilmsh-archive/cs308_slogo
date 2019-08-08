package ui.helpWindow;

import java.net.URL;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/**
 * This class implements the help window given to the user
 * How to create a new window in JavaFX learned from https://stackoverflow.com/questions/15041760/javafx-open-new-window
 * @author Dan Sun
 *
 */
public class HelpWindow extends Stage{
    private static String HELP_PAGE_PATH = "/resources/ui/help_page.html";

    Scene myScene;
    Group root;
    WebView myWebView;

    public HelpWindow() {
	root = new Group();
	myWebView = new WebView();
	this.setTitle("Command Document");
	this.setScene(new Scene(root));
	loadContent();
    }

    /**
     * adopted from WebView example in Ensemble 8
     */
    private void loadContent() { 
	final WebEngine webEngine = myWebView.getEngine();
	//load from local html page https://stackoverflow.com/questions/35703884/trying-to-load-a-local-page-into-javafx-webengine
	URL url = this.getClass().getResource(HELP_PAGE_PATH);
	webEngine.load(url.toString());
	root.getChildren().add(myWebView);
    }


}
