import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {
	
	private Timer timer;
	private Spaceship spaceship;
	private List<Alien> aliens;
    private boolean ingame;
    private int score;
	private final int DELAY = 10; 
	private final int ICRAFT_X = 280;
	private final int ICRAFT_Y = 550;
	private final int B_WIDTH = 600;
	private final int B_HEIGHT = 600;
	
	private final int[][] pos = {
			// white aliens
			{50, 500}, {500, 450}, {300, 400}, {200, 150}, {400, 475},
			{100, 300}, {175, 350}, {575, 200}, {450, 250}, {325, 100},
			
			// red aliens
	        {490, 25}, {575, 500}, {25, 250}, {250, 100}, {350, 200}, 
	        {400, 350}, {100, 400}, {150, 575}, {300, 10}, {180, 150}, 
	        
	        // blue aliens
	        {25, 25}, {100, 450}, {150, 100}, {200, 400}, {250, 300}, 
			{300, 25}, {400, 200}, {450, 575}, {500, 375}, {550, 150} 
	    };
	
	// sets up the panel
	public Panel() {
		initPanel();
	}
	
	// initiates the panel
	private void initPanel() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		ingame = true;
		
		setPreferredSize((new Dimension(B_WIDTH, B_HEIGHT)));
		
		spaceship = new Spaceship(ICRAFT_X, ICRAFT_Y);
		
		initAliens();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	/* creates an array of all the aliens in the game
	   with their position given from the "pos" array */
	public void initAliens() {
		aliens = new ArrayList<>();
		
        for (int i = 0; i < pos.length; i++) {
        	int[] p = pos[i];
        	
        	if (i < 10) {
        		aliens.add(new WhiteAlien(p[0], p[1]));
        	}
        	else if (i < 20) {
        		aliens.add(new RedAlien(p[0], p[1]));
        	}
        	else {
        		aliens.add(new BlueAlien(p[0], p[1]));
        	}
        }
    }
	
	
	/* overrides the original paintComponent method in Jpanel
	   that draws the objects onto the panel */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(ingame) {
			doDrawing(g);
		}
		else if(aliens.isEmpty()){
            drawCongrats(g);
        }
		else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	// draws the spaceship, bullets, and aliens
	private void doDrawing(Graphics g) {
		if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }

        List<Bullet> bullets = spaceship.getBullets();
        
        for (Bullet bullet : bullets) {
        	if (bullet.isVisible()) {
            	g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
        
        for (Alien alien : aliens) {
        	if (alien.isVisible()) {
        		g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 5, 15);
        g.setColor(Color.RED);
        g.drawString("Total Aliens left: " + aliens.size(), 5, 30);
	}
	
	// draws the losing screen
	private void drawGameOver(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        
        g.setColor(Color.RED);
        g.setFont(small);
        g.drawString("Game Over", (B_WIDTH - fm.stringWidth("Game Over")) / 2, B_HEIGHT / 2);
        g.setColor(Color.ORANGE);
        g.drawString("Score: " + score, (B_WIDTH - fm.stringWidth("Score: " + score)) / 2, B_HEIGHT / 2 + 15);
    }
	
	// draws the winning screen
	private void drawCongrats(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        
        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString("You Won!", (B_WIDTH - fm.stringWidth("You Won!")) / 2, B_HEIGHT / 2);
        g.setColor(Color.ORANGE);
        g.drawString("Score: " + score, (B_WIDTH - fm.stringWidth("Score: " + score)) / 2, B_HEIGHT / 2 + 15);
    }
	
	/* method that updates the status of the bullets
	   and spaceship visually */
	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		
		updateBullets();
        updateSpaceship();
        updateAliens();
        checkCollisions();

        repaint();
	}
	
	/* determines if the game is still going on and 
	   stops drawing if the game is done */
	private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }
	
	// updates the count of bullets and their visibility
	private void updateBullets() {
		List<Bullet> bullets = spaceship.getBullets();

        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);

            if (bullet.isVisible()) {
                bullet.move();
            } 
            else {
                bullets.remove(i);
            }
        }
	}
	
	// updates/moves the spaceship's position
	private void updateSpaceship() {
		spaceship.move();
	}
	
	/* class with methods that overrides the original methods 
	in the KeyAdapter class */
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			spaceship.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			spaceship.keyReleased(e);
		}
	}
	
	// updates the aliens list accordingly
	private void updateAliens() {
		if (aliens.isEmpty()) {
			ingame = false;
			return;
		}
		 
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			 
			if (a.isVisible()) {
				a.move();
	        } 
			else {
				aliens.remove(i);
			}
		}
	}
	
	// checks if two objects have collided
	public void checkCollisions() {
		Rectangle r3 = spaceship.getBounds();
		 
		for (Alien alien : aliens) {
			Rectangle r2 = alien.getBounds();
			 
			if (r3.intersects(r2)) {
				spaceship.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
		}
		 
		List<Bullet> bullets = spaceship.getBullets();
		 
		for (Bullet bullet : bullets) {
			Rectangle r1 = bullet.getBounds();
			 
			for (Alien alien : aliens) {
				Rectangle r2 = alien.getBounds();
				 
				if (r1.intersects(r2)) {
					bullet.setVisible(false);
					alien.setVisible(false);
					score += 100;
				}
			}
		}
	}
	
}

