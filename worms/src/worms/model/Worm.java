/**
 * WAT TE DOEN:
 * 
 * - documentatie
 * - tests
 * - jumping defensief uitwerken
 * 
 * 
 */
package worms.model;

import worms.util.Util;

/**
 * 
 * @author 	Joland Baetens
 * 			Robbe Berrevoets
 * 		
 *
 */

public class Worm {
	/**
	 * A variable that holds the actionpoints for the worm. The worm can use these 
	 * actionpoints to move, turn and jump. It is associated with the mass of the worm
	 * and must be smaller than maximumactionpoints.  
	 */
	private int actionPoints;
	
	/**
	 * This is the position on the horizontal axis for the worm. 
	 */
	private double X;
	
	/**
	 * This is the position on the vertical axis for the worm. 
	 */
	private double Y;
	
	/**
	 * This value gives the angle in radians in wich the worm is facing. 	
	 * The angle is 0 when facing the positive x-axis and pi/2 when facing the positive y-axis.
	 */
	private double orientation;
	
	/**
	 * The shape of a worm is a circle defined by this value.
	 * the radius must be greater than the minimalRadius.
	 */
	private double radius;
	
	/**
	 * The name of the worm.
	 */
	private String name; 
	
	/**
	 * The densitiy of the worm, this is a final variable and so it will not change during
	 * the game.
	 */
	private final double DENSITY = 1062.0;
	
	/**
	 * the acceleration the earth gives the worm. 
	 * This is a final variable so we assume the acceleration is constant.
	 */
	private final double EARTH_ACCELERATION = 9.80665;
	
	/**
	 * This defines a lower bound for the radius. 
	 */
	private double minimalRadius = 0.25 ;
	
	/**
	 * 
	 * @param x
	 * 		This value gives the position on the horizontal axis.
	 * @param y
	 * 		This value gives the position on the vertical axis.
	 * @param orientation
	 * 		This value gives the angle in radians in wich the worm is facing. 	
	 * 		The angle is 0 when facing the positive x-axis and pi/2 when facing the positive y-axis.
	 * @param radius
	 * 		The shape of a worm is a circle defined by this value.
	 * @param name
	 * 		This is the name of the worm.
	 * @post 	The horizontal position is set to double value x
	 * 			| new.getX() == x
	 * @post 	The vertical position is set to double value y
	 * 			| new.getY() == y
	 * @post 	the orientation of the worm is set to the given orientation
	 * 			| new.getOrientation() == orientation
	 * @post 	the radius of this worm is set to the given radius
	 * 			| new.getRadius() == radius
	 * @post 	the name of the worm is equal to the given name
	 * 			| new.getName() == name
	 * @throws 	IllegalArgumentException
	 * 			The x-position is invalid when the given double is not a number.
	 * 			| (!isValidCoordinate(x))
	 * @throws 	IllegalArgumentException
	 * 			The y-position is invalid when the given double is not a number.
	 * 			| (!isValidCoordinate(y))
	 * @throws 	IllegalArgumentException
	 * 			the orientation is invalid when the given double is not in between 
	 * 			0 an 2*Pi. 
	 * 			| (!isValidOrientation(orientation))
	 * @throws 	IllegalArgumentException
	 * 			the Radius is invalid when it's smaller than the minimalRadius
	 * 			and it's not a number. 
	 * 			|(!isValidRadius(radius))
	 * @throws 	IllegalArgumentException
	 * 			the name is invalid when it hasn't at least two characters, when it contains
	 * 			a character wich is not a letter, quote or space and when the name doesn't start
	 * 			with a capital letter.
	 * 			|(!isValidName(name))
	 */
	
	public Worm(double x, double y, double orientation, double radius,
			String name) throws IllegalArgumentException {
		this.setX(x);
		this.setY(y);
		this.setOrientation(orientation);
		this.setRadius(radius);
		this.rename(name);
		this.setActionPoints(this.getMaxActionPoints());
	
	}
	
