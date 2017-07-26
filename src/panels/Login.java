/** 
 * login 
 * Author: sawet manachaichana
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import databaseConnection.DatabaseAccess;
import model.User;
import project.ViewFrame;

@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class Login extends JPanel {
	
	private int myWidth;
	private int myHeight;
	private JFrame frame;
	private Font font2;
	private int flightID;
	private int ppl;
	private double pricePaid;
	private JTextField emailText;
	private JPasswordField passwordText;
	
	/**
	 * 
	 * 
	 * @param width
	 * @param height
	 */
	public Login(int width, int height, JFrame theFrame, int flightIds, int people, double price){
		setBackground(Color.LIGHT_GRAY);
		myWidth = width;
		myHeight = height;
		frame = theFrame;
		flightID = flightIds;
		ppl = people;
		pricePaid = price;
		font2 = new Font("SansSerif", Font.BOLD, 20);
		
		add(needLogin(), BorderLayout.CENTER);

	}
	
	public JPanel needLogin (){
		JPanel insideLoginPanel = new JPanel();
		insideLoginPanel.setBackground(Color.LIGHT_GRAY);
		BoxLayout boxLayout = new BoxLayout(insideLoginPanel, BoxLayout.Y_AXIS);
		insideLoginPanel.setLayout(boxLayout);
		insideLoginPanel.setPreferredSize(new Dimension(400, 500));
		
		JLabel loginWelcome = new JLabel("Login If You Already Have An Account ");
		loginWelcome.setFont(font2);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(font2);
		
		emailText = new JTextField(6);
		emailText.setFont(font2);
		emailText.setMaximumSize(new Dimension(600, 40));
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(font2);
		
		
		passwordText = new JPasswordField(6);
		passwordText.setFont(font2);
		passwordText.setMaximumSize(new Dimension(600, 40));

		JButton loginButton = new JButton("Login");
		loginButton.setFont(font2);
		loginButton.setSize(new Dimension(600, 40));
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// check the user name and password
				User result = DatabaseAccess.login(emailText.getText(), new String(passwordText.getPassword()));
				if (result != null)
				{
					if(result.isEmployee())
					{
						JOptionPane.showMessageDialog (null, "Please let customer create an account",
								 "Information", JOptionPane.INFORMATION_MESSAGE);

					}
					else
					{
						removeAll();
						add(fillInfor(1, result.getPassengerID(), flightID, ppl, pricePaid,
								emailText.getText(), new String(passwordText.getPassword())),BorderLayout.CENTER);
						updateUI();
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog (null, "Incorrect Username or Password",
							 "Warnning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton registerButton = new JButton("Do Not Have An Account!!");
		registerButton.setFont(font2);
		registerButton.setSize(new Dimension(600, 40));
		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				removeAll();
				add(fillInfor(0, 0, flightID, ppl, pricePaid, "", ""),BorderLayout.CENTER);
				updateUI();
			}
		});
		
		add(Box.createHorizontalStrut(50));
		insideLoginPanel.add(loginWelcome);
		insideLoginPanel.add(Box.createVerticalStrut(50));
		insideLoginPanel.add(emailLabel);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(emailText);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(passwordLabel);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(passwordText);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(loginButton);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(registerButton);
		
		return insideLoginPanel;
	}

	/**
	 * switch to FillingCustoInfor panel 
	 * @param fillType
	 * @param passengerID
	 * @param flightIds
	 * @param people
	 * @param price
	 * @param email
	 * @param password
	 * @return
	 */
	public JPanel fillInfor(int fillType, int passengerID, int flightIds, int people, 
			double price, String email, String password){
		FillingCustoInfor fillingInfor = new FillingCustoInfor(myWidth, myHeight, frame, 
				fillType, passengerID, flightIds, people, price, email, password);	
		return fillingInfor;
	}


}
