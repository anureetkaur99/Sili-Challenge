package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Drone extends Movable {

	private static Random rand = new Random();

	// constuctor
	public Drone(int size, int r, int g, int b, Point location, int speed, int heading) {
		super(size, r, g, b, location, speed, heading);
	}

	// not allowed to set the color
	@Override
	public void setColor(int r, int g, int b) {
	}

	// Drones are unfilled isosceles triangles, the size of a drone indicates the
	// length
	// of the unequal side and height of the isosceles triangle.
	// This method uses the location and size attributes of the object to determine
	// where to draw the object so it's center coincides with it's location (i.e.
	// center_location.x - size/2, center_location.y - size/2) relative
	// to the origin of the MapView, which is the origin of the game world

	// Example: to draw a triangle with corner coordinates (1,1) (3,1) (2,2), you
	// should assign xPoints = {1,3,2}, yPoints = {1,1,2}, nPoints = 3.
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
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
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void move(int rate) {
		// calculate update for x and y
		int width = GameWorld.getGameWorld().getX();
		int height = GameWorld.getGameWorld().getY();

		float dist = (float) (getSpeed() * (rate / 1000.0));
		float xCoordinate = (float) (super.getLocation().getX())
				+ (dist * (float) Math.cos(Math.toRadians((double) 90.0 - getHeading())));
		float yCoordinate = (float) (super.getLocation().getY())
				+ (dist * (float) Math.sin(Math.toRadians((double) 90.0 - getHeading())));

		if ((xCoordinate + this.getSize() / 2) > width) {
			xCoordinate = width - this.getSize() / 2;
		} else if ((xCoordinate - this.getSize() / 2) < 0) {
			xCoordinate = this.getSize() / 2;
		}

		if ((yCoordinate + this.getSize() / 2) > height) {
			yCoordinate = height - this.getSize();
		} else if ((yCoordinate - this.getSize() / 2) < 0) {
			yCoordinate = this.getSize() / 2;
		}
		Point newLocation = new Point(xCoordinate, yCoordinate);
		this.setLocation(newLocation);
		// bounce it back
		//this.setHeading(this.getHeading() + 180);
		Random random = new Random();
		int headingAdjustment = rand.nextInt(20) - 10;
		setHeading(getHeading() + headingAdjustment);
	}

	public String toString() {
		return "Drone: " + super.toString();
	}

	public void handleCollision(ICollider otherObject) {
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}
}
