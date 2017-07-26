/**
 * The main GUI class that call each panel.
 * Author: Sawet Manachaichana
 */

package project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import panels.About;
import panels.ViewTicket;
import panels.Search;


@SuppressWarnings("serial")
public class ViewFrame extends JFrame {

	private JPanel buttonPanel;
	private JButton search;
	private JButton about;
	private JButton login;
	private JButton logout;
	private ImageIcon icon = new ImageIcon(Main.class.getResource("/image/icon2.png"));
	private Font font;
	
	// width and height of JFrame
	private int height = 500;
	private int width = 850;
	
	/**
	 * Constructor Class creates the overall Frame of the Program.
	 */
	public ViewFrame(){
		super("Welcome to Airline Reservation", null);
		setSize(width, height);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		buttomset();
		printAll(getGraphics());
		
	}

	/**
	 * button set up  at the top of the program
	 */
	private void buttomset() {
		font = new Font("Serif", Font.BOLD, 20);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		search = new JButton("Search");
		search.setFont(font);
		about = new JButton("About");
		about.setFont(font);
		login = new JButton("View Your Ticket");
		login.setFont(font);
		logout = new JButton("Log out");
		logout.setFont(font);

		buttonPanel.add(Box.createVerticalStrut(5));
		buttonPanel.add(search);
		buttonPanel.add(about);
		buttonPanel.add(Box.createVerticalStrut(5));
		buttonPanel.add(login);

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(about(), BorderLayout.CENTER);
				getContentPane().add(buttonPanel, BorderLayout.NORTH);
				printAll(getGraphics());
			}
		});

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(searchAirline(), BorderLayout.CENTER);
				getContentPane().add(buttonPanel, BorderLayout.NORTH);
				printAll(getGraphics());
			}
		});


		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(viewTicket(), BorderLayout.CENTER);
				getContentPane().add(buttonPanel, BorderLayout.NORTH);
				printAll(getGraphics());
			}
		});
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(viewTicket(), BorderLayout.CENTER);
				getContentPane().add(buttonPanel, BorderLayout.NORTH);
				printAll(getGraphics());
			}
		});

		add(buttonPanel, BorderLayout.NORTH);
		add(about(), BorderLayout.CENTER);
	}

	/**
	 * About panel is welcome user when they enter to the program
	 * @return about panel
	 */
	private JPanel about() {
		About aboutPanel = new About(width, height);
		return aboutPanel;
	}

	/**
	 *  search panel is giving ability for user to search for flight
	 * @return download panel
	 */
	private JPanel searchAirline() {
		Search searchPanel = new Search(width, height);
		return searchPanel;
	}

	/**
	 * viewTicket panel will ask user to login to display their ticket
	 * @return login panel
	 */
	private JPanel viewTicket() {
		ViewTicket loginPanel = new ViewTicket(width, height, logout, this);
		return loginPanel;
	}
	
	/**
	 * remove the buttonPanel when login
	 */
	public void removeButtonMenu() {
		remove(buttonPanel);
	}
	
}
