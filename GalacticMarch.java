/* Galactic March! A space shooter game 
   where the only goal is to take out all 
   the aliens in one massive level! */

import java.awt.*;
import javax.swing.*;

public class GalacticMarch extends JFrame {

	public static void main(String[] args) {
		
		/* invokeLater used to post an event 
		after all other GUI events have been finished */
		EventQueue.invokeLater(() -> {
			GalacticMarch galacticMarch = new GalacticMarch();
			galacticMarch.setVisible(true);
		});
		
	}
	
	// sets up the GUI
	public GalacticMarch() {
		initUI();
	}
	
	// initiates the GUI
	private void initUI() {
		add(new Panel());
		
		setTitle("Galactic March");
		pack();
		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
