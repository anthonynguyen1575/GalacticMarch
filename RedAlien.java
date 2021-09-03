
public class RedAlien extends Alien {

	/* sets the position of the alien and 
	   calls the method that loads the image */
	public RedAlien(int x, int y) {
		super(x, y);
		
		initRedAlien();
	}
	
	// loads the image of the alien
	private void initRedAlien() {
		
		loadImage("Sprites/red_alien.gif");
		getImageDimensions();
	}
	
	// controls the alien's movement
	@Override
	public void move() {
		if(y > FINAL_Y) {
			y = -500;
		}
		
		y += speed + 2;
	}
	
}
