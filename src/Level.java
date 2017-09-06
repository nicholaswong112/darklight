import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Level implements Constants{
	
	private final int BLOCK_SIZE = 200/*in pixels*/, ARRAY_SIZE = 5 /*the square root of the number of 5 by 9 screens*/; 
	private final int ROWS_PER_SCREEN = 5, COLS_PER_SCREEN = 9;
	
	private Block[][] levelArray = new Block[ARRAY_SIZE*ROWS_PER_SCREEN][ARRAY_SIZE*COLS_PER_SCREEN];
	
	private int[][] enemyArray = new int[ARRAY_SIZE][ARRAY_SIZE];
	
	private boolean [][] locked = new boolean[ARRAY_SIZE][ARRAY_SIZE], visited = new boolean[ARRAY_SIZE][ARRAY_SIZE];
	
	private int minRow, maxRow, minCol, maxCol, locationRow, locationCol, id;
	
	private Map map;
	
	public Level(int identity, Map map){
		this.id = identity;
		this.map = map;
		minRow = 0;
		maxRow = 4;
		minCol = 0;
		maxCol = 8;
		locationRow = 0;
		locationCol = 0;
		generateLevel(identity);
		generateEnemiesWeapons(identity);
		initBoolGrids();
	}
	
	public void generateLevel(int identity){
		if(identity == GRAVEYARD){
			for(int r = 0; r< levelArray.length; r++){
				for(int c = 0; c< levelArray[r].length; c++){
					int temp = (int) (Math.random() * 3);
					temp++;
					levelArray[r][c] = new Block(temp); //DIRT,GRASS,SAND
				}
			}
			levelArray[levelArray.length - 1][levelArray[0].length - 1] = new Block(EXIT);
		}
		if(identity == SEWERS){
			for(int r = 0; r< levelArray.length; r++){
				for(int c = 0; c< levelArray[r].length; c++){
					int temp = (int) (Math.random() * 2);
					temp += 4;
					levelArray[r][c] = new Block(temp);//AQUATIC,STONE
				}
			}
			levelArray[levelArray.length - 1][levelArray[0].length - 1] = new Block(EXIT);
		}
		if(identity == DUNGEON){
			for(int r = 0; r< levelArray.length; r++){
				for(int c = 0; c< levelArray[r].length; c++){
					levelArray[r][c] = Math.random() < .5 ? new Block(CONCRETE) : new Block(STONE);//STONE, CONCRETE
				}
			}
			levelArray[levelArray.length - 1][levelArray[0].length - 1] = new Block(EXIT);
		}
		if(identity == CATACOMBS){
			for(int r = 0; r< levelArray.length; r++){
				for(int c = 0; c< levelArray[r].length; c++){
					levelArray[r][c] = Math.random() < .5 ? new Block(LAVA) : new Block(DARKSTONE);//DARKSTONE, LAVA
				}
			}
			levelArray[levelArray.length - 1][levelArray[0].length - 1] = new Block(EXIT);
		}
		if(identity == HELL){
			for(int r = 0; r< levelArray.length; r++){
				for(int c = 0; c< levelArray[r].length; c++){
					levelArray[r][c] = new Block(FIRE);//FIRE
				}
			}
		}
	}
	
	public void generateEnemiesWeapons(int identity){
		if(identity == GRAVEYARD){
			for(int r = 0; r< enemyArray.length; r++){
				for(int c = 0; c< enemyArray[r].length; c++){
					int temp = (int) (Math.random() * 2);
					temp -= 3;
					enemyArray[r][c] = temp; //REGULAR ZOMBIE, TURRET ZOMBIE
				}
			}
			placeWeapon(WATER_GUN);
			enemyArray[enemyArray.length - 1][enemyArray.length - 1] = REAPER;
		}
		if(identity == SEWERS){
			for(int r = 0; r< enemyArray.length; r++){
				for(int c = 0; c< enemyArray[r].length; c++){
					int temp = (int) (Math.random() * 10);
					temp -= 1;
					if(temp > 0) temp = 0;
					temp -= 5;					
					enemyArray[r][c] = temp;// 9/10 RAT, 1/10 CROC
				}
			}
			placeWeapon(PAINTBALL_GUN);
			placeWeapon(PISTOL);
			enemyArray[enemyArray.length - 1][enemyArray.length - 1] = CROC;
		}
		if(identity == DUNGEON){
			for(int r = 0; r< enemyArray.length; r++){
				for(int c = 0; c< enemyArray[r].length; c++){
					int temp = (int)(Math.random() * 2);
					temp -= 8;
					enemyArray[r][c] = temp; //1/2 GUARD 1/2 CONVICT
				}
			}
			placeWeapon(SNIPER_RIFLE);
			placeWeapon(ASSAULT_RIFLE);
			enemyArray[enemyArray.length - 1][enemyArray.length - 1] = WARDEN;
		}
		if(identity == CATACOMBS){
			for(int r = 0; r< enemyArray.length; r++){
				for(int c = 0; c< enemyArray[r].length; c++){
					int temp = (int)(Math.random() * 2);
					temp -= 12;
					enemyArray[r][c] = temp;//1/2 BEELZEBUB 1/2 CTHULU
				}
			}
			placeWeapon(LASER_GUN);
			enemyArray[enemyArray.length - 1][enemyArray[0].length - 1] = (int)(Math.random() * 2) == 1 ? BEELZEBUB : CTHULU; //1/2 BEELZEBUB 1/2 CTHULU
		}
		if(identity == HELL){
			for(int r = 0; r< enemyArray.length; r++){
				for(int c = 0; c< enemyArray[r].length; c++){
					enemyArray[r][c] = 0;
				}
			}
			enemyArray[(int)(Math.random() * 5)][(int)(Math.random() * 5)] = LUCIFER;
			if(enemyArray[0][0] == LUCIFER){
				enemyArray[2][2] = LUCIFER;
				enemyArray[0][0] = 0;
			}
		}
	}
	
	public void placeWeapon(int identity){
		int row = (int)(Math.random() * ARRAY_SIZE);
		int col = (int)(Math.random() * ARRAY_SIZE);
		if(row == col && row == 0) col++;
		if(row == col && row == ARRAY_SIZE - 1) col--;
		if(enemyArray[row][col] > 120 && enemyArray[row][col] < 128) placeWeapon(identity);
		else enemyArray[row][col] = identity;
	}
	
	public void initBoolGrids(){
		for(int r = 0; r < visited.length; r++)
			for(int c = 0; c < visited[0].length; c++)
				visited[r][c] = locked[r][c] = false;
		visited[0][0] = true;
	}
	
	public void shift(int direction){
		if(direction == RIGHT && locationCol != ARRAY_SIZE - 1){
			minCol += COLS_PER_SCREEN;
			maxCol += COLS_PER_SCREEN;
			locationCol++;
			map.getPlayer().setX(2);
		} else if(direction == DOWN && locationRow != ARRAY_SIZE - 1){
			minRow += ROWS_PER_SCREEN;
			maxRow += ROWS_PER_SCREEN;
			locationRow++;
			map.getPlayer().setY(2);
		} else if(direction == LEFT && locationCol != 0){
			minCol -= COLS_PER_SCREEN;
			maxCol -= COLS_PER_SCREEN;
			locationCol--;
			map.getPlayer().setX(1683);
		} else if(direction == UP && locationRow != 0){
			minRow -= ROWS_PER_SCREEN;
			maxRow -= ROWS_PER_SCREEN;
			locationRow--;
			map.getPlayer().setY(850);
		}
		if(!hasVisited())
			map.setDisplayedObject(enemyArray[locationRow][locationCol]);
		setVisited(locationRow, locationCol, true);
		map.setLevelChanged(true);
		map.repaint();
	}
	
	public int getArraySize(){
		return ARRAY_SIZE;
	}
	
	public int getBlockSize(){
		return BLOCK_SIZE;
	}
	
	public int getMinRow() {
		return minRow;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public int getMinCol() {
		return minCol;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public Block[][] getLevelArray() {
		return levelArray;
	}
	
	public int[][] getEnemyArray(){
		return enemyArray;
	}
	
	public void setVisited(int r, int c, boolean status){
		visited[r][c] = status;
	}
	
	public void setLocked(int r, int c, boolean status){
		locked[r][c] = status;
	}
	
	public boolean isLocked(){
		return locked[locationRow][locationCol];
	}
	
	public boolean hasVisited(){
		return visited[locationRow][locationCol];
	}
	
	public int getLocationRow(){
		return locationRow;
	}
	
	public int getLocationCol(){
		return locationCol;
	}
	
	public int getId(){
		return id;
	}

}
