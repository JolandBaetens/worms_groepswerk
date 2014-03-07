package worms.model;

public class IllegalNameException extends Exception {
	/**
	 * 
	 */
	
	public IllegalNameException(String name){
		this.name = name;
		
	}
	public String getName(){
		return this.name;
	}
	private final String name;
	private static final long serialVersionUID = 1L;
}
