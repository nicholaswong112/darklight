import java.awt.*;


public interface DisplayedObject {
	public abstract int returnSelf();
	public abstract int getX();
	public abstract int getY();
	public abstract Rectangle getHitBox();
}