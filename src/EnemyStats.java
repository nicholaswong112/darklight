
public class EnemyStats implements Constants{
	private int id, health, damage, speed;
	
	public EnemyStats(int identity){
		id = identity;
		if(identity == REGULAR_ZOMBIE){
			health = 1;
			damage = 1;
			speed = 3;
		}else if(identity == TURRET_ZOMBIE){
			health = 2;
			damage = 2;
			speed = 0;
		}else if(identity == REAPER){
			health = 5;
			damage = 2;
			speed = 5;
		}else if(identity == RAT){
			health = 4;
			damage = 2;
			speed = 4;
		}else if(identity == CROC){
			health = 10;
			damage = 4;
			speed = 4;
		}else if(identity == CONVICT){
			health = 9;
			damage = 3;
			speed = 4;
		}else if(identity == GUARD){
			health = 9;
			damage = 3;
			speed = 4;
		}else if(identity == WARDEN){
			health = 15;
			damage = 5;
			speed = 5;
		}else if(identity == BAT){
			health = 6;
			damage = 3;
			speed = 5;
		}else if(identity == BEELZEBUB){
			health = 17;
			damage = 6;
			speed = 7;
		}else if(identity == CTHULU){
			health = 17;
			damage = 6;
			speed = 7;
		}else{
			health = 30;
			damage = 8;
			speed = 10;
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public int getSpeed() {
		return speed;
	}
}
