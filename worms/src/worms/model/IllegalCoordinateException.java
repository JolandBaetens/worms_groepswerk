package worms.model;

public class IllegalCoordinateException extends RuntimeException {
	
	public IllegalCoordinateException(double coordinate){
		this.coordinate = coordinate;
		
	}
	public double getCoordinate(){
		return this.coordinate;
	}
	private final double coordinate;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
