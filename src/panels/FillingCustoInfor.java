/**
 * For employee to look at customer data
 * Author: Sawet Manachaichana
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import databaseConnection.DatabaseAccess;
import model.Passenger;

/**
 * Set up component.
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class FillingCustoInfor extends JPanel implements ListSelectionListener {
	
	private JScrollPane idScrollPane;	
	private JButton submitButton; 
	private Font font;
	private int myWidth;
	private int myHeight;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField country;
	private JTextField phoneNumber;
	private JTextField passportCountry;
	private JTextField address1;
	private JTextField address2;
	private JTextField city;
	private JTextField state;
	private JTextField cardName;
	private JTextField cardNumber;
	private JTextField cardExprie;
	private JTextField cardSecurityCode;
	private JFrame frame;
	private int fillType;
	private int passID;
	private JTextField emailRegisText; 
	private JPasswordField passwordRegisText;
	private JPasswordField confirmPassText;
	private JComboBox meal;
	private JComboBox genderCombo;
	private JComboBox seatAssign;
	//-------------------------------------
	private int flightID;
	private int ppl;
	private double pricePaid;
	private String cusEmail;
	private String cusPassword;

	public FillingCustoInfor(int width, int height, JFrame theFrame, int fill, int passengerID
			, int flightIds, int people, double price, String email, String password) {
		myWidth = width;
		myHeight = height;
		frame = theFrame;
		fillType = fill;
		passID = passengerID;
		flightID = flightIds;
		ppl = people;
		pricePaid = price;
		cusEmail = email;
		cusPassword = password;
		setPreferredSize(new Dimension(myWidth, myHeight));	
		setBackground(Color.LIGHT_GRAY);
		font = new Font("SansSerif", Font.BOLD, 20);

//----------------------------------------------------------------------------------------------------------			
		idScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		idScrollPane.setPreferredSize(new Dimension(myWidth, 400));	
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);		
		mainPanel.add(customerInformation()); 
		
		// login with need to create account
		if(fillType == 0){
			mainPanel.setPreferredSize(new Dimension(830, 590));
			mainPanel.add(register());
		}else { // already have an account
			mainPanel.setPreferredSize(new Dimension(830, 470));
		}	
		
		idScrollPane.revalidate();
		idScrollPane.setViewportView(mainPanel);

//----------------------------------------------------------------------------------------------------------

			
		submitButton = new JButton("Submit");
		submitButton.setFont(font);
		submitButton();
         
		add(idScrollPane);
		add(submitButton, BorderLayout.SOUTH);
	}

	
	/**
	 * change the customer information in the JTextArea 
	 * when employee click select on customerName
	 */
	public void valueChanged(ListSelectionEvent e) {
		//Unused inherited method.
	}
	
	/**
	 * filling customer information
	 * @return
	 */
	public JPanel customerInformation(){
		
		JPanel cusInfor = new JPanel();
		cusInfor.setBackground(Color.LIGHT_GRAY);
		GridLayout layout = new GridLayout(0, 4);
		cusInfor.setLayout(layout);
		cusInfor.setPreferredSize(new Dimension(800, 450));
		
		firstName = new JTextField();
		lastName = new JTextField();
		country = new JTextField();
		phoneNumber = new JTextField();
		passportCountry = new JTextField();
		address1 = new JTextField();
		address2 = new JTextField();
		city = new JTextField();
		state = new JTextField();
		cardName = new JTextField();
		cardNumber = new JTextField();
		cardExprie = new JTextField();
		cardSecurityCode = new JTextField();
		
		String[] genderList = { "Male", "Female"};
		genderCombo = new JComboBox(genderList);
		
		String[] mealOption = { "Regular", "Meat", "Vegetarian", "No Food"};
		meal = new JComboBox(mealOption);

		// get the seat and check what seat is taken
		List<Integer> seatList = DatabaseAccess.getSeatsTaken(flightID);
		
		int capacity = DatabaseAccess.getAircraftCapacity(flightID);
		System.out.println("capacity = " + capacity);
		
		List<Integer> empty = new ArrayList<>();
		
		for (int i = 1; i <= capacity; i++)
		{
			if (!seatList.contains(i))
			{
				empty.add(i);
			}
		}
		
		seatAssign = new JComboBox(empty.toArray(new Integer[0]));
		
		
		if (fillType == 1 || fillType == 2)
		{
			Passenger p = DatabaseAccess.getPassenger(passID);
			firstName.setText(p.getPassengerFirstName()); 
			lastName.setText(p.getPassengerLastName()); 
			country.setText(p.getPassengerCountry()); 
			phoneNumber.setText(p.getPassengerPhoneNumber()); 
			//gender.setText(p.getPassengerGender()); 
			if(p.getPassengerGender().equals("Male")){
				genderCombo.setSelectedIndex(0);
			} else {
				genderCombo.setSelectedIndex(1);
			}
			
			if(p.getPassengerMeal().equals("Regular")){
				meal.setSelectedIndex(0);
			} else if(p.getPassengerMeal().equals("Meat")){
				meal.setSelectedIndex(1);
			} else if(p.getPassengerMeal().equals("Vegetarian")){
				meal.setSelectedIndex(2);
			} else if(p.getPassengerMeal().equals("No Food")){
				meal.setSelectedIndex(3);
			}
			passportCountry.setText(p.getPassportCountry()); 
			address1.setText(p.getPassengerAddress1()); 
			address2.setText(p.getPassengerAddress2()); 
			city.setText(p.getPassengerCity()); 
			state.setText(p.getPassengerState()); 
		}
		
		
		cusInfor.add(new JLabel("  First Name "));
		cusInfor.add(firstName);
		cusInfor.add(new JLabel("  Last Name "));
		cusInfor.add(lastName);
		cusInfor.add(new JLabel("  Phone Number "));
		cusInfor.add(phoneNumber);
		cusInfor.add(new JLabel("  Gender "));
		cusInfor.add(genderCombo);
		cusInfor.add(new JLabel("  Passport Country "));
		cusInfor.add(passportCountry);
		cusInfor.add(new JLabel("  Address 1 "));
		cusInfor.add(address1);
		cusInfor.add(new JLabel("  Address 2 "));
		cusInfor.add(address2);
		cusInfor.add(new JLabel("  City "));
		cusInfor.add(city);
		cusInfor.add(new JLabel("  State "));
		cusInfor.add(state);
		cusInfor.add(new JLabel("  Country "));
		cusInfor.add(country);
		cusInfor.add(new JLabel("  Seat Available"));
		cusInfor.add(seatAssign);
		cusInfor.add(new JLabel("  Meal Option "));
		cusInfor.add(meal);
		cusInfor.add(new JLabel("   How Would you like to play?"));
		cusInfor.add(new JLabel("   "));
		cusInfor.add(new JLabel("   "));
		cusInfor.add(new JLabel("   "));
		cusInfor.add(new JLabel("  Card holder Name"));
		cusInfor.add(cardName);
		cusInfor.add(new JLabel("  Crad Number"));
		cusInfor.add(cardNumber);
		cusInfor.add(new JLabel("  Expiration Date"));
		cusInfor.add(cardExprie);
		cusInfor.add(new JLabel("  Card Security Code"));
		cusInfor.add(cardSecurityCode);
		
		
		return cusInfor;
	}
	
	/**
	 * when need to create an account for new customer
	 * @return
	 */
	public JPanel register(){
		JPanel register = new JPanel();
		register.setBackground(Color.LIGHT_GRAY);
		register.setPreferredSize(new Dimension(800, 120));
		GridLayout layout = new GridLayout(0, 2);
		register.setLayout(layout);
		
		JLabel emailResgisLabel = new JLabel("Email: ");
		emailRegisText = new JTextField(6);
		JLabel passwordRegisLabel = new JLabel("Password: ");
		passwordRegisText = new JPasswordField(6);
		JLabel confirePassLabel = new JLabel("Confirm Password: ");
		confirmPassText = new JPasswordField(6);

		register.add(emailResgisLabel);
		register.add(emailRegisText);
		register.add(passwordRegisLabel);
		register.add(passwordRegisText);
		register.add(confirePassLabel);
		register.add(confirmPassText);

		return register;
	}
	
	/**
	 * update data to database 
	 */
	public void updateData(){
		String cusFirstName = firstName.getText(); 
		String cusLastName = lastName.getText();
		String cusCountry = country.getText();
		String cusPhoneNumber = phoneNumber.getText();
		String cusGender = "";
		if(genderCombo.getSelectedIndex() == 0){
			cusGender = "Male";
		} else {
			cusGender = "Female";
		}
		String mealOption = "";
		if(meal.getSelectedIndex() == 0){
			mealOption = "Regular";
		} else if(meal.getSelectedIndex() == 1){
			mealOption = "Meat";
		} else if(meal.getSelectedIndex() == 2){
			mealOption = "Vegetarian";
		} else if(meal.getSelectedIndex() == 3){
			mealOption = "No Food";
		}
		String cusPassportCountry = passportCountry.getText();
		String cusAddress1 = address1.getText(); 
		String cusAddress2 = address2.getText();
		String cusCity = city.getText(); 
		String cusState = state.getText(); 
		String email = cusEmail;
		int seat = (int) seatAssign.getItemAt(seatAssign.getSelectedIndex());
		
		if(fillType == 0 ){ // when user need to buy a ticket and does not has an account
			// need to update all the data into database with ticket bought
			String newEmail = emailRegisText.getText();
			String newPass = new String(passwordRegisText.getPassword());
				Passenger passen = new Passenger(cusFirstName, cusLastName, 
					cusPhoneNumber, newEmail, cusAddress1, cusAddress2, 
					cusCity, cusState, cusCountry, cusPassportCountry, cusGender, mealOption);
				
				int id = DatabaseAccess.createAccount(newEmail, newPass, passen);
				
				for(int pplNum = 0; pplNum < ppl; pplNum++){
					DatabaseAccess.createReservation(id, flightID, pricePaid, seat); 
				}
			
		} else if (fillType == 1){// when user need to buy a ticket and has an account
			// check if user make any change and then update data into database with ticket bought
			Passenger passen = new Passenger(passID, cusFirstName, cusLastName, 
					cusPhoneNumber, email, cusAddress1, cusAddress2, 
					cusCity, cusState, cusCountry, cusPassportCountry, cusGender, mealOption);
			DatabaseAccess.updatePassenger(passen);
			for(int pplNum = 0; pplNum < ppl; pplNum++){
				DatabaseAccess.createReservation(passen.getPassengerID(), flightID, pricePaid, seat); 
			}
			
			
		} else { // fillType == 2 edit infor when view ticket
			// check if user make any change and then update data into database
			Passenger passen = new Passenger(passID, cusFirstName, cusLastName, 
					cusPhoneNumber, email, cusAddress1, cusAddress2, 
					cusCity, cusState, cusCountry, cusPassportCountry, cusGender, mealOption);
			DatabaseAccess.updatePassenger(passen);
			
			
		}
		
	}

	/**
	 * submit button 
	 */
	public void submitButton(){
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){			
				if(!firstName.getText().equals("") && !lastName.getText().equals("") 
						&& !phoneNumber.getText().equals("") && !passportCountry.getText().equals("") 
						&& !address1.getText().equals("") && !city.getText().equals("") 
						&& !state.getText().equals("") && !country.getText().equals("")){
					
					if(fillType ==  1){ // login when user buy a ticket
						if(!cardName.getText().equals("") && !cardNumber.getText().equals("")
							&& !cardExprie.getText().equals("") && !cardSecurityCode.getText().equals("")){
							frame.dispose();
						updateData();
						System.out.println("login when user buy a ticket ");
						JOptionPane.showMessageDialog (null, "You Bought The Ticket.",
								 "Thank you!!!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog (null, "**** Please Fill information ******",
									 "Warning!!!", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} else if(fillType == 2){// login when view Ticket
						frame.dispose();
						updateData();
						System.out.println("login when view Ticket");
						JOptionPane.showMessageDialog (null, "Your information had updated",
								 "Information", JOptionPane.INFORMATION_MESSAGE);
					}else if(fillType == 0){ // 
						if(new String(confirmPassText.getPassword()).equals(new String(passwordRegisText.getPassword()))
								&& !(new String(confirmPassText.getPassword()).equals("")) 
								&& !emailRegisText.getText().equals("")
								&& !cardName.getText().equals("") && !cardNumber.getText().equals("")
								&& !cardExprie.getText().equals("") && !cardSecurityCode.getText().equals("")){
							frame.dispose();
							updateData();
							System.out.println("when create a new user " + fillType);
							JOptionPane.showMessageDialog (null, "You Bought The Ticket.",
								 "Thank you!!!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog (null, "**** Email or Password Incorrect ******",
									 "Warning!!!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else {
					JOptionPane.showMessageDialog (null, "**** Please Fill information ******",
							 "Warning!!!", JOptionPane.INFORMATION_MESSAGE);
				}

				
				
			}
		});
	}

}