/** 
 * Main page that display information of this program
 * Author: sawet manachaichana
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import project.Main;


/**
 * About Page that opens when the application first opens up.
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class About extends JPanel {
	
	private JLabel imageLabel;

	/**
	 * Constructor that creates the About main panel seen upon logging into the program.
	 * 
	 * @param width
	 * @param height
	 */
	public About(int width, int heigh){	
		Panel inPanel = new Panel(new BorderLayout());
		setBackground(Color.BLUE.brighter());
		
		Label label = new Label("Welcome to");
		label.setFont(new Font("Serif", Font.BOLD, 50));
		label.setForeground(Color.ORANGE);
		Label label2 = new Label("Airline");
		label2.setFont(new Font("Serif", Font.BOLD, 50));
		label2.setForeground(Color.ORANGE);
		Label label3 = new Label("Reservation");
		label3.setFont(new Font("Serif", Font.BOLD, 50));
		label3.setForeground(Color.ORANGE);
 
		inPanel.add(label, BorderLayout.NORTH);
		inPanel.add(label2, BorderLayout.CENTER);
		inPanel.add(label3, BorderLayout.SOUTH);
		
		ImageIcon imageIcon = new ImageIcon(Main.class.getResource("/image/icon1.png"));
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(400, 400,
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg); // transform it back
		imageLabel = new JLabel(imageIcon);
		

		add(imageLabel, BorderLayout.WEST);
		add(inPanel, BorderLayout.EAST);
	}
	
}