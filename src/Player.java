import javax.swing.*;

import java.awt.*;



public class Player implements Constants, DisplayedObject{
	
	private final int STARTING_X_COORDINATE = 775, STARTING_Y_COORDINATE = 320; //in pixels
	private Stats stats;
	private Image sprite;
	private Weapon weapon;
	private int dx, dy, x, y, width, height;
	private final int SPRITE_HEIGHT = 200, SPRITE_WIDTH = 167;
	private boolean canFire;
	
	public Player(){
		stats = new Stats();
		weapon = new Weapon(FINGER_GUN);
		sprite = loadImage("Graphics/player.png");
		canFire = true;
		dx = dy = 0;
		x = STARTING_X_COORDINATE;
		y = STARTING_Y_COORDINATE;
	}
	
	public Image loadImage(String fileName) {
		ImageIcon temp = new ImageIcon(fileName);
		width = temp.getIconWidth();
		height = temp.getIconHeight();
		return temp.getImage();
	}

	public void resetCoordinate(){
		x = STARTING_X_COORDINATE;
		y = STARTING_Y_COORDINATE;
	}
	
	public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public synchronized void setX(int x){
    	this.x = x;
    }
    
    public synchronized void setY(int y){
    	this.y = y;
    }
    
    public synchronized void setDx(int dx) {
		this.dx = dx;
	}

	public synchronized void setDy(int dy) {
		this.dy = dy;
	}
	
	public synchronized boolean canFire(){
		return canFire;
	}
	
	public synchronized void setCanFire(boolean bool){
		canFire = bool;
	}

	public synchronized Stats getStats(){
		return stats;
	}
	
	public synchronized Weapon getWeapon(){
		return weapon;
	}
	
	public synchronized void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
    public synchronized Image getSprite() {
        return sprite;
    }
	
	public int getSPRITE_HEIGHT() {
		return SPRITE_HEIGHT;
	}

	public int getSPRITE_WIDTH() {
		return SPRITE_WIDTH;
	}

	@Override
	public synchronized Rectangle getHitBox(){
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public int returnSelf() {
		return PLAYER;
	}
	
}
