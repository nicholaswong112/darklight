import javax.swing.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Darklight extends JFrame implements Constants, Runnable{
	private Map map;
	
	private Thread rendering;
	
	private Thread keyListening;
	
	private Thread collisionTesting;
	
	private Player player;
	
	private MovementListener moveListener;
	
	private AttackListener attackListener;
	
	private boolean stillOnLevel, luciferAlive;
	
	public Darklight(){
		player = new Player();
		addKeyListener(moveListener = new MovementListener());
		addKeyListener(attackListener = new AttackListener());
		setTitle("Darklight");
		setSize(1800, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(map = new Map(player));
		rendering = new Thread(map);
		keyListening = new Thread(this);
		collisionTesting = new Thread(new Collision(this, map, player));
		stillOnLevel = true;
		luciferAlive = true;
		setVisible(true);
	}
	

	@Override
	public void run() {
		for(int level = GRAVEYARD; level < HELL; level++){
			stillOnLevel = true;
			while(stillOnLevel){
				if(player.getStats().getHealth() <= 0){
					terminateGame();
				}
				if(checkPan() != NONE && !map.getLevel().isLocked()){
					map.getLevel().shift(checkPan());
				}
			}
			map.generateNewMap(level + 1);
			player.resetCoordinate();
		}
		while(luciferAlive){
			if(player.getStats().getHealth() <= 0){
				terminateGame();
			}
			if(checkPan() != NONE && !map.getLevel().isLocked()){
				map.getLevel().shift(checkPan());
			}
		}
		victory();
	}
	
	public void setLuciferAlive(boolean bool){
		this.luciferAlive = bool;
	}
	
	public void victory(){
		setVisible(false);
		JOptionPane.showMessageDialog(this, "You have triumphed over Lucifer!");
		System.exit(0);
	}
	
	public void setStillOnLevel(boolean bool){
		this.stillOnLevel = bool;
	}

	
	public void play(){
		rendering.start();
		keyListening.start();
		collisionTesting.start();
	}
	
	public int checkPan(){
		if(player.getX() > 1683 && map.getLevel().getLocationCol() != map.getLevel().getArraySize() - 1){
			return RIGHT;
		} else if (player.getY() > 850 && map.getLevel().getLocationRow() != map.getLevel().getArraySize() - 1){
			return DOWN;
		} else if(player.getX() < -50 && map.getLevel().getLocationCol() != 0){
			return LEFT;
		} else if(player.getY() < -50 && map.getLevel().getLocationRow() != 0){
			return UP;
		} else {
			return NONE;
		}
	}
	
	
	public void terminateGame(){
		setVisible(false);
		JOptionPane.showMessageDialog(this, "You died. Click OK to quit.");
		System.exit(0);
	}

	public static void main(String[] args) {
		Darklight d = new Darklight(); //calls constructor, sets up the UI
		d.play();
	}
	
	class MovementListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W){
				player.setDy(-1 * player.getStats().getSpeed());
			}
			
			if(key == KeyEvent.VK_A){
				player.setDx(-1 * player.getStats().getSpeed());
			}
			
			if(key == KeyEvent.VK_S){
				player.setDy(player.getStats().getSpeed());
			}
			
			if(key == KeyEvent.VK_D){
				player.setDx(player.getStats().getSpeed());
			}
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W){
				player.setDy(0);
			}
			
			if(key == KeyEvent.VK_A){
				player.setDx(0);
			}
			
			if(key == KeyEvent.VK_S){
				player.setDy(0);
			}
			
			if(key == KeyEvent.VK_D){
				player.setDx(0);
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			//intentionally left blank
		}

	}

	class AttackListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(player.canFire()){
				if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_DOWN
						|| key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP){
					
					Projectile temp = new Projectile(player);
					if(key == KeyEvent.VK_LEFT){
						temp.setDx(-1 * player.getWeapon().getWeaponStats().getBulletSpeed());
					}
					if(key == KeyEvent.VK_DOWN){
						temp.setDy(player.getWeapon().getWeaponStats().getBulletSpeed());
					}
					if(key == KeyEvent.VK_RIGHT){
						temp.setDx(player.getWeapon().getWeaponStats().getBulletSpeed());
					}
					if(key == KeyEvent.VK_UP){
						temp.setDy(-1 * player.getWeapon().getWeaponStats().getBulletSpeed());
					}
					temp.setX(temp.getX() + 80);
					temp.setY(temp.getY() + 100);
					map.getProjectiles().add(temp);
				}
				player.setCanFire(false);
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// intentionally left blank
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// intentionally left blank
			
		}
		
	}

	
}
