import javax.swing.*;

import java.awt.*;


public class Weapon implements Constants, DisplayedObject{
	private int id, x, y, width, height;
	private Image sprite;
	private boolean pickedUp;
	private WeaponStats stats;
	
	public Weapon(int identity){
		id = identity;
		stats = new WeaponStats(identity);
		x = CENTER_COL_PIXEL;
		y = CENTER_ROW_PIXEL;

		if(identity == FINGER_GUN){
			sprite = loadImage("Graphics/Weapons/fingerGun.png");
		}else if(identity == WATER_GUN){
			sprite = loadImage("Graphics/Weapons/waterGun.png");
		}else if(identity == PAINTBALL_GUN){
			sprite = loadImage("Graphics/Weapons/paintballGun.png");
		}else if(identity == PISTOL){
			sprite = loadImage("Graphics/Weapons/pistol.png");
		}else if(identity == SNIPER_RIFLE){
			sprite = loadImage("Graphics/Weapons/sniperRifle.png");
		}else if(identity == ASSAULT_RIFLE){
			sprite = loadImage("Graphics/Weapons/assaultRifle.png");
		}else if(identity == LASER_GUN){
			sprite = loadImage("Graphics/Weapons/laserGun.png");
		}
		
	}
	
	public Image loadImage(String fileName) {
		ImageIcon temp = new ImageIcon(fileName);
		width = temp.getIconWidth();
		height = temp.getIconHeight();
		return temp.getImage();
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public WeaponStats getWeaponStats(){
		return stats;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	@Override
	public synchronized Rectangle getHitBox(){
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public int returnSelf(){
		return id;
	}
}
