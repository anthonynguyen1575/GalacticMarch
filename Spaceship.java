import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class Spaceship extends Sprite {
	
	private int dx;
	private int dy;
	private List<Bullet> bullets;
    
	/* sets the position of the spaceship and calls
	 the method that loads the image and creates an
	 array for all the bullets the spaceship shoots */
    public Spaceship(int x, int y) {
    	super(x, y);
    	
    	initSpaceship();
    }
	
    /* loads the spaceship's image and 
    creates an array for all the bullets 
    the spaceship shoots */
    public void initSpaceship() {
    	bullets = new ArrayList<>();
    	
    	loadImage("Sprites/ship.gif");
    	getImageDimensions();
    }
    
    // updates the position of the spaceship
    public void move() {
    	x += dx;
    	y += dy;
    }
    
    // returns the list of bullets shot
    public List<Bullet> getBullets() {
    	return bullets;
    }
    
    /* takes the keyboard inputs for the 
    movement and firing of the spaceship */
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_SPACE) {
    		fire();
    	}
    	
    	if(key == KeyEvent.VK_LEFT) {
    		dx = -2;
    	}
    	
    	if(key == KeyEvent.VK_RIGHT) {
    		dx = 2;
    	}
    	
    	if(key == KeyEvent.VK_DOWN) {
    		dy = 2;
    	}
    	
    	if(key == KeyEvent.VK_UP) {
    		dy = -2;
    	}
    }
    
    // determines when the keys are released
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_LEFT) {
    		dx = 0;
    	}
    	
    	if(key == KeyEvent.VK_RIGHT) {
    		dx = 0;
    	}
    	
    	if(key == KeyEvent.VK_DOWN) {
    		dy = 0;
    	}
    	
    	if(key == KeyEvent.VK_UP) {
    		dy = 0;
    	}
    }
    
    /* adds a bullet object, with the specific location, 
    to the list of bullets */
    public void fire() {
        bullets.add(new Bullet(x + width / 2 - 2, y - height / 2));
    }
    
}
