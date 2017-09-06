import java.awt.Rectangle;
import java.util.ArrayList;


public class Collision implements Runnable, Constants{
	private Darklight darklight;
	private Map map;
	private Player player;
	
	
	public Collision(Darklight d, Map map, Player player){
		this.darklight = d;
		this.map = map;
		this.player = player;
	}
	
	@Override
	public void run() {
		while(true){
			try{
				/*Finding the hit boxes*/
				ArrayList<Rectangle> bullets = new ArrayList<Rectangle>();
				for(Projectile p : map.getProjectiles())
					if(p != null)
						bullets.add(p.getHitBox());
				Rectangle playerBox = player.getHitBox(), enemyBox = null, weaponBox = null;
				if(map.getObject() != null && map.getObject() instanceof Enemy){
					enemyBox = ((Enemy)map.getObject()).getHitBox();
					/*Checking collision between player bullets and enemy*/
					if(bullets.size() != 0){
						for(int i = 0; i < bullets.size(); i++){
							if(map.getObject() == null) break;
							Rectangle r = bullets.get(i);
							if(r.intersects(enemyBox)){
								map.getProjectiles().remove(i);
								i--;
								Enemy temp = (Enemy)map.getObject();
								temp.getStats().setHealth(temp.getStats().getHealth() 
										- player.getWeapon().getWeaponStats().getDamage());
								if(temp.getStats().getHealth() <= 0){
									map.setDisplayedObject(0);
									if(temp.returnSelf() == LUCIFER)
										darklight.setLuciferAlive(false);
								}
							}
						}
					}
				}
				if(map.getObject() != null && map.getObject() instanceof Weapon){
					weaponBox = ((Weapon)map.getObject()).getHitBox();
					/*Check collision between player and weapon*/
					if(weaponBox.intersects(playerBox)){
						player.setWeapon((Weapon)map.getObject());
						map.setDisplayedObject(0);
					}
				}
				/*Checking collision between enemy and player*/
				if(enemyBox != null){
					if(enemyBox.intersects(playerBox) && ((Enemy)map.getObject()).canDamage()){
						player.getStats().setHealth(player.getStats().getHealth() - 
								((Enemy)map.getObject()).getStats().getDamage());
						((Enemy)map.getObject()).setCanDamage(false);
					}
				}
				/*Check if exit is reached*/
				if(map.getLevel().getLocationRow() == map.getLevel().getArraySize() - 1 
						&& map.getLevel().getLocationCol() == map.getLevel().getArraySize() - 1){
					darklight.setStillOnLevel(!map.getPlayer().getHitBox().intersects(new Rectangle(1600, 800, 200, 200)));
				}
			} catch (Exception e){
			}
		}
	}

	
}
