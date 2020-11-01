package com.mycompany.a3;
import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject{

	//instance variables, heading needs to accessed by steerable objects
	private int speed;
	protected int heading;
	
//	public Movable() { }
	//constructor
	public Movable(int size, int r, int g, int b, Point location, int speed, int heading) {
		super(size, r, g, b, location);
		this.speed = speed;
		this.heading = heading;
	}

	//the move method
	public void move(int rate) {
		// calculate update for x and y
		int width = GameWorld.getGameWorld().getX();
		int height = GameWorld.getGameWorld().getY();
		
	
		float dist = (float) (getSpeed() * (rate/1000.0));
		float xCoordinate = (float) (super.getLocation().getX()) +(dist * (float) Math.cos(Math.toRadians((double) 90.0 - getHeading())));
		float yCoordinate = (float) (super.getLocation().getY()) +(dist * (float) Math.sin(Math.toRadians((double) 90.0 - getHeading())));
		
		if ((xCoordinate + this.getSize()/2) > width) {
			xCoordinate = width - this.getSize()/2;
		}else if ((xCoordinate - this.getSize()/2)  < 0) {
			xCoordinate = this.getSize()/2;
		}
		
		if ((yCoordinate + this.getSize()/2)  > height) {
			yCoordinate = height - this.getSize();
		}else if ((yCoordinate - this.getSize()/2) < 0) {
			yCoordinate = this.getSize()/2;
		}	
		
		Point newLocation = new Point(xCoordinate, yCoordinate);
		this.setLocation(newLocation);
	}
	
		
//		float currX = this.getLocation().getX();
//		float currY = this.getLocation().getY();
//		float deltaX = 0;
//		float deltaY = 0;
//		
//		// calculate new location
//		float degreesToRadians = (float) Math.toRadians(90 - heading);
//		if(heading == 0 || heading == 180) 
//			deltaY = (float) (Math.sin(degreesToRadians)*speed*rate);
//		else if(heading == 90 || heading == 270)
//			deltaX = (float) (Math.cos(degreesToRadians)*speed*rate);
//		else {
//			deltaX = (float) (Math.cos(degreesToRadians)*speed*rate);
//			deltaY = (float) (Math.sin(degreesToRadians)*speed*rate);
//		}
//		
//		// calculate newX and newY
//		float newX = currX + deltaX;
//		float newY = currY + deltaY;
//		
//		// height and width of map view
//		int width = this.getWidth();
//		int height = this.getHeight();
//		
//		// bounds checking
//		if(newX >= width)
//			newX = width;
//		if(newX <= 50)
//			newX = 50;
//		if(newY >= height)
//			newY = height;
//		if(newY <= 50)
//			newY = 50;
//		
//		//create a new point with the new coordinates
//		Point newLocation = new Point(newX, newY);
//		this.setLocation(newLocation); // set the location
	
	//getter methods
	public int getSpeed() {
		return this.speed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	//setter method
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setHeading(int newHeading) {
		heading = newHeading;
		if(heading >= 360) 
			heading -= 360;
		if(heading <= 0)
			heading += 360;
	}
	
	public String toString() {
		return super.toString() + " Speed: " + this.speed + " Heading: " + this.heading;
	}
}
