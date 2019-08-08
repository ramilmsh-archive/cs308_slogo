package ui.menuBar;

import java.util.Locale;
import java.util.ResourceBundle;

import ui.controlPanel.ControlPanel;
import ui.helpWindow.HelpWindow;
import ui.menu.UiMenuHelp;
import ui.menu.UiMenuView;
import ui.menuItem.CommandDocumentItem;
import ui.menuItem.CommandInputViewCheckMenuItem;
import ui.menuItem.CommandOutputViewCheckMenuItem;
import ui.menuItem.ControlPanelViewCheckMenuItem;
import ui.menuItem.StateDisplayViewCheckMenuItem;
import ui.page.PageViewer;

/**
 * This class creates the menu bar that is seen on the UI
 
 * @author DanSun
 *
 */
public class UiMenuBarCreater {

    private static final String propertiesFile = "resources/ui/MenuLabelsBundle";
    
    ResourceBundle myMenuLabels;
    
    public UiMenuBarCreater(Locale currentLocale) {
	myMenuLabels = ResourceBundle.getBundle(propertiesFile, currentLocale);
    }
    
    /**
     * Creates the menu bar the is seen on the view
     * @param helpWindow The HelpWindow needed
     * @return The menu bar that is created
     */
    public UiMenuBar createUiMenuBar(HelpWindow helpWindow, PageViewer pageViewer) {
	assert(helpWindow != null);
	UiMenuBar uiMenuBar = new UiMenuBar();
	uiMenuBar.addUiMenu(createHelpMenu(helpWindow));
	uiMenuBar.addUiMenu(createViewMenu(pageViewer));
	return uiMenuBar;
    }
    
    private  UiMenuHelp createHelpMenu(HelpWindow helpWindow) {
	UiMenuHelp menuHelp = new UiMenuHelp(myMenuLabels);
	CommandDocumentItem commandDocumentItem = 
		new CommandDocumentItem(myMenuLabels, helpWindow);
	menuHelp.addUiMenuItem(commandDocumentItem);
	return menuHelp;
    }
    

    private UiMenuView createViewMenu(PageViewer pageViewer) {
	UiMenuView menuView = new UiMenuView(myMenuLabels);
	StateDisplayViewCheckMenuItem stateDisplayView = 
		new StateDisplayViewCheckMenuItem(myMenuLabels, pageViewer);
	CommandInputViewCheckMenuItem commandInputView = 
		new CommandInputViewCheckMenuItem(myMenuLabels, pageViewer);
	CommandOutputViewCheckMenuItem commandOutputView = 
		new CommandOutputViewCheckMenuItem(myMenuLabels, pageViewer);
	ControlPanelViewCheckMenuItem controlPanelView = 
		new ControlPanelViewCheckMenuItem(myMenuLabels, pageViewer);
	menuView.addUiMenuItem(stateDisplayView);
	menuView.addUiMenuItem(commandOutputView);
	menuView.addUiMenuItem(commandInputView);
	menuView.addUiMenuItem(controlPanelView);
	
	return menuView;
    }

}
