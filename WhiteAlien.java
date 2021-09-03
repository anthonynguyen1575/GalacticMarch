
public class WhiteAlien extends Alien {
	
	private boolean moveRight = true;
	private boolean moveLeft = false;
	private final int SPRITE_WIDTH = 20;
	
	/* sets the position of the alien and 
	   calls the method that loads the image */
	public WhiteAlien(int x, int y) {
		super(x, y);
		
		initWhiteAlien();
	}
	
	// loads the image of the alien
	private void initWhiteAlien() {
		
		loadImage("Sprites/white_alien.gif");
		getImageDimensions();
	}
	
	// controls the alien's movement
	@Override
	public void move() {
		if (moveRight) {
			x += speed + 1;
			
			if (x + SPRITE_WIDTH > FINAL_X) {
				moveRight = false;
				moveLeft = true;
			}
		}
		
		else if (moveLeft) {
			x -= speed + 1;
			
			if (x < 0) {
				moveRight = true;
				moveLeft = false;
			}
		}
		
	}

}
