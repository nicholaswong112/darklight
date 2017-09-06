
public class Lucifer extends Enemy{
	
	public Lucifer(int identity, Map map) {
		super(identity, map);
		super.setX(475);
		super.setY(275);
	}
	
	@Override
	public void move(){
		if(Math.random() < .995){ //MAINLY ERRATIC MOVEMENT
			setX(getX() + (int)(Math.random() * 75));
			setX(getX() - (int)(Math.random() * 75));
			setY(getY() + (int)(Math.random() * 75));
			setY(getY() - (int)(Math.random() * 75));
		} else { //SMALL CHANCE TO TELEPORT
			Level temp = getMap().getLevel();
			temp.getEnemyArray()[(int)(Math.random() * temp.getEnemyArray().length)][(int)(Math.random() * temp.getEnemyArray()[0].length)] = LUCIFER;
			temp.getEnemyArray()[temp.getLocationRow()][temp.getLocationCol()] = 0;
			temp.setLocked(temp.getLocationRow(), temp.getLocationCol(), false);
			getMap().setDisplayedObject(0);
			getMap().setMessage("CYA SUCKER. TRY AND FIND ME");
		}
	}
	

}
