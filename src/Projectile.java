import java.awt.*;


public class Projectile implements Constants, DisplayedObject{
	private final int WIDTH = 10, HEIGHT = 10;
	private Color color;
	private int x, y, dx, dy;
	private int xLimit, yLimit;
	private Player player;
	private int direction;

	public Projectile(Player player){
		this.player = player;
		x = player.getX();
		y = player.getY();
		dx = dy = 0;
		color = Color.WHITE;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
		if(dx > 0){
			xLimit = x + player.getWeapon().getWeaponStats().getRange();
			direction = RIGHT;
		}
		if(dx < 0){
			xLimit = x - player.getWeapon().getWeaponStats().getRange();
			direction = LEFT;
		}
	}

	public void setDy(int dy) {
		this.dy = dy;
		if(dy > 0){
			yLimit = y + player.getWeapon().getWeaponStats().getRange();
			direction = DOWN;
		}
		if(dy < 0){
			yLimit = y - player.getWeapon().getWeaponStats().getRange();
			direction = UP;
		}
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Player getPlayer() {
		return player;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getXLimit(){
		return xLimit;
	}
	
	public int getYLimit(){
		return yLimit;
	}
	
	public int getDirection(){
		return direction;
	}

	public void move(){
		x += dx;
		y += dy;
	}
	
	@Override
	public synchronized Rectangle getHitBox(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public int returnSelf() {
		return PROJECTILE;
	}
}
