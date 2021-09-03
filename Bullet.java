
public class Bullet extends Sprite {
	
	private final int BOARD_HEIGHT = 600;
    private final int MISSILE_SPEED = 3;
	
    /* sets the position of the bullet and calls
     the method that loads the image */
	public Bullet(int x, int y) {
		super(x, y);
		
		initBullet();
	}
	
	// loads the image of the bullet
	private void initBullet() {
		
		loadImage("Sprites/bullet.gif");  
        getImageDimensions(); 
	}
	
	// updates the bullet's position and determines visibility
	public void move() {
		y -= MISSILE_SPEED;
		
        if (y > BOARD_HEIGHT) {
            visible = false;
        }
    }
	
}
