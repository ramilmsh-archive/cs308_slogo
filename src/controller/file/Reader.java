package controller.file;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * @author cy122
 * 
 * This class is to used to get the default type and choices of data that front-end needs.
 * 
 * It read from a properties type file.
 *
 */
public class Reader{
	private ResourceBundle myResources;
	private static final String configPath="resources/Configuration/";
	private static final String configDefaultPath="resources/Configuration/default";
	private static final ResourceBundle defaultResources = ResourceBundle.getBundle(configDefaultPath);
	private static final String DEFAULTLANGUAGE=defaultResources.getString("Language");
	private static final Color DEFAULTPENCOLOR= getStringtoColor(defaultResources.getString("Pen"));
	private static final Color DEFAULTBACKCOLOR=getStringtoColor(defaultResources.getString("Background"));
	private static final String DEFAULTTURTLETYPE=defaultResources.getString("Turtle");
	private static final double DEFAULTTURTLELENGTH = Double.parseDouble(defaultResources.getString("TurtleSize"));
	private static Map<Integer,String> languageChoices=initLanguageChoices();
	private static Map<Integer,Image> turtleImageChoices=initTutuleImageChoices();
	private static Map<Integer,Color> colorChoices=initColorChoices();
	
	
	public Reader(String path){
		myResources = ResourceBundle.getBundle(path);
	}

	private static Map<Integer,Color> initColorChoices() {
		Map<Integer,Color> result=new HashMap<Integer,Color>();
		int index=0;
		for(String color:defaultResources.getString("ColorChoices").split(",")){
			if((color!=null)&&(!color.equals(""))){
				result.put(index,getStringtoColor(color));
			}
			index++;
		}
		return result;
	}
	
	private static Map<Integer,Image> initTutuleImageChoices(){
		Map<Integer,Image> result= new HashMap<Integer,Image>();
		int index=0;
		for(String turtleName:defaultResources.getString("TurtleChoices").split(",")){
			if((turtleName!=null)&&(!turtleName.equals(""))){
				result.put(index,getTurtleImage(turtleName));
			}
			index++;
		}
		return result;
	}
	
	private static Map<Integer,String> initLanguageChoices(){
		Map<Integer,String> result= new HashMap<Integer,String>();
		int index=0;
		for(String language:defaultResources.getString("LanguageChoices").split(",")){
			if((language!=null)&&(!language.equals(""))){
				result.put(index,language);
			}
			index++;
		}
		return result;
	}
	
	/**
	 * 
	 * @return - the initial language of slogo
	 */
	public String getConfigLanguage(){
		String result;
		try {
			result=myResources.getString("Language");
			if(result==null){
				result=DEFAULTLANGUAGE;
			}
		} catch (Exception e) {
			result=DEFAULTLANGUAGE;
		}
		return result;
	}
	
	/**
	 * 
	 * @return - list of images of turtle that user can choose
	 */
	public Image getConfigTurtleImage(){
		String turtle;
		try {
			turtle=myResources.getString("Turtle");
			if(turtle==null){
				turtle=DEFAULTTURTLETYPE;
			}
		} catch (Exception e) {
			turtle=DEFAULTTURTLETYPE;
		}
		return getTurtleImage(turtle);
	}
	
	private static Image getTurtleImage(String turtleType){
		Image turtle= new Image("resources/Configuration/turtles/"+""+turtleType+".png");
		ImageView imageView = new ImageView(turtle);
	    imageView.setPreserveRatio(false);
	    imageView.setFitWidth(DEFAULTTURTLELENGTH);
	    imageView.setFitHeight(DEFAULTTURTLELENGTH);
		return turtle;
	}
	
	private static Color getStringtoColor(String type){
		return Color.web(type);
	}
	
	/**
	 * 
	 * @return - the color of pen
	 */
	public Color getConfigPenColor(){
		return getColorProperty("Pen", DEFAULTPENCOLOR);
	}
	
	/**
	 * 
	 * @return - the color of background
	 */
	public Color getConfigBackgroundColor(){
		return getColorProperty("Background", DEFAULTBACKCOLOR);
	}
	
	private Color getColorProperty(String name, Color defaultColor){
		Color result;
		try {
			result=getStringtoColor(myResources.getString(name));
			if(result==null){
				result=defaultColor;
			}
		} catch (Exception e) {
			result=defaultColor;
		}
		return result;
	}
	
	public static int getScreenWidth(){
		return Integer.parseInt(defaultResources.getString("Width"));
	}
	
	public static int getScreenHeight(){
		return Integer.parseInt(defaultResources.getString("Height"));
	}
	
	/**
	 * 
	 * @return - the initial language of slogo
	 */
	public static Map<Integer,String> getLanguageChoices(){
		return new HashMap<Integer,String>(languageChoices);
	}
	
	/**
	 * 
	 * @return - list of images of turtle that user can choose
	 */
	public static Map<Integer,Image> getTurtleImageChoices(){
		return new HashMap<Integer,Image>(turtleImageChoices);
	}
	
	/**
	 * 
	 * @return - the color of pen
	 */
	public static Map<Integer,Color> getPenColorChoices(){
		return new HashMap<Integer,Color>(colorChoices);
	}
	
	/**
	 * 
	 * @return - the color of background
	 */
	public static Map<Integer,Color> getBackgroundColorChoices(){
		return new HashMap<Integer,Color>(colorChoices);
	}
	
	public static String getDefaultPath(){
		return new String(configDefaultPath);
	}
	
	public static String getFilePath(String filename){
		return new String(configPath)+filename;
	}
}
