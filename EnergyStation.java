package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed {

	// instance variable
	private int capacity;

	// constructor
	public EnergyStation(int size, int r, int g, int b, Point location, int capacity) {
		super(size, r, g, b, location);
		this.capacity = capacity;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		System.out.println(
				"Energy Station! This is my pointer location: " + pPtrRelPrnt.getX() + " " + pPtrRelPrnt.getY());
//		int leftBound = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() - this.getSize() / 2);
//		int rightBound = (int) (this.getLocation().getX() + pCmpRelPrnt.getX() + this.getSize() / 2);
//		int upperBound = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() + this.getSize() / 2);
//		int lowerBound = (int) (this.getLocation().getY() + pCmpRelPrnt.getY() - this.getSize() / 2);
//		if (leftBound < pPtrRelPrnt.getX() && rightBound > pPtrRelPrnt.getX()) {
//			if (lowerBound < pPtrRelPrnt.getY() && upperBound > pPtrRelPrnt.getY()) {
//				return true;
//			}
//		}
//		return false;
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();

//		// get location of object
//		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (this.getSize() / 2));
//		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (this.getSize() / 2));
		
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX());
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY());

		// check to see if the pointer is in the object
		if ((px >= xLoc) && (px <= xLoc + this.getSize()) && (py >= yLoc) && (py <= yLoc + this.getSize())) {
			return true;
		}
		return false;
		
//		// get pointer location
//		int px = (int) pPtrRelPrnt.getX();
//		int py = (int) pPtrRelPrnt.getY();
//
//		// get location of object
//		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (this.getSize() / 2));
//		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (this.getSize() / 2));
//		
//		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX());
//		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY());
//
//		// check to see if the pointer is in the object
//		if ((px >= xLoc) && (px <= xLoc + this.getWidth()) && (py >= yLoc) && (py <= yLoc + this.getHeight())) {
//			return true;
//		}
//		return false;
	}
	// Energy stations are filled circles
	// Size attribute of an energy station indicates the diameter of the circle
	// The initial capacity of an energy station is proportional to its size and the
	// size of an energy station remains the same even as its capacity decreases.
	// To draw a filled circle with radius r at location (x,y)use fillArc(x, y, 2*r,
	// 2*r, 0, 360).

	// This method uses the location and size attributes of the object to determine
	// where to draw the object so it's center coincides with it's location (i.e.
	// center_location.x - size/2, center_location.y - size/2) relative
	// to the origin of the MapView, which is the origin of the game world
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + super.getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + super.getLocation().getY());
		int r = super.getSize() / 2; // size indicates diameter, so divide it by 2 to get the radius
		g.setColor(getColor());
		if (isSelected()) {
			g.drawArc(x, y, 2 * r, 2 * r, 0, 360);
		} else {
			g.fillArc(x, y, 2 * r, 2 * r, 0, 360);
		}
		g.setColor(ColorUtil.BLACK);
		Point textLocation = new Point(
				(int) (this.getLocation().getX() + pCmpRelPrnt.getX() - (g.getFont().getPixelSize() / 2)),
				(int) (this.getLocation().getY() + pCmpRelPrnt.getY() - g.getFont().getPixelSize() / 2));
		g.drawString(Integer.toString(this.capacity), (int) textLocation.getX(), (int) textLocation.getY() - 15);
	}

	// getter method
	public int getCapacity() {
		return this.capacity;
	}

	// setter method
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// toString method
	public String toString() {
		return "EnergyStation: " + super.toString() + "Capacity: " + this.capacity;
	}

	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub

	}

}
