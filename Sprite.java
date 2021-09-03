import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	
	protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
	
    // sets the position and visibility of the sprite
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }
    
    // loads the image given by the directory path
	protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
	// gets the width and height of the image
    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    // returns the image object
    public Image getImage() {
        return image;
    }
    
    // returns the x position of the sprite
    public int getX() {
        return x;
    }

    // returns the y position of the sprite
    public int getY() {
        return y;
    }

    // returns the visibility of the sprite
    public boolean isVisible() {
        return visible;
    }

    // sets the visibility of the sprite
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    // used to create the hitbox around the objects
    public Rectangle getBounds() {
    	return new Rectangle(x, y, width, height);
    }
    
}
