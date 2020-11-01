package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Cyborg extends Movable implements ISteerable {

	// instance variable
	private int steerDirection, maxSpeed, energyLevel, energyConsumptionRate, damageLevel, maxDamage, lastBaseReached;

//	public Cyborg() { }
	
	//constructor
	public Cyborg(int size, int r, int g, int b, Point location, int speed, int heading, int steerDirection,
			int maxSpeed, int energyLevel, int energyConsumptionRate, int damageLevel, int maxDamage, int lastBaseReached) {
		super(size, r, g, b, location, speed, heading);
		this.steerDirection = steerDirection;
		this.maxSpeed = maxSpeed;
		this.energyLevel = energyLevel;
		this.energyConsumptionRate = energyConsumptionRate;
		this.damageLevel = damageLevel;
		this.maxDamage = maxDamage;
		this.lastBaseReached = lastBaseReached;
	}

	public void changeHeading(int heading) {
		this.heading += heading;
	}

	// getter methods
	public int getSteerDirection() {
		return this.steerDirection;
	}

	// damage level increases every time Cyborg collides
	public void damageByCyborg() {
		this.damageLevel += 2;
		validateSpeed();
	}
	
	//damage level increases half of damage by Cyborg
	public void damageByDrone() {
		this.damageLevel += 1;
		validateSpeed();
	}

	// the speed can never be more than the maximum speed, therefore if greater then
	// set it to Max speed
	public void validateSpeed() {
		int currentMax = getMaxSpeed();
		if (this.getSpeed() > currentMax)
			this.setSpeed(currentMax);
	}

	// returns the maximum speed it can reach after the damages caused
	public int getMaxSpeed() {
		return this.maxSpeed - (this.damageLevel * 2);
	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public int getEnergyConsumptionRate() {
		return this.energyConsumptionRate;
	}

	public int getDamageLevel() {
		return this.damageLevel;
	}
	
	public int getMaxDamage() {
		return this.maxDamage;
	}

	public int getLastBaseReached() {
		return this.lastBaseReached;
	}

	// setter methods
	public void setSteerDirection(int steerDirection) {
		this.steerDirection = steerDirection;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public void setEnergyConsumptionRate(int energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}

	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}
	
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}

	public String toString() {
		return "Cyborg: " + super.toString() + " Steering Direction: " + this.steerDirection + " Max Speed: " + this.maxSpeed + " Energy Level: " + this.energyLevel + " Energy Consumption: "
				+ this.energyConsumptionRate + " Damage Level: " + this.damageLevel + " Max Damage Level: " + this.maxDamage + " Last Base Reached: " + this.lastBaseReached;
	}
}
