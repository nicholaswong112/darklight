
public class WeaponStats implements Constants{
	private final int damage;
	private final int bulletSpeed; //in pixels
	private final int fireRate; //delay time in milliseconds
	private final int range; //in pixels
	
	public WeaponStats(int identity){
		if(identity == FINGER_GUN){
			damage = 1;
			bulletSpeed = 15;
			fireRate = 750;
			range = 500;
		}else if(identity == WATER_GUN){
			damage = 2;
			bulletSpeed = 15;
			fireRate = 500;
			range = 550;
		}else if(identity == PAINTBALL_GUN){
			damage = 2;
			bulletSpeed = 20;
			fireRate = 300;
			range = 600;
		}else if(identity == PISTOL){
			damage = 4;
			bulletSpeed = 25;
			fireRate = 450;
			range = 650;
		}else if(identity == SNIPER_RIFLE){
			damage = 7;
			bulletSpeed = 20;
			fireRate = 1000;
			range = 1800;
		}else if(identity == ASSAULT_RIFLE){
			damage = 3;
			bulletSpeed = 25;
			fireRate = 333;
			range = 800;
		}else{
			damage = 10;
			bulletSpeed = 50;
			fireRate = 200;
			range = 1800;
		}
	}

	public int getDamage() {
		return damage;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
	}

	public int getFireRate() {
		return fireRate;
	}
	
	public int getRange(){
		return range;
	}
	
}
