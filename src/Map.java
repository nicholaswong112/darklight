import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Map extends JPanel implements Constants, Runnable, ActionListener{
	
	private Level level;
	private Player player;
	private DisplayedObject object;
	private ArrayList<Projectile> projectiles;
	private Timer timer;
	private int count1, count2;
	private Image fullHeart, emptyHeart;
	private boolean levelChanged;
	private String message;
	
	public Map(Player player){
		this.player = player;
		timer = new Timer(16, this);
		object = null;
		projectiles = new ArrayList<Projectile>();
		setSize(1800, 1000);
		level = new Level(GRAVEYARD, this);
		count1 = 0;
		count2 = 0;
		fullHeart = loadImage("Graphics/fullHeart.png");
		emptyHeart = loadImage("Graphics/emptyHeart.png");
		levelChanged = false;
		message = "";
		setVisible(true);
	}
	
	public Image loadImage(String fileName) {
		ImageIcon temp = new ImageIcon(fileName);
		return temp.getImage();
	}
	
	@Override
	public void run() {
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(timer))
			repaint();
	}
	
	public void generateNewMap(int type){
		level = new Level(type, this);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		
		try{
			if(count1 < player.getWeapon().getWeaponStats().getFireRate() / 16){
				count1++;
			} else if(!player.canFire()){
				player.setCanFire(true);
				count1 = 0;
			}
			
			Graphics2D g2 = (Graphics2D) g;
			
			int i = level.getMinRow(), j = level.getMinCol();
			
			//DRAWING THE BLOCKS
			out:
				for(int yCoor = 0; yCoor < this.getHeight(); yCoor += level.getBlockSize()){
					j = level.getMinCol();
					for(int xCoor = 0; xCoor < this.getWidth(); xCoor += level.getBlockSize()){
						if(i > level.getMaxRow() || j > level.getMaxCol()) break out;
						g2.drawImage(level.getLevelArray()[i][j].getImage(),xCoor,yCoor,this);
						j++;
					}
					i++;
				}
			
			
			//RENDERING PLAYER'S SPRITE
			player.move();
			g2.drawImage(player.getSprite(), player.getX(), player.getY(), this);
			
			
			//RENDERING ENEMY/WEAPON
			if(object != null){
				if(object instanceof Enemy){
					Enemy temp = (Enemy) getObject();
					if(temp.returnSelf() == LUCIFER){
						((Lucifer)temp).move();
						g2.drawImage(temp.getSprite(), temp.getX(), temp.getY(), this);
					} else { //ENEMY OTHER THAN LUCIFER
						temp.move();
						g2.drawImage(temp.getSprite(), temp.getX(), temp.getY(), this);
						if(!temp.canDamage()  && count2 < 1000/16){
							count2++;
						}else if(!temp.canDamage()){
							temp.setCanDamage(true);
							count2 = 0;
						}
					}
				} else {
					Weapon temp = (Weapon) getObject();
					g2.drawImage(temp.getSprite(), temp.getX(), temp.getY(), this);
				}
			}
			
			//CODE FOR THREATENING HELL MESSAGES
			if(levelChanged && object == null && level.getId() == HELL){
				setMessage("");
				int temp = (int)(Math.random() * 10); //10 different messages
				switch(temp){
				case 0:
					setMessage("TRY AND FIND ME");
					break;
				case 1:
					setMessage("ARE YOU LOST, LITTLE BOY?");
					break;
				case 2:
					setMessage("JUST GIVE UP");
					break;
				case 3:
					setMessage("YOU ARE MORTAL. I AM NOT.");
					break;
				case 4:
					setMessage("HAHAHAHAHAHAHA");
					break;
				case 5:
					setMessage("GO HOME");
					break;
				case 6:
					setMessage("I WILL CRUSH YOU");
					break;
				case 7:
					setMessage("YOU ARE INSIGNIFICANT");
					break;
				case 8:
					setMessage("MY FIRE WILL BURN YOU");
					break;
				case 9:
					setMessage("I COMMAND YOU TO DIE");
					break;
				}
				levelChanged = false;
			}
			
			if(object == null && level.getId() == HELL){
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("SansSerif", Font.PLAIN, 75));
				g2.drawString(getMessage(), 400, 150);
			}
			
			//PROCESSING PROJECTILES
			for(int a = 0; a < getProjectiles().size(); a++){
				Projectile temp = getProjectiles().get(a);
				if(temp.getDirection() == UP && temp.getY() < temp.getYLimit()){
					getProjectiles().remove(a);
					a--;
				}
				if(temp.getDirection() == RIGHT && temp.getX() > temp.getXLimit()){
					getProjectiles().remove(a);
					a--;
				}
				if(temp.getDirection() == DOWN && temp.getY() > temp.getYLimit()){
					getProjectiles().remove(a);
					a--;
				}
				if(temp.getDirection() == LEFT && temp.getX() < temp.getXLimit()){
					getProjectiles().remove(a);
					a--;
				}
			}
			
			//RENDERING PROJECTILES
			if(getProjectiles().size() != 0){
				for(Projectile p : getProjectiles()){
					p.move();
					g2.setColor(p.getColor());
					g2.fillOval(p.getX(), p.getY(), p.getWIDTH(), p.getHEIGHT());
				}
			}
			
			//RENDERING STATUS BAR
			//HEARTS
			for(int x = 10; x < 50 * player.getStats().getHealthSlots() + 10; x += 50){
				g2.drawImage(emptyHeart, x, 10, this);
			}
			for(int x = 10; x < 50 * player.getStats().getHealth() + 10; x += 50){
				g2.drawImage(fullHeart, x, 10, this);
			}
			//WEAPON EQUIPPED
			String weaponName = "";
			switch(player.getWeapon().returnSelf()){
			case(FINGER_GUN):
				weaponName = "Finger Gun";
				break;
			case(WATER_GUN):
				weaponName = "Water Gun";
				break;
			case(PAINTBALL_GUN):
				weaponName = "Paintball Gun";
				break;
			case(PISTOL):
				weaponName = "Pistol";
				break;
			case(SNIPER_RIFLE):
				weaponName = "Sniper Rifle";
				break;
			case(ASSAULT_RIFLE):
				weaponName = "Assault Rifle";
				break;
			case(LASER_GUN):
				weaponName = "Laser Gun";
				break;
			}
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("SansSerif", Font.PLAIN, 40));
			g2.drawString(weaponName, 500, 40);
			
			
		} catch (Exception e){
		}
	}
	
	public synchronized Level getLevel(){
		return level;
	}
	
	public synchronized Player getPlayer(){
		return player;
	}
	
	public synchronized DisplayedObject getObject(){
		return object;
	}
	
	public synchronized ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	public void setDisplayedObject(int identity){
		if(identity < 0){ //IF IT IS A MONSTER
			level.setLocked(level.getLocationRow(), level.getLocationCol(), true);
			if(identity == LUCIFER){
				object = new Lucifer(identity, this);
			} else {
				object = new Enemy(identity, this);
			}
		} else if(identity > 0){ //IT IS A WEAPON
			object = new Weapon(identity);
		} else if(identity == 0){ //IT IS DEAD
			object = null;
			level.setLocked(level.getLocationRow(), level.getLocationCol(), false);
		}
	}

	public void setLevelChanged(boolean bool){
		levelChanged = bool;
	}
	
	public synchronized void setMessage(String str){
		this.message = str;
	}
	
	public synchronized String getMessage(){
		return this.message;
	}

}
