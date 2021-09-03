
public class BlueAlien extends Alien {

	private boolean moveRight = true;
	private boolean moveLeft = false;
	private boolean moveUp = false;
	private boolean moveDown = true;
	private final int SPRITE_WIDTH = 20;
	private final int SPRITE_HEIGHT = 17;
	
	/* sets the position of the alien and 
	   calls the method that loads the image */
	public BlueAlien(int x, int y) {
		super(x, y);
		
		initBlueAlien();
	}
	
	// loads the image of the alien
	private void initBlueAlien() {
		
		loadImage("Sprites/blue_alien.gif");
		getImageDimensions();
	}
	
	// controls the alien's movement
	@Override
	public void move() {
		if (moveRight && moveDown) {
			x += speed;
			y += speed;
			
			if (x + SPRITE_WIDTH == FINAL_X) {
				moveRight = false;
				moveLeft = true;
			}
			
			if (y + SPRITE_HEIGHT == FINAL_Y) {
				moveUp = true;
				moveDown = false;
			}
		}
		
		else if (moveLeft && moveDown) {
			x -= speed;
			y += speed;
			
			if (x == 0) {
				moveRight = true;
				moveLeft = false;
			}
			
			if (y + SPRITE_HEIGHT == FINAL_Y) {
				moveUp = true;
				moveDown = false;
			}
		}
		
		else if (moveRight && moveUp) {
			x += speed;
			y -= speed;
			
			if (x + SPRITE_WIDTH == FINAL_X) {
				moveRight = false;
				moveLeft = true;
			}
			
			if (y == 0) {
				moveUp = false;
				moveDown = true;
			}
		}
		
		else if (moveLeft && moveUp) {
			x -= speed;
			y -= speed;
			
			if (x == 0) {
				moveRight = true;
				moveLeft = false;
			}
			
			if (y == 0) {
				moveUp = false;
				moveDown = true;
			}
		}
	}
}
