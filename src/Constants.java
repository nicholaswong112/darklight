
public interface Constants {
	
	int PLAYER = 666;
	int PROJECTILE = 999;
	
	int CENTER_ROW_PIXEL = 400, CENTER_COL_PIXEL = 750;

	//BLOCKS (1-50)
	int DIRT = 1;
	int GRASS = 2;
	int SAND = 3;
	int AQUATIC = 4;
	int STONE = 5;
	int DARKSTONE = 6;
	int FIRE = 7;
	int EXIT = 8;
	int CONCRETE = 9;
	int LAVA = 10;
	
	//LEVELS (51 - 100)
	int GRAVEYARD = 51;
	int SEWERS = 52;
	int DUNGEON = 53;
	int CATACOMBS = 54;
	int HELL = 55;


	//HEARTS(111-120)
	int HALF_HEART = 111;
	int FULL_HEART = 112;
	int DOUBLE_HEART = 113;

	//MONSTERS(-500 - -1)
	int REGULAR_ZOMBIE = -2;
	int TURRET_ZOMBIE = -3;
	int REAPER = -4;
	int RAT = -5;
	int CROC = -6;
	int CONVICT = -7;
	int GUARD = -8;
	int WARDEN = -9;
	int BAT = -10;
	int BEELZEBUB = -11;
	int CTHULU = -12;
	int LUCIFER = -13;

	//WEAPONS(121-200)
	int FINGER_GUN = 121;
	int WATER_GUN = 122; //IN GRAVEYARD
	int PAINTBALL_GUN = 123; //IN SEWERS
	int PISTOL = 124; //IN SEWERS
	int SNIPER_RIFLE = 125; //IN DUNGEON
	int ASSAULT_RIFLE =126; //IN DUNGEON
	int LASER_GUN = 127; //IN CATACOMBS
	
	//PAN(201-205)
	int RIGHT = 201;
	int DOWN = 202; 
	int LEFT = 203;
	int UP = 204;
	int NONE  = 205;
	

}
