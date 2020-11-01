package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Base extends Fixed {

	// instance variables
	private int sequenceNumber;

	// constructor
	public Base(int size, int r, int g, int b, Point location, int sequenceNumber) {
		super(size, r, g, b, location);
		this.sequenceNumber = sequenceNumber;
	}

	// Bases are filled isosceles triangles, the size of a base indicates the length
	// of the unequal side and height of the isosceles triangle.

	// This method uses the location and size attributes of the object to determine
	// where to draw the object so it's center coincides with it's location (i.e.
	// center_location.x - size/2, center_location.y - size/2) relative
	// to the origin of the MapView, which is the origin of the game world

	// Example: to draw a triangle with corner coordinates (1,1) (3,1) (2,2), you
	// should assign xPoints = {1,3,2}, yPoints = {1,1,2}, nPoints = 3.

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		System.out.println("Base! This is my pointer location: " + pPtrRelPrnt.getX() + " " + pPtrRelPrnt.getY());
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
		
		
		
		
//		int px = (int) pPtrRelPrnt.getX();
//		int py = (int) pPtrRelPrnt.getY();
//
//		// get location of object
//		int xLoc = (int) (pCmpRelPrnt.getX() + getLocation().getX() - (this.getSize() / 2));
//		int yLoc = (int) (pCmpRelPrnt.getY() + getLocation().getY() - (this.getSize() / 2));
//
//		// check to see if the pointer is in the object
//		if ((px >= xLoc) && (px <= xLoc + (getSize() * 10)) && (py >= yLoc) && (py <= yLoc + 100)) {
//			return true;
//		}
//		return false;
		
		
		
		
		// get pointer location
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
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
//		if (isSelected()) {
//			g.setColor(ColorUtil.WHITE);
//		} else {
//			g.setColor(this.getColor());
//		}
		// calculate upperPoint
		float x = pCmpRelPrnt.getX() + super.getLocation().getX();
		float y = pCmpRelPrnt.getY() + super.getLocation().getY() + (super.getSize() / 2);
		Point upperPoint = new Point(x, y);
		// System.out.println("upperPoint.x: " + upperPoint.getX());
		// System.out.println("upperPoint.y: " + upperPoint.getY());

		// calculate leftLowerPoint
		x = (pCmpRelPrnt.getX() + super.getLocation().getX() - (super.getSize() / 2));
		y = (pCmpRelPrnt.getY() + super.getLocation().getY() - (super.getSize() / 2));
		Point leftLowerPoint = new Point(x, y);

		// calculate rightLowerPoint
		x = (pCmpRelPrnt.getX() + super.getLocation().getX() + (super.getSize() / 2));
		y = (pCmpRelPrnt.getY() + super.getLocation().getY() - (super.getSize() / 2));
		Point rightLowerPoint = new Point(x, y);

		// put x and y points in the array
		int xPoints[] = new int[] { (int) upperPoint.getX(), (int) leftLowerPoint.getX(),
				(int) rightLowerPoint.getX() }; // array of integers that has x coordinates of the corners
		int yPoints[] = new int[] { (int) upperPoint.getY(), (int) leftLowerPoint.getY(),
				(int) rightLowerPoint.getY() }; // array of integers that has y coordinates of the corners
		int nPoints = 3; // number of corners of the polygon
		g.setColor(getColor());
		if (isSelected()) {
			g.drawPolygon(xPoints, yPoints, nPoints);
		} else {
			g.fillPolygon(xPoints, yPoints, nPoints);
		}
		g.setColor(ColorUtil.BLACK);
		Point textLocation = new Point(
				(int) (this.getLocation().getX() + pCmpRelPrnt.getX() - (g.getFont().getPixelSize() / 2)),
				(int) (this.getLocation().getY() + pCmpRelPrnt.getY() - g.getFont().getPixelSize() / 2));
		g.drawString(Integer.toString(this.sequenceNumber), (int) textLocation.getX() + 13,
				(int) textLocation.getY() + 17);
	}

	// getter method
	public int getSeqNumber() {
		return this.sequenceNumber;
	}

	// not allowed to change the color
	@Override
	public void setColor(int r, int g, int b) {
	}

	// toString method
	public String toString() {
		return "Base: " + super.toString() + " SequenceNum: " + this.sequenceNumber;
	}

	public void handleCollision(ICollider otherObject) {
	}

}
