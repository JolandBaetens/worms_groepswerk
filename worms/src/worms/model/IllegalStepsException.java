package worms.model;

public class IllegalStepsException extends Exception {
	
	public IllegalStepsException(int nbSteps){
		this.nbSteps = nbSteps;
	}
	
	public int getNbSteps(){
		return this.nbSteps;
	}
	private final int nbSteps;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}