	/**
	 * Check whether this person can have the other person as its spouse
	 * in view of the given gender.
	 *
	 * @param  other
	 *         The other person to check.
	 * @param  gender
	 *         The gender to be assumed for this person. 
	 * @return True if the other person is not effective.
	 *       | if (other == null)
	 *       |   then result == true
	 *         Otherwise, false if this person or the other person
	 *         is terminated.
	 *       | else if ( this.isTerminated() || other.isTerminated() )
	 *       |   then result == false
	 *         Otherwise, true if and only if the given gender is a valid
	 *         gender for a person and differs from the gender of the
	 *         other person.
	 *       | else
	 *       |   result ==
	 *       |     isValidGender(gender) &&
	 *       |     (gender != other.getGender())
	 * @note   We must add the gender as an extra argument to this method,
	 *         because we need the method in the constructor at a time
	 *         the gender of the new person is not yet registered.
	
	/**
	 * Returns whether or not the given worm can move a given number of steps.
	 * 
	 * @param	nbSteps
	 * 			The number of steps the worm takes.
	 * @return 	True if the actionpoints are greator or equal to the cost.
	 * 			|result == Util.fuzzyGreaterThanOrEqualTo(this.getActionPoints(),cost);
	 * @note 	the cost is calculated by a given formula.
	 * 			|int cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
				|+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
	 */
	public boolean canMove(int nbSteps){
		int cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
				+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
		
		return Util.fuzzyGreaterThanOrEqualTo(this.getActionPoints(),cost);
	}

	/**
	 * Moves the given worm by the given number of steps. The distance that the worm moves
	 * is the number of steps multiplied with the radius. 
	 * @param 	nbSteps
	 * 			This value gives the number of steps that the worm will move. 
	 * @post	The worm has changed it's position, the movement in x and y direction is calculated
	 * 			with distance multiplied with cosinus for x and with sinus for y.
	 * 			The x movement is added with the current x position.
	 * 			The y movement is added with the current y position.
	 * 			|this.setX(this.getX() + distance*Math.cos(this.getOrientation()));
	 * 			|this.setY(this.getY() + distance*Math.sin(this.getOrientation()));
	 * @throws	IllegalArgumentException
	 * 			The worm can't move when there aren't sufficient action points!
	 * 			The total cost of a step in the current direction can be computed as followed:
	 * 			cost = cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
	 * 			+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
	 * 			|(!canMove(cost))
	 */
	public void move(int nbSteps) throws IllegalArgumentException, IllegalStepsException{
		nbSteps = -2;
		if (!isValidNbSteps(nbSteps)){
			throw new IllegalArgumentException("This value of steps isn't possible");
		}
	
		if (!canMove(nbSteps)){
			throw new IllegalArgumentException("There aren't enough action points");
			
		}
		int cost =(int) Math.ceil(nbSteps*((double) Math.abs(Math.cos(this.getOrientation())) 
				+ (double) Math.abs(4*Math.sin(this.getOrientation()))));
		this.setActionPoints(this.getActionPoints() -cost);
		double distance = nbSteps * this.getRadius();
		this.setX(this.getX() + distance*Math.cos(this.getOrientation()));
		this.setY(this.getY() + distance*Math.sin(this.getOrientation()));
		
	}
	
	public boolean isValidNbSteps(int nbSteps){
		return (nbSteps > 0);
	}
	/**
	 * Returns whether or not the given worm can turn by the given angle.
	 * 
	 * @param 	angle
	 * 			The angle that the worm will turn.
	 * @return	true if the angle is valid: the turn can be made with the worm's actionpoints.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(this.getActionPoints(),cost);
	 * @note	The cost is calculated so that the cost is minimal: 
	 * 			example: if the worm has an angle of 3*Pi/2 we calculate te cost with an angle -Pi/2.
	 * 			The first thing we do is change te angle to a positve value.
	 * 			|if (angle < 0)
	 * 			|positiveAngle = (2*Math.PI + angle);
	 * 			than we calculate the cost by using the minimal value for angle
	 * 			|int cost = (int) Math.ceil(positiveAngle* 30 / (Math.PI));
	 * 			|if (positiveAngle > Math.PI)
	 * 			|cost = (int) Math.ceil((2*Math.PI - positiveAngle)* 30 / (Math.PI));		
	 */
	public boolean canTurn(double angle){
		double positiveAngle = angle;
		if (angle < 0){
			positiveAngle = (2*Math.PI + angle);
		}
		int cost = (int) Math.ceil(positiveAngle* 30 / (Math.PI));
		if (positiveAngle > Math.PI){
			
			cost = (int) Math.ceil((2*Math.PI - positiveAngle)* 30 / (Math.PI));
		}
		return Util.fuzzyGreaterThanOrEqualTo(this.getActionPoints(),cost);
//		return cost <= this.getActionPoints();
	}

