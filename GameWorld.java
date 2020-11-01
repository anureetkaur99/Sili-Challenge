/* Anureet Kaur
 * CSC 133 Sili Challenge
 */
package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import java.util.Observable;

public class GameWorld extends Observable {

	// instance variables
	private GameObjectCollection objectList;
	private int x, y, lives, clock;
	private boolean sound;
	private ICollider lastCollideObject;
	PlayerCyborg player;
	private static GameWorld gw;
	private boolean position;
	
	private Sound npcCollisionSound;
	private Sound energyCollisionSound;
	private Sound droneCollisionSound;

	public static GameWorld getGameWorld() {
		if (gw == null)
			gw = new GameWorld();
		return gw;
	}

	// constructor
	private GameWorld() {
		this.lives = 3;
		this.clock = 0;
		this.sound = false;
		this.position = false;
	}

	public void init() {
		Random rand = new Random();
		// initializing an empty ObjectCollection so that when we call it multiple
		// times, the
		// objectList from attempt 1, don't get concatenated
		objectList = new GameObjectCollection();

		// construct 4 bases;
		Point baseOneLocate = new Point(200, 700);
		Point baseTwoLocate = new Point(500, 300);
		Point baseThreeLocate = new Point(700, 600);
		Point baseFourLocate = new Point(900, 400);
		Base baseOne = new Base(150, 0, 0, 255, baseOneLocate, 1);
		objectList.add(baseOne);

		// make the last collide object equal to first base because the player starts
		// from first base
		lastCollideObject = baseOne;

		Base baseTwo = new Base(150, 0, 0, 255, baseTwoLocate, 2);
		objectList.add(baseTwo);
		Base baseThree = new Base(150, 0, 0, 255, baseThreeLocate, 3);
		objectList.add(baseThree);
		Base baseFour = new Base(150, 0, 0, 255, baseFourLocate, 4);
		objectList.add(baseFour);

		// Singleton pattern: construct 1 Player Cyborg
		player = PlayerCyborg.getPlayerCyborg();
		objectList.add(player);

		// construct 3 NonPlayerCyborgs, location is near 1st base (not exactly 1st
		// base)
		Point npcOneLocate = new Point(250, 30);
		Point npcTwoLocate = new Point(170, 100);
		Point npcThreeLocate = new Point(150, 200);
		NonPlayerCyborg nonPlayer1 = new NonPlayerCyborg(130, 255, 0, 0, npcOneLocate, 150, 0, 0, 1000, 10000, 5, 0, 100,
				1, new DirectStrategy());
		objectList.add(nonPlayer1);
		NonPlayerCyborg nonPlayer2 = new NonPlayerCyborg(130, 255, 0, 0, npcTwoLocate, 150, 0, 0, 1000, 10000, 5, 0, 100,
				1, new AttackStrategy());
		objectList.add(nonPlayer2);
		NonPlayerCyborg nonPlayer3 = new NonPlayerCyborg(130, 255, 0, 0, npcThreeLocate, 150, 0, 0, 1000, 10000, 5, 0, 100,
				1, new DirectStrategy());
		objectList.add(nonPlayer3);

		// construct 2 drones
		for (int i = 1; i <= 2; i++) {
			int size = rand.nextInt(110) + 65;
			int speed = rand.nextInt(100) + 200;
			int heading = rand.nextInt(360);
			Drone drones = new Drone(size, 0, 0, 0, getRandLocation(rand), speed, heading);
			objectList.add(drones);
		}

		// construct 2 energy stations
		for (int i = 1; i <= 2; i++) {
			int size = rand.nextInt(110) + 100;
			EnergyStation eStation = new EnergyStation(size, 0, 255, 0, getRandLocation(rand), size);
			objectList.add(eStation);
		}

		// notify observers of initial state
		this.updateObservers();
	}

	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;

	}

	public void setClock(int time) {
		clock = time;
	}

	public void setPosition() {
		this.position = !this.position;
	}

	// getters
	public GameObjectCollection getCollection() {
		return objectList;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getLives() {
		return lives;
	}

	public int getClock() {
		return clock;
	}

	public boolean getSound() {
		return sound;
	}

	public boolean getPosition() {
		return this.position;
	}

	public void updateObservers() {
		this.setChanged();
		this.notifyObservers();
	}

	public void toggleSound() {
		this.sound = !this.sound;
		this.updateObservers();
	}

	// the nextFloat function always gives you a number from 0.0 to 1.0 (its always
	// a decimal number). So 1000*0.5 will be 500
	public Point getRandLocation(Random rand) {

		float xLocation;
		float yLocation;
		do {
			xLocation = rand.nextFloat() * x;
			yLocation = rand.nextFloat() * y;
		} while (xLocation >= x || yLocation >= y || xLocation <= 0 || yLocation <= 0);

		return new Point(xLocation, yLocation);
	}

	// every time there is a damage or when the level of health is low, the lives
	// are subtracted
	public void damageLives() {
		System.out.println("Went to damageLives method to subtract a live!");
		this.lives--;
		resetDamage();
		if (lives == 0) {
			System.out.println("GAME OVER!");
			System.exit(0);
		}
		init();
	}

	public void createSounds() {
		npcCollisionSound = new Sound("CRASH1.wav");
		energyCollisionSound = new Sound("electric1.wav");
		droneCollisionSound = new Sound("explosio.wav");
	}
	
	// accelerate the speed of cyborg
	public void accelerate() {
		int newSpeed = player.getSpeed() + 10;
		if (player.getEnergyLevel() != 0 && player.getDamageLevel() != 20 && player.getMaxSpeed() >= newSpeed) {
			player.setSpeed(newSpeed);
		}
		System.out.println("Acceleration:" + player.getSpeed());
		this.updateObservers();
	}

	// apply brakes to the cyborg
	public void brake() {
		int newSpeed = player.getSpeed() - 10;
		if (player.getMaxSpeed() >= newSpeed && player.getSpeed() != 0)
			player.setSpeed(newSpeed);
		System.out.println("Brake: " + player.getSpeed());
		this.updateObservers();
	}

	// steer left
	public void left() {
		int tempDirection = player.getSteerDirection() - 10;
		if (tempDirection >= -40) {
			player.setSteerDirection(tempDirection);
			player.changeHeading(-5);
			System.out.println("Left: " + player.heading);
			this.updateObservers();
		}
	}

	// steer right
	public void right() {
		int tempDirection = player.getSteerDirection() + 10;
		if (tempDirection <= 40) {
			player.setSteerDirection(tempDirection);
			player.changeHeading(5);
			System.out.println("right: " + player.heading);
			this.updateObservers();
		}
	}

	public void resetDamage() {
		player.setDamageLevel(0);
		IIterator it = objectList.getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof NonPlayerCyborg) {
				NonPlayerCyborg nonPlaya = (NonPlayerCyborg) ob;
				nonPlaya.setDamageLevel(0);
			}
		}
	}

	// when cyborg collides with another cyborg
	public void npcCollide() {
		//	npcCollisionSound.play();
		// affect on player cyborg
		player.damageByCyborg();
		int affectColorPc = player.getDamageLevel() * 10;
		player.setColor(155 + affectColorPc, 0, 0);
		if (player.getDamageLevel() >= player.getMaxDamage())
			damageLives();
		System.out.println("NPC Collision, Cyborg's damage: " + player.getDamageLevel());

		// affect on non player cyborg
		IIterator it = objectList.getIterator();
		Random rand = new Random();
		int counter = 1;
		int npcNum = rand.nextInt(3) + 1;
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof NonPlayerCyborg) {
				if (counter == npcNum) {
					NonPlayerCyborg nonPlaya = (NonPlayerCyborg) ob;
					nonPlaya.damageByCyborg(); // NPC also sustain a damage
					int affectColorNpc = nonPlaya.getDamageLevel() * 10;
					nonPlaya.setColor(155 + affectColorNpc, 0, 0);
					System.out.println("NPC Collision, NPC's damage: " + nonPlaya.getDamageLevel());
				}
				// counter that increments and whenever it is equal to the random number
				// generated then that is the NPC to get damaged
				// generate a random number between 1-3
				counter++;
			}
		}
		this.updateObservers();
	}

	// when cyborg collides with a base
	public void baseCollide(Base b) {
		int baseNum = b.getSeqNumber();
		// if cyborg reaches last base, it wins
		if (baseNum == player.getLastBaseReached() + 1) {
			IIterator it = objectList.getIterator();
			while (it.hasNext()) {
				GameObject ob = it.getNext();
				if (ob instanceof Base) {
					Base newB = (Base) ob;
					if (newB.getSeqNumber() == baseNum) {
						GameObject o = it.getNext();
						if (o instanceof PlayerCyborg) {
							player.setLastBaseReached(baseNum);
							player.setLocation(newB.getLocation());
							if (player.getLastBaseReached() == 4) {
								System.out.println("Player's Base reached: " + player.getLastBaseReached());
								System.out.println("YOU WON! Total time: " + clock);
								System.exit(0);
							} else if (o instanceof NonPlayerCyborg) { // if non-player reaches last base, you loose
								NonPlayerCyborg nonPlaya = (NonPlayerCyborg) o;
								if (nonPlaya.getLastBaseReached() == 4) {
									System.out.println("Non-Player's Base reached: " + nonPlaya.getLastBaseReached());
									System.out.println("Game over, a non-player cyborg wins!");
									System.exit(0);
								}
							}
						}
					}
				}
			}
		}
		this.updateObservers();

	}

	// cyborg collides with the energy station and its energy increases
	public void energyCollide(EnergyStation eStation) {
		Random rand = new Random();
		if (eStation.getCapacity() > 0) {
			// add code to play sound of colliding with energy station
		//		energyCollisionSound.play();
			// add energy stations capacity to cyborgs energy level
			int newEnergyLevel = player.getEnergyLevel() + eStation.getCapacity();
			IIterator it = objectList.getIterator();
			while (it.hasNext()) {
				GameObject o = it.getNext();
				if (o instanceof Cyborg) {
					Cyborg borg = (Cyborg) o;
					borg.setEnergyLevel(newEnergyLevel);
					// set energy level's capacity to 0
					eStation.setCapacity(0);
					// fade stations color to light green
					eStation.setColor(152, 251, 152);
					// make new energy station
					int size = rand.nextInt(40) + 10;
					EnergyStation newStation = new EnergyStation(size, 0, 255, 0, getRandLocation(rand), size);
					objectList.add(newStation);
					System.out.println("Energy: " + borg.getEnergyLevel());
				}
			}
		}
		this.updateObservers();
	}

	// when cyborg collides with drone
	public void droneCollide() {
		// play sound when colliding with spider
	//		droneCollisionSound.play();
		IIterator it = objectList.getIterator();
		while (it.hasNext()) {
			GameObject o = it.getNext();
			if (o instanceof Cyborg) {
				Cyborg borg = (Cyborg) o;
				borg.damageByDrone();
				int affectColor = player.getDamageLevel() * 10;
				borg.setColor(155 + affectColor, 0, 0);
				if (borg.getDamageLevel() >= borg.getMaxDamage())
					damageLives();
				System.out.println("Drone Collision, Damage: " + borg.getDamageLevel());
				this.updateObservers();
			}
		}
	}

	public void changeStrategy() {
		IIterator it = objectList.getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof NonPlayerCyborg) {
				NonPlayerCyborg nonPlaya = (NonPlayerCyborg) ob;
				if (nonPlaya.getCurrStrategy() instanceof DirectStrategy) {
					System.out.println("Strategy changed to Attack!");
					nonPlaya.setStrategy(new AttackStrategy());
				} else {
					System.out.println("Strategy changed to Direct!");
					nonPlaya.setStrategy(new DirectStrategy());
				}
			}
		}
	}

	public void updateNpcHeading() {
		IIterator it = objectList.getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof NonPlayerCyborg) {
				NonPlayerCyborg nonPlaya = (NonPlayerCyborg) ob;
				nonPlaya.invokeStrategy();
			}
		}
	}

	// tells the game world that the game clock has ticked, every tick moves the
	// movable objects and reduces Cyborg's energy level
	public void clockTick(int rate) {
		// call NPC update heading method for every frame
		updateNpcHeading();

		IIterator it = objectList.getIterator();
		while (it.hasNext()) {
			GameObject ob = it.getNext();
			if (ob instanceof Movable) {
				Movable moveIt = (Movable) ob;
				moveIt.move(rate); // all movable objects move
				if (moveIt instanceof Cyborg) {
					Cyborg playa = (Cyborg) moveIt;
					// update energy level based on energy consumption
					if (this.getClock() % 1000 == 0) {
						double newEnergy = playa.getEnergyLevel() - playa.getEnergyConsumptionRate();
						playa.setEnergyLevel((int) newEnergy);
					}
					if (playa.getEnergyLevel() <= 0)
						this.damageLives();
				}
			}
		}
		// increment the game clock
		int newClock = this.getClock() + rate;
		this.setClock(newClock);

		// look for a collision
		IIterator iter = objectList.getIterator();
		while (iter.hasNext()) {
			GameObject ob = iter.getNext();
			if (ob instanceof Cyborg) {
				Cyborg c = (Cyborg) ob;
				IIterator otherObs = objectList.getIterator();
				while (otherObs.hasNext()) {
					ICollider collide = otherObs.getNext();
					if (c.collidesWith(collide) && collide != lastCollideObject) {
	//					c.handleCollision(collide);
						lastCollideObject = collide;
					}
				}
			}
		}
		this.updateObservers();
	}

	public String toString() {
		return this.objectList + " " + this.x + " " + this.y + " " + this.lives + " " + this.clock + " " + this.sound;
	}

}
