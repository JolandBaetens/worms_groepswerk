package worms.model;

public class IllegalCostException extends Exception {
	
	public IllegalCostException(int cost){
		this.cost = cost;
	}
	private final int cost;
	
	public int getCost(){
		return this.cost;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
