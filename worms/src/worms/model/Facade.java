package worms.model;


public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) {
		Worm worm = new Worm(x, y, direction, radius, name);
		return worm;
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) {
		try{
			worm.move(nbSteps);
		}
		catch (IllegalArgumentException exc){
			if(exc.getMessage() == "This value of steps isn't possible"){
				throw new ModelException("You can not move!");
			}
			if(exc.getMessage() == "There aren't enough action points"){
				throw new ModelException("You don't got enough action points");
			}
		}
		catch (IllegalStepsException exc){
			throw new ModelException("invalid nbSteps");
		}
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

	@Override
	public void jump(Worm worm) {
		worm.jump();
	}

	@Override
	public double getJumpTime(Worm worm) {
		return worm.getJumpTime();
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.getJumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getOrientation();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		worm.setRadius(newRadius);
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) {
		worm.rename(newName);
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
