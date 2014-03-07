package worms.model;

public class IllegalRadiusException extends Exception {

	public IllegalRadiusException(double radius){
		this.radius = radius;
		
	}
	public double getRadius(){
		return this.radius;
	}
	private final double radius;
	/**
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
