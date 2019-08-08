package executor.commandable.pen;

/**
 * Interface for pen classes
 * 
 * @author Natalie
 *
 */
public interface Pen {

	/**
	 * Set pen up or down
	 * 
	 * @param vis		whether pen should be up or down
	 * @return whether pen is now up or down after method executes
	 */
	public double penVis(Double vis);
	
	/**
	 * Set pen color
	 * 
	 * @param index		index of color to set pen to
	 * @return			index of color after method executes
	 */
	public double penColor(Double index);
	
	/**
	 * Set pen thickness
	 * 
	 * @param pixels	new size of pen
	 * @return			size of pen after method executes
	 */
	public double penSize(Double pixels);
}
