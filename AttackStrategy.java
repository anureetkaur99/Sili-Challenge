package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {

	private String strategyName = "Attack Strategy";
	private GameWorld gw;

	public int apply(NonPlayerCyborg nPC) {
		gw = GameWorld.getGameWorld();
		Point newCyborgLocation = new Point(0,0);
		IIterator it = gw.getCollection().getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof PlayerCyborg) {
				PlayerCyborg playa = (PlayerCyborg) ob;
				newCyborgLocation = playa.getLocation();
			}
		}
		Point difference = new Point(); 
		difference.setX(newCyborgLocation.getX() - nPC.getLocation().getX());
		difference.setY(newCyborgLocation.getY() - nPC.getLocation().getY());
		int angle = (int) MathUtil.atan(difference.getY() / difference.getX());
		return angle;
	}

	public String toString() {
		return " Strategy: " + this.strategyName;
	}
}
