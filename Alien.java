
public class Alien extends Sprite {
	
	public final int FINAL_X = 600;
	public final int FINAL_Y = 600;
	public int speed = 1;
	
	// sets the position of the alien
	public Alien(int x, int y) {
		super(x, y);
	}
	
	/* controls the movement of the alien
	   but gets override in the respective alien classes */
	public void move() {
	
	}
	
}
