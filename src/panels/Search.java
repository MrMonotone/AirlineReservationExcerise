/** 
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import databaseConnection.DatabaseAccess;
import model.DatePicker;
import model.Airport;

/**
 * 
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class Search extends JPanel {
	private int myWidth;
	private int myHeight;
	
	private JLabel ticketType;
	private JLabel fromText;
	private JLabel toText;
	private JLabel departText;
	private JLabel ReturnText;
	private JLabel adultsText;
	private JLabel childrenText;

	private JButton findTickets;
	
	private JComboBox airportDepart;
	private JComboBox arriveToAirport;	
	private JComboBox numOfAdults;
	private JComboBox numOfChil;
	private Font font;
	private JTextField departDate;
	private JRadioButton roundTrip;
	private JRadioButton oneWay;
	private JTextField returnDate;
	//---------------------
	private int travelType = 1;
	int index = 1;
	String[] toAirportList;
	
	/**
	 * 
	 * 
	 * @param width
	 * @param height
	 */
	public Search(int width, int height){
		myWidth = width;
		myHeight = height;
		
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);	
		setLayout(boxLayout);
		font = new Font("Serif", Font.BOLD, 30);

		add(Box.createVerticalStrut(20));
		add(topPanel());
		add(Box.createVerticalStrut(30));
		add(middleOnePanel());
		add(Box.createVerticalStrut(20));
		add(middleTwoPanel());
		add(Box.createVerticalStrut(30));
		add(bottomPanel());
		add(Box.createVerticalStrut(40));
	}
	
	public JPanel topPanel(){
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(800, 100));
		roundTrip = new JRadioButton("Round trip");
		roundTrip.setMnemonic(KeyEvent.VK_R);
		roundTrip.setActionCommand("Round trip");
		roundTrip.setSelected(true);
		roundTrip.setFont(font);
		
		oneWay = new JRadioButton("One way");
		roundTrip.setMnemonic(KeyEvent.VK_O);
		roundTrip.setActionCommand("One way");
		oneWay.setFont(font);

		ButtonGroup group = new ButtonGroup();
		
		group.add(roundTrip);
		group.add(oneWay);
		

		ticketType = new JLabel("Ticket type   ");
		ticketType.setFont(font);
		top.add(ticketType);
		top.add(roundTrip);
		top.add(oneWay);
			
		return top;
	}
	public JPanel middleOnePanel(){
		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(800, 200));
		GridLayout layout = new GridLayout(0,2);
		middle.setLayout(layout);
		
		
		String[] airportList = DatabaseAccess.getAirports().toArray(new String[0]);
		
		airportDepart = new JComboBox(airportList);
		airportDepart.setFont(font);
		
		arriveToAirport = new JComboBox(airportList);
        arriveToAirport.setFont(font);
		
		fromText = new JLabel("From ");
		fromText.setFont(font);
		middle.add(fromText);
		
		toText = new JLabel("To ");
		toText.setFont(font);
		middle.add(toText);
		middle.add(airportDepart);
		middle.add(arriveToAirport);
		
		departText = new JLabel("Depart ");
		departText.setFont(font);
		middle.add(departText);
		ReturnText = new JLabel("Return ");
		ReturnText.setFont(font);
		middle.add(ReturnText);
		
		return middle;
	}
	public JPanel middleTwoPanel(){
		JPanel middleTwo = new JPanel();
		middleTwo.setPreferredSize(new Dimension(800, 100));
		BoxLayout boxLayoutTwo = new BoxLayout(middleTwo, BoxLayout.X_AXIS);
		middleTwo.setLayout(boxLayoutTwo);
		

		departDate = new JTextField();
		departDate.setFont(font);
		departDate.setToolTipText("dd/mm/yyyy");
		departDate.setEditable(false);
		returnDate = new JTextField();
		returnDate.setFont(font);
		returnDate.setToolTipText("dd/mm/yyyy");
		returnDate.setEditable(false);
		//-------------------------------------------------------------------------------
		JButton dateDepartButton = new JButton("...");
		dateDepartButton.setFont(font);
		dateDepartButton.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date 
				departDate.setText(new DatePicker(f).setPickedDate());
			}
		});
		
		JButton dateArrivalButton = new JButton("...");
		dateArrivalButton.setFont(font);
		dateArrivalButton.addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent arg0) 
			{
				//create frame new object  f
				final JFrame f = new JFrame();
				//set text which is collected by date picker i.e. set date 
				returnDate.setText(new DatePicker(f).setPickedDate());
			}
		});
		
		//-----------------------------------------------------------------------
		middleTwo.add(Box.createHorizontalStrut(10));
		middleTwo.add(departDate);
		middleTwo.add(Box.createHorizontalStrut(10));
		middleTwo.add(dateDepartButton);
		middleTwo.add(Box.createHorizontalStrut(10));
		middleTwo.add(returnDate);
		middleTwo.add(Box.createHorizontalStrut(10));
		middleTwo.add(dateArrivalButton);
		middleTwo.add(Box.createHorizontalStrut(10));
		 
		return middleTwo;
	}
	public JPanel bottomPanel(){
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(800, 100));
		BoxLayout boxLayoutThree = new BoxLayout(bottom, BoxLayout.X_AXIS);
		bottom.setLayout(boxLayoutThree);
		String[] numAdults = {"1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] numChil = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		numOfAdults = new JComboBox(numAdults);
		numOfAdults.setFont(font);
		numOfChil = new JComboBox(numChil);
		numOfChil.setFont(font);
		
		adultsText = new JLabel("Adults ");
		adultsText.setFont(font);
		bottom.add(Box.createHorizontalStrut(10));
		bottom.add(adultsText);
		bottom.add(Box.createHorizontalStrut(10));
		bottom.add(numOfAdults);
		bottom.add(Box.createHorizontalStrut(30));
		
		childrenText = new JLabel("Children(under 12) ");
		childrenText.setFont(font);
		bottom.add(childrenText);
		bottom.add(numOfChil);
		bottom.add(Box.createHorizontalStrut(30));
		
		findTickets = new JButton("Find Tickets");
		findTickets.setFont(font);
		bottom.add(findTickets);
		bottom.add(Box.createHorizontalStrut(10));
		findTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ppl = numOfAdults.getSelectedIndex() + 1 + numOfChil.getSelectedIndex();
				System.out.println("number of ppl = " + ppl);
				
				if(roundTrip.isSelected() && returnDate.getText().equals("")){
					JOptionPane.showMessageDialog (null, "Please fill correct information",
							 "Warning", JOptionPane.INFORMATION_MESSAGE);
					
				} else if(oneWay.isSelected() && departDate.getText().equals("")){
					JOptionPane.showMessageDialog (null, "Please fill correct information",
							 "Warning", JOptionPane.INFORMATION_MESSAGE);
					
				} else if(oneWay.isSelected() && !departDate.getText().equals("")){
					if(airportDepart.getSelectedIndex() != arriveToAirport.getSelectedIndex()){
						if(roundTrip.isSelected()){
							travelType = 2;
						} else {
							travelType = 1;
						}
						// everything pass and sent all the infor to display the ticket
						removeAll();
						try {
								add(disTickets(airportDepart.getSelectedIndex()+1, arriveToAirport.getSelectedIndex()+1, 
										departDate.getText(), travelType, "00/00/0000", ppl),BorderLayout.CENTER);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						updateUI();
					} else{
						JOptionPane.showMessageDialog (null, "Please select airport",
								 "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
					
				} else {
					
					if(airportDepart.getSelectedIndex() != arriveToAirport.getSelectedIndex()){
						if(roundTrip.isSelected()){
							travelType = 2;
						} else {
							travelType = 1;
						}
						// everything pass and sent all the infor to display the ticket
						removeAll();
						try {
								add(disTickets(airportDepart.getSelectedIndex()+1, arriveToAirport.getSelectedIndex()+1, 
										departDate.getText(), travelType, returnDate.getText(), ppl),BorderLayout.CENTER);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						updateUI();
					} else{
						JOptionPane.showMessageDialog (null, "Please select airport",
								 "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
				}
			}
		});
		return bottom;
	}
	
	/**
	 * change to DisplayTickets panel
	 * @param departFlightId
	 * @param arrivalFlightId
	 * @param date
	 * @param travel
	 * @param returnDate
	 * @param people
	 * @return
	 * @throws ParseException
	 */
	private JPanel disTickets(int departFlightId, int arrivalFlightId, String date, 
			int travel, String returnDate, int people) throws ParseException {
		DisplayTickets ticketsPanel = new DisplayTickets(myWidth, myHeight, departFlightId,
				arrivalFlightId, date, travel, returnDate, people);
		return ticketsPanel;
	}
}