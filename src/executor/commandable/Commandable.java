package executor.commandable;


public interface Commandable {
	
	/**
	 * Resets instance variables
	 * 
	 * @return the distance commandable moved, if appropriate
	 */
	public double reset();
	
	/**
	 * Translates user query into corresponding double value
	 * 
	 * @param query		translates query from user
	 * @return			value corresponding to query
	 */
	public double interpretQuery(String query);
	
	
}
