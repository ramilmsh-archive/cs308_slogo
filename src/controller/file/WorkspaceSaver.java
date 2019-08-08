package controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javafx.scene.paint.Color;

/**
 * 
 * @author cy122
 * 
 * This class is responsible for saving the data of workspace into file.
 *
 */
public class WorkspaceSaver {
	private Properties properties= new Properties();
	private File file;
	
	/**
	 * 
	 * @param absolutePath- this should be an absolute path name
	 */
	public WorkspaceSaver(String absolutePath){
		try {
			file = new File(absolutePath);
			if(!file.isDirectory()&&file.isFile()){
				properties.load((InputStream)(new FileInputStream(file)));
			}
		} catch (FileNotFoundException e) {
			// Do nothing
		} catch (IOException e) {
			//Do nothing
		}
	}
	
	//https://stackoverflow.com/questions/17925318/how-to-get-hex-web-string-from-javafx-colorpicker-color
	private String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
	
	public void savePalettes(Map<Integer, Color> palettes){
		Map<String,String> result = new HashMap<String,String>();
		for(Integer key:palettes.keySet()){
			result.put(key.toString(), toRGBCode(palettes.get(key)));
		}
		saveMap("Variables",result);
		write();
	}
	
	public void saveCommandHistories(String[] histories){
		String result = "";
		for(String history:histories){
			result = result + history + " ";
		}
		properties.setProperty("CommandHistories",result);
		write();
	}
	
	public void saveVariables(Map<String, String> variables){
		saveMap("Variables",variables);
		write();
	}
	
	public void saveMethods(Map<String, String> methods){
		saveMap("Methods",methods);
		write();
	}
	
	private void saveMap(String name, Map<String,String> data){
		String result = "";
		Iterator<String> keyIter = data.keySet().iterator();
		while(keyIter.hasNext()){
			String key = keyIter.next();
			String pair = new String(key+","+data.get(key));
			if(keyIter.hasNext()){
				pair=pair+" ";
			}
			result = result+pair;
		}
		properties.setProperty(name, result);
	}
	
	private void write(){
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "workspace");
			fileOut.close();
		} catch (FileNotFoundException e) {
			// DO NOTHING
		} catch (IOException e) {
			// DO NOTHING
		}
	}
}
