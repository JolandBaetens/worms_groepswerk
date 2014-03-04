package worms.model;

import worms.util.Util;

public class Worm {
	/**
	 * 
	 * @param x
	 * 		This value gives the position on the horizontal axis.
	 * @param y
	 * 		This value gives the position on the vertical axis.
	 * @param direction
	 * 		This value gives the angle in radians in wich the worm is facing. 	
	 * 		The angle is 0 when facing the positive x-axis and pi/2 when facing the positive y-axis.
	 * @param radius
	 * 		The shape of a worm is a circle defined by this value.
	 * @param name
	 * 		This is the name of the worm.
	 * @return
	 */
	public Worm(double x, double y, double orientation, double radius,
			String name){
		this.setX(x);
		this.setY(y);
		this.setOrientation(orientation);
		this.setRadius(radius);
		this.setName(name);
		this.setActionPoints(this.getMaxActionPoints());
	
	}
	private int actionPoints;
	private double X;
	private double Y;
	private double orientation;
	private double radius;
	private String name; 
	private final double DENSITY = 1062.0;
	private final double EARTH_ACCELERATION = 9.80665;
	private double mass;
	private double minimalRadius = 0.25 ;
	
	/**
	 * Returns whether or not the given worm can move a given number of steps.
	 */
	public boolean canMove(int nbSteps){
		int cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
				+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
		return cost <= this.getActionPoints();
	}

	/**
	 * Moves the given worm by the given number of steps.
	 */
	public void move(int nbSteps){
		
		int cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
				+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
		
		this.setActionPoints(this.getActionPoints() -cost);
		double distance = nbSteps * this.getRadius();
		this.setX(this.getX() + distance*Math.cos(this.getOrientation()));
		this.setY(this.getY() + distance*Math.sin(this.getOrientation()));
		
	}

	/**
	 * Returns whether or not the given worm can turn by the given angle.
	 */
	public boolean canTurn(double angle){
		double fraction = angle/(2*Math.PI);
		int cost = (int) Math.ceil(60/fraction);
		return cost <= this.getActionPoints();
	}

	/**
	 * Turns the given worm by the given angle.
	 */
	public void turn(double angle){
		assert this.isValidOrientation(angle);
		assert this.isValidOrientation(this.getOrientation() + angle);
		assert this.canTurn(angle);
		
		this.setOrientation(this.getOrientation() + angle);
		double fraction = angle/(2*Math.PI);
		int cost = (int) Math.ceil(60/fraction);
		this.setActionPoints(this.getActionPoints() -cost);
		
	}

	/**
	 * JUMPING DEFENSIEF UITWERKEN! HOE DE ACTIEPUNTEN OPLOSSEN?
	 * Makes the given worm jump.
	 */
	void jump(){
		if (orientation tussen 0 en Pi):
			jump
	}

	/**
	 * Returns the total amount of time (in seconds) that a
	 * jump of the given worm would take.
	 */
	private double getJumpTime(){
		double d = Math.pow(this.getStartVelocity(),2)*Math.sin(2*this.getOrientation())/this.getEarthAcceleration();
		double t = (d/(this.getStartVelocity()*Math.cos(this.getOrientation())));
		return t;
	}

	/**
	 * Returns the location on the jump trajectory of the given worm
	 * after a time t.
	 *  
	 * @return An array with two elements,
	 *  with the first element being the x-coordinate and
	 *  the second element the y-coordinate
	 */
	private double[] getJumpStep(double t){
		
		double initVelocityX = this.getStartVelocity() * Math.cos(this.getOrientation());
		double initVelocityY = this.getStartVelocity() * Math.sin(this.getOrientation());
		double currentX = getX() + (initVelocityX * t);
		double currentY = getY() + (initVelocityY * t - 0.5 * this.getEarthAcceleration() * Math.pow(t, 2));
		double jumpStep[] = new double[] {currentX, currentY};
		return jumpStep;
	}
	
	private double getStartVelocity(){
		double F = (5*this.getActionPoints())+(this.getMass()*this.getEarthAcceleration());
		return ((F/this.getMass())*0.5);
	}

	/**
	 * Returns the x-coordinate of the current location of the given worm.
	 */
	public double getX(){
		return this.X;
	}
	public void setX(double x){
//		this.isValidCoordinate(x);
		this.X = x;
	}
	private boolean isValidCoordinate(double coordinate) {
		return (coordinate != Double.NaN);
	}
		

	/**
	 * Returns the y-coordinate of the current location of the given worm.
	 */
	public double getY(){
		return this.Y;
	}
	public void setY(double y){
//		this.isValidCoordinate(y);
		this.Y = y;
	}

	/**
	 * Returns the current orientation of the given worm (in radians).
	 */
	public double getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	public boolean isValidOrientation(double orientation){
		return ((Util.fuzzyGreaterThanOrEqualTo(orientation,0)) && 
				(!Util.fuzzyGreaterThanOrEqualTo(orientation,2*Math.PI)));
//				((orientation >= 0)&&(orientation<2*Math.PI));
	}
		

	/**
	 * Returns the radius of the given worm.
	 */
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * 
	 * 
	 * @param radius
	 * @return 	the radius must be higher then the specified minimalRadius and 	
	 * 			radius must be of type 'double'.
	 * 			|(radius > this.getMinimalRadius() && radius.isType() == double);
	 */
	public boolean isValidRadius(double radius){
		return (Util.fuzzyGreaterThanOrEqualTo(radius,this.getMinimalRadius()));
				
	}
	
	/**
	 * Sets the radius of the given worm to the given value.
	 */
	void setRadius(double newRadius){
		// isValidRadius
		this.radius = newRadius;
	}
	
	/**
	 * Returns the minimal radius of the given worm.
	 */
	public double getMinimalRadius(){
		return this.minimalRadius;
	}
	
	public void setMinimalRadius(double newMinimalRadius){
		this.minimalRadius = newMinimalRadius;
	}

	/**
	 * Returns the current number of action points of the given worm.
	 */
	public int getActionPoints(){
		return this.actionPoints;
	}
	
	public void setActionPoints(int newActionPoints){
		this.actionPoints = newActionPoints;
	}
	
	/**
	 * Returns the maximum number of action points of the given worm.
	 */
	public int getMaxActionPoints(){
		return (int) Math.round(getMass());
	}
	
	
	
	
	/**
	 * Returns the name the given worm.
	 */
	public String getName(){
		return this.name;
	}
	
	public boolean isValidName(String newName){
		if (!Util.fuzzyGreaterThanOrEqualTo(newName.length(), 2)) {
			return false;
		} else if(!Character.isUpperCase(newName.charAt(0))){
			return false;
		} else {
			return newName.matches("[a-zA-Z\'\" ]");
		}
	}

	/**
	 * Renames the given worm.
	 */
	public void setName(String newName) {
		if(isValidName(newName)){
			this.name = newName;
		}
	}

	/**
	 * Returns the mass of the given worm.
	 * massa wordt berekend uit twee variabelen (radius en density) en wordt dus telkens opnieuw berekend. 
	 * de massa als variabele opslaan is enkel redundantie.
	 */
	public double getMass(){
		return this.getDensity() * (4.0/3.0 * Math.PI * Math.pow(this.getRadius(),3));
	}

	public double getDensity(){
		return this.DENSITY;
	}
	public double getEarthAcceleration(){
		return this.EARTH_ACCELERATION;
	}

	
}

