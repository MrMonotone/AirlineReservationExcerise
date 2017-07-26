/** 
 * view the ticket
 * Author: sawet manachaichana
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import databaseConnection.DatabaseAccess;
import model.User;
import project.ViewFrame;

/**
 * 
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class ViewTicket extends JPanel {

	private int myWidth;
	private int myHeight;
	private JButton myLogout;
	private ViewFrame frame;
	private JTextField emailText;
	private JPasswordField passwordText;
	
	/**
	 * 
	 * 
	 * @param width
	 * @param height
	 */
	public ViewTicket(int width, int height, JButton logout, ViewFrame theFrame){
		setBackground(Color.RED.brighter());
		myWidth = width;
		myHeight = height;
		myLogout = logout;
		frame = theFrame;
		Font font2 = new Font("SansSerif", Font.BOLD, 20);
		
		JPanel insideLoginPanel = new JPanel();
		insideLoginPanel.setBackground(Color.RED);
		BoxLayout boxLayout = new BoxLayout(insideLoginPanel, BoxLayout.Y_AXIS);
		insideLoginPanel.setLayout(boxLayout);
		insideLoginPanel.setPreferredSize(new Dimension(300, 320));
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel emailLabel = new JLabel("Email: ", JLabel.RIGHT);
		emailLabel.setFont(font2);
		JLabel passwordLabel = new JLabel("Password: ", JLabel.CENTER);
		passwordLabel.setFont(font2);
		emailText = new JTextField(6);
		emailText.setFont(font2);
		emailText.setPreferredSize(new Dimension(100, 40));
		passwordText = new JPasswordField(6);
		passwordText.setFont(font2);
		passwordText.setPreferredSize(new Dimension(100, 40));
		
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(font2);
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(emailText.getText().equals("") ){			
					JOptionPane.showMessageDialog (null, "Incorrect Username or Password",
							 "Warnning", JOptionPane.INFORMATION_MESSAGE);			
				} else if(passwordText.getPassword().length == 0){
					JOptionPane.showMessageDialog (null, "Incorrect Username or Password",
							 "Warnning", JOptionPane.INFORMATION_MESSAGE);	
				} else {
					User result = DatabaseAccess.login(emailText.getText(), new String(passwordText.getPassword()));
					if(result != null){
						if (result.isEmployee()) {
							// switch to the employee panel
							removeAll();
							add(employLogin(),BorderLayout.CENTER);	
							updateUI();
							frame.removeButtonMenu();
						} else{
							// switch to the customer panel
							removeAll();
							add(cusLogin(result.getPassengerID()),BorderLayout.CENTER);
							updateUI();
							frame.removeButtonMenu();
						}
					}else {
						JOptionPane.showMessageDialog (null, "Incorrect Username or Password",
								 "Warnning", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		insideLoginPanel.add(Box.createVerticalStrut(100));
		insideLoginPanel.add(emailLabel);
		insideLoginPanel.add(Box.createVerticalStrut(10));
		insideLoginPanel.add(emailText);
		insideLoginPanel.add(Box.createVerticalStrut(10));
		insideLoginPanel.add(passwordLabel);
		insideLoginPanel.add(Box.createVerticalStrut(10));
		insideLoginPanel.add(passwordText);
		insideLoginPanel.add(Box.createVerticalStrut(30));
		insideLoginPanel.add(loginButton);

		add(insideLoginPanel, BorderLayout.CENTER);

	}
	/**
	 * change to CustomerLogin
	 * @param passID
	 * @return
	 */
	private JPanel cusLogin(int passID) {
		CustomerLogin cusViewPanel = new CustomerLogin(myWidth, myHeight, myLogout, frame, passID,
				emailText.getText(), new String(passwordText.getPassword()));
		return cusViewPanel;
	}
	
	/**
	 * change to EmployeeLogin
	 * @return
	 */
	private JPanel employLogin() {
		EmployeeLogin employViewPanel = new EmployeeLogin(myWidth, myHeight, myLogout);
		return employViewPanel;
	}
}