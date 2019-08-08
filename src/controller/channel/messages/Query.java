package controller.channel.messages;

/**
 * Message class for queries to the turtle and the pen
 * Example: xcoordinate
 * 
 * @author Natalie
 *
 */

public class Query extends Message{
	private String query;
	
	public Query (String s){
		query = s;
	}
	
	public String getQuery(){
		return query;
	}
}
