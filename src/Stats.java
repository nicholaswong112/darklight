import javax.swing.*;
import java.awt.*;

public class Stats {
	private int speed, health, healthSlots;
	
	public Stats(){
		speed = 10; //IN PIXELS
		health = 5;
		healthSlots = 5;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthSlots() {
		return healthSlots;
	}

	public void setHealthSlots(int healthSlots) {
		this.healthSlots = healthSlots;
	}
	
	
}
