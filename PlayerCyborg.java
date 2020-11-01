package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg {

	// maintain a single global reference to the player cyborg
	private static PlayerCyborg playerCyborg;
	private static Point baseOneLocate = new Point(200, 700);
	private GameWorld gw;

	// ensure that no one can create a player cyborg directly
	// private constructor restricted for this class itself
	private PlayerCyborg() {
		super(130, 255, 0, 0, baseOneLocate, 50, 0, 0, 200, 500, 2, 0, 20, 1);
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
		g.fillRect(x, y, width, height);
	}

	// provide access to the instance of Player Cyborg, creating it if necessary
	public static PlayerCyborg getPlayerCyborg() {
		if (playerCyborg == null)
			playerCyborg = new PlayerCyborg();
		return playerCyborg;
	}

	public String toString() {
		return "Player " + super.toString();
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
