package controller;
/**
 * 
 * The functions that the front-end should use when they are needed, which are provided by the Controller
 * 
 * @author cy122
 *
 */
public interface Controller2ViewAPI {
	/**
	 * 
	 * @param command - the string of commands that the user put in
	 */
	void runCommand(String command);
	/**
	 * 
	 * @param language - Like "English" "Russian" "Chinese"
	 */	
	void changeLanguage(String language);
	
	void createWorkspace();
	
	void switchToWorkSpace(int index);
	
	void save(String absolutePath);
	
	void load(String absolutePath);
}
