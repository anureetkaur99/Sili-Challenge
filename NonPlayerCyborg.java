package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class NonPlayerCyborg extends Cyborg {

	private IStrategy currStrategy;
	private GameWorld gw;

	public NonPlayerCyborg(int size, int r, int g, int b, Point location, int speed, int heading, int steerDirection,
			int maxSpeed, int energyLevel, int energyConsumptionRate, int damageLevel, int maxDamage,
			int lastBaseReached, IStrategy currStrategy) {

		super(size, r, g, b, location, speed, heading, steerDirection, maxSpeed, energyLevel, energyConsumptionRate,
				damageLevel, maxDamage, lastBaseReached);
		this.currStrategy = currStrategy;
		this.gw = GameWorld.getGameWorld();
	}

	// size of a cyborg indicates the length of equal sides of the square 
	
	// This method uses the location and size attributes of the object to determine
	// where to draw the object so it's center coincides with it's location (i.e.
	// center_location.x - size/2, center_location.y - size/2) relative
	// to the origin of the MapView, which is the origin of the game world
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + super.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + super.getLocation().getY());
		int width = super.getSize();
		int height = super.getSize();
		g.setColor(getColor());		
		g.drawRect(x, y, width, height);
	}

	public void setStrategy(IStrategy strategy) {
		currStrategy = strategy;
	}

	public void invokeStrategy() {
		this.changeHeading(currStrategy.apply(this));
	}

	public IStrategy getCurrStrategy() {
		return this.currStrategy;
	}

	public String toString() {
		if (currStrategy instanceof DirectStrategy) {
			currStrategy = (DirectStrategy) currStrategy;
			return "Non-Player " + super.toString() + currStrategy.toString();
		} else {
			currStrategy = (AttackStrategy) currStrategy;
			return "Non-Player " + super.toString() + currStrategy.toString();
		}
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof Base) {
			Base b = (Base) otherObject;
			gw.baseCollide(b);
		}else if (otherObject instanceof Drone) {
			gw.droneCollide();
		}else if (otherObject instanceof NonPlayerCyborg) {
			gw.npcCollide();
		}else if (otherObject instanceof EnergyStation) {
			EnergyStation energyS = (EnergyStation) otherObject;
			gw.energyCollide(energyS);
		}
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}
}
