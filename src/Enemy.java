
import javax.swing.*;

import java.awt.*;


public class Enemy implements Constants, DisplayedObject{
	
	private Image sprite;
	private int id, x, y, width, height;
	private EnemyStats stats;
	private Map map;
	private boolean canDamage;
	
	public Enemy(int identity, Map map){  //TODO create and reference sprites
		id = identity;
		this.map = map;
		stats = new EnemyStats(identity);
		x = CENTER_COL_PIXEL;
		y = CENTER_ROW_PIXEL;
		canDamage = true;
		
		if(identity == REGULAR_ZOMBIE){
			sprite = loadImage("Graphics/Enemies/regularzombie.png");
		} else if(identity == TURRET_ZOMBIE){
			sprite = loadImage("Graphics/Enemies/turretZombie.png");
		} else if(identity == REAPER){
			sprite = loadImage("Graphics/Enemies/reaper.png");
		} else if(identity == RAT){
			sprite = loadImage("Graphics/Enemies/rat.png");
		} else if(identity == CROC){
			sprite = loadImage("Graphics/Enemies/croc.png");
		} else if(identity == CONVICT){
			sprite = loadImage("Graphics/Enemies/convict.png");
		} else if(identity == GUARD){
			sprite = loadImage("Graphics/Enemies/guard.png");
		} else if(identity == WARDEN){
			sprite = loadImage("Graphics/Enemies/warden.png");
		} else if(identity == BAT){
			sprite = loadImage("Graphics/Enemies/bat.png");
		} else if(identity == BEELZEBUB){
			sprite = loadImage("Graphics/Enemies/beelzebub.png");
		} else if(identity == CTHULU){
			sprite = loadImage("Graphics/Enemies/cthulu.png");
		} else if(identity == LUCIFER){
			sprite = loadImage("Graphics/Enemies/lucifer.png");
		}
	}

	public Image loadImage(String fileName) {
		ImageIcon temp = new ImageIcon(fileName);
		width = temp.getIconWidth();
		height = temp.getIconHeight();
		return temp.getImage();
	}
	
	public void move(){
		int px = map.getPlayer().getX() + 20;
		int py = map.getPlayer().getY() + 30;
		int dx = 0;
		int dy = 0;
		if(px > x) dx = stats.getSpeed();
		if(px < x) dx = -1 * stats.getSpeed();
		if(py > y) dy = stats.getSpeed();
		if(py < y) dy = -1 * stats.getSpeed();
		
		x += dx;
		y += dy;
	}
	
	public boolean canDamage(){
		return canDamage;
	}
	
	public void setCanDamage(boolean bool){
		this.canDamage = bool;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

    public Image getSprite() {
        return sprite;
    }
    
    public synchronized EnemyStats getStats(){
    	return stats;
    }
    
    public synchronized Map getMap(){
    	return map;
    }

	@Override
	public synchronized Rectangle getHitBox(){
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public int returnSelf() {
		return id;
	}
	
}
