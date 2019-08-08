package controller.file;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * 
 * @author cy122
 * 
 * This class is to help the front-end convert between Color and index
 *
 */

@SuppressWarnings("serial")
public class PaletteManager extends HashMap<Integer,Color>{
	public PaletteManager(Map<Integer,Color> palettes){
		super(palettes);
	}
	
	/* return -1 if such color doesn't exist in PaletteManager*/
	public int Color2Int(Color color){
		for(int index:this.keySet()){
			if(this.get(index).equals(color)){
				return index;
			}
		}
		return -1;
	}
	
	public Color Int2Color(int index){
		return this.get(index);
	}
	
	public void setPalette(int index, Color color){
		this.put(new Integer(index), color);
	}
}
