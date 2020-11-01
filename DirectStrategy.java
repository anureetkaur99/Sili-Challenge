package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;


public class DirectStrategy implements IStrategy{

	private GameWorld gw;
	private String strategyName = "Direct Strategy";
	public int apply(NonPlayerCyborg nPC) {
		gw = GameWorld.getGameWorld();
		int newBase = nPC.getLastBaseReached() + 1;
		Point newBaseLocation = new Point(0,0);
		IIterator it = gw.getCollection().getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof Base) {
				Base b = (Base) ob;
				if (b.getSeqNumber() == newBase) {
					newBaseLocation = b.getLocation();
				}
			}
		}
		Point difference = new Point(); 
		difference.setX(newBaseLocation.getX() - nPC.getLocation().getX());
		difference.setY(newBaseLocation.getY() - nPC.getLocation().getY());
		int angle = (int) MathUtil.atan(difference.getY() / difference.getX());
		return angle;
	}
	
	public String toString() {
		return " Strategy: " + this.strategyName;
	}
}