	/**
	 * Turns the given worm by the given angle.
	 */
	public void turn(double angle){
		assert this.isValidOrientation(Math.abs(angle));
		double positiveAngle = angle;
		if (angle < 0){
			positiveAngle = (2*Math.PI + angle);
		}
		
		assert this.canTurn(angle);
//		assert this.isValidOrientation(this.getOrientation() + angle);
		int cost = (int) Math.ceil(positiveAngle* 30 / (Math.PI));
		if (positiveAngle > Math.PI){
			// hoe ronden we cost hier af?
			cost = (int) Math.ceil((2*Math.PI - positiveAngle)* 30 / (Math.PI));
		}
		
		
		assert this.canTurn(cost);
		
		this.setActionPoints(this.getActionPoints() -cost);
		
		
		double newOrientation  = (this.getOrientation() + positiveAngle) % (2*Math.PI); 
		this.setOrientation(newOrientation);
		

	}
	

	/**
	 * JUMPING DEFENSIEF UITWERKEN!
	 * Makes the given worm jump.
	 */
	public void jump(){
		
		if (Util.fuzzyGreaterThanOrEqualTo(this.getOrientation(),0) && 
				(!Util.fuzzyGreaterThanOrEqualTo(this.getOrientation(),Math.PI))){
			double t = this.getJumpTime();
			double [] endPosition = this.getJumpStep(t);
			this.setX(endPosition[0]);
			this.setY(endPosition[1]);
			this.setActionPoints(0);
			
		}
			
	}

	/**
	 * Returns the total amount of time (in seconds) that a
	 * jump of the given worm would take.
	 */
	public double getJumpTime(){
		if (this.getActionPoints() == 0){
			return 0;
		}
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
	public double[] getJumpStep(double t){
		
		double initVelocityX = this.getStartVelocity() * Math.cos(this.getOrientation());
		double initVelocityY = this.getStartVelocity() * Math.sin(this.getOrientation());
		double currentX = getX() + (initVelocityX * t);
		double currentY = getY() + (initVelocityY * t - 0.5 * this.getEarthAcceleration() * Math.pow(t, 2));
		double jumpStep[] = new double[] {currentX, currentY};
		return jumpStep;
	}
	
	public double getStartVelocity(){
		double F = (5*this.getActionPoints())+(this.getMass()*this.getEarthAcceleration());
		return ((F/this.getMass())*0.5);
	}

	/**
	 * Returns the x-coordinate of the current location of the given worm.
	 */
	public double getX(){
		return this.X;
	}

	/**
	 * Sets the x-position of the worm to the given value x.
	 * @param x
	 * This value gives the position on the horizontal axis.
	 * @post	The x-position is set to the given value x. 
	 * 			|new.getX() == x
	 * @throws 	IllegalArgumentException
	 * 			The x-position is invalid when the given double is not a number.
	 * 			| (!isValidCoordinate(x))
	 */
	public void setX(double x) throws IllegalArgumentException{

		if (!this.isValidCoordinate(x)){
			throw new IllegalArgumentException("Invalid x-position!");
		}
		this.X = x;
	}
	
	
	
	
	public boolean isValidCoordinate(double coordinate) {
		return (coordinate != Double.NaN);
	}
		

	/**
	 * Returns the y-coordinate of the current location of the given worm.
	 */
	public double getY(){
		return this.Y;
	}
	
	/**
	 * Sets the y-position of the worm to the given value y.
	 * @param y
	 * This value gives the position on the vertical axis.
	 * @post	The y-position is set to the given value y. 
	 * 			|new.getY() == y
	 * @throws 	IllegalArgumentException
	 * 			The y-position is invalid when the given double is not a number.
	 * 			| (!isValidCoordinate(y))
	 */
	
	public void setY(double y) throws IllegalArgumentException{

		if (!this.isValidCoordinate(y)){
			throw new IllegalArgumentException("Invalid y-position!");
		}
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
		return ((Util.fuzzyGreaterThanOrEqualTo(radius,this.getMinimalRadius())) && (radius != Double.NaN));
				
	}
	
	/**
	 * Sets the radius of the given worm to the given value.
	 */
	public void setRadius(double newRadius) throws IllegalArgumentException{

		if (!isValidRadius(newRadius)){
			throw new IllegalArgumentException("Invalid radius!");
		}
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
		if (newActionPoints < 0){
			newActionPoints = 0;
		}
		if (newActionPoints > this.getMaxActionPoints()){
			newActionPoints =  this.getMaxActionPoints();
		}
		
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
			return newName.matches("[a-zA-Z\'\" ]*");
		}
	}

	/**
	 * Renames the given worm.
	 */
	public void rename(String newName) throws IllegalArgumentException{
		if(!isValidName(newName)){
			throw new IllegalArgumentException("Invalid name!");
		}
		this.name = newName;
		
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

