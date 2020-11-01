package com.mycompany.a3;

import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject implements IDrawable, ICollider {

	// an array list to keep track of the collided objects
	List<ICollider> collidedList = new ArrayList<ICollider>();

	// instance variables
	private int size, color;
	private Point location;
	private int width = 1609;
	private int height = 1255;

//	public GameObject() { }
	// constructor
	public GameObject(int size, int r, int g, int b, Point location) {
		this.size = size;
		this.color = ColorUtil.rgb(r, g, b);
		this.location = location;
	}

	public abstract void draw(Graphics g, Point pCmpRelPrnt);

	public boolean collidesWith(ICollider otherObject) {

		GameObject otherCollider = (GameObject) otherObject;
		if (otherCollider == this)
			return false;
		if (Math.abs(otherCollider.location.getX() - this.location.getX()) < (otherCollider.size / 2 + this.size / 2)
				&& (Math.abs(otherCollider.location.getY() - this.location.getY()) < (otherCollider.size / 2
						+ this.size / 2))) {
			if (!collidedList.contains(otherObject)) {
				this.handleCollision(otherObject);
			}
			collidedList.add(otherObject);
			return true;
		} else {
			collidedList.remove(otherObject);
			return false;
		}

//		int otherX = (int) otherCollider.getLocation().getX();
//		int otherY = (int) otherCollider.getLocation().getY();
//		int currX = (int) this.getLocation().getX();
//		int currY = (int) this.getLocation().getY();
//
//		int distanceX = currX - otherX;
//		int distanceY = currY - otherY;
//		int betweenD = distanceX * distanceX + distanceY * distanceY;
//
//		int currRadius = this.getSize() / 2;
//		int otherRadius = otherCollider.getSize() / 2;
//		int radiusDouble = (currRadius * currRadius + 2 * currRadius * otherRadius + otherRadius * otherRadius); // a^2
//																													// +
//																													// 2ab
//																													// +
//																													// b^2
//		if (betweenD <= radiusDouble) {
//			if (!collidedList.contains(otherObject)) {
//				this.handleCollision(otherObject);
//				collidedList.add(otherObject);
//				return true;
//			}
//		} else {
//			collidedList.remove(otherObject);
//			return false;
//		}
//		return false;
	}

	public void handleCollison(ICollider otherObject) {

	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		return false;
	}

	// getter methods
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getSize() {
		return this.size;
	}

	public int getColor() {
		return this.color;
	}

	public Point getLocation() {
		return this.location;
	}

	// setter methods, cannot set size
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}

	// check to see if the new points are in bound and update the values
	public void setLocation(Point location) {
		this.location = location;
	}

	public String toString() {
		return "Size: " + this.size + " Color: " + this.color + " Location: (" + this.location.getX() + ", "
				+ this.location.getY() + ") ";
	}
}
