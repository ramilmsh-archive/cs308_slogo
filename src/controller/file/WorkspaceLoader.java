package controller.file;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;

/**
 * 
 * @author cy122
 * 
 * This class is responsible for loading the data of workspace into file.
 *
 */
public class WorkspaceLoader {
	private ResourceBundle myResources;
	
	/**
	 * 
	 * @param absolutePath- this should be an absolute path name
	 */
	public WorkspaceLoader(String absolutePath){
		File file = new File(absolutePath);
		try{
			if(file.exists()){
		        String directoryPath = file.getParent();
		        File directory = new File(directoryPath);
		        URL[] urls = {directory.toURI().toURL()};
		        ClassLoader loader = new URLClassLoader(urls);
		        myResources = ResourceBundle.getBundle(file.getName().split(".")[0], Locale.getDefault(), loader);
			}else{
				throw new Exception();
			}
		}catch( Exception e ){
			myResources=null;
		}
	}
	
	public Map<Integer, Color> loadPalettes(){
		Map<Integer, Color> palettes = new HashMap<Integer, Color>();
		if(myResources!=null){
			String[] paletteString=myResources.getString("Palettes").split(" ");
			for(String pair:paletteString){
				if(!pair.equals(" ")){
					String[] pairs = pair.split(",");
					palettes.put(Integer.parseInt(pairs[0]), Color.web(pairs[1]));
				}
			}
		}
		return palettes;
	}
	
	public Iterable<String> loadCommandHistories(){
		List<String> histories = new ArrayList<String>();
		if(myResources!=null){
			String[] historyString=myResources.getString("CommandHistories").split(" ");
			for(String history:historyString){
				if(!history.equals(" ")){
					histories.add(history);
				}
			}
		}
		return histories;
	}
	
	public Map<String, String> loadVariables(){
		return loadMap("Variables");
	}
	
	public Map<String, String> loadMethods(){
		return loadMap("Methods");
	}
	
	private Map<String,String> loadMap(String name){
		Map<String,String> result = new HashMap<String,String>();
		if(myResources!=null){
			String[] strings=myResources.getString(name).split(" ");
			for(String string:strings){
				if(!string.equals(" ")){
					String[] pairs = string.split(",");
					result.put(pairs[0], pairs[1]);
				}
			}
		}
		return result;
	}
}
