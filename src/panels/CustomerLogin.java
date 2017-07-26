/** 
 * When customer login
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
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import databaseConnection.DatabaseAccess;
import model.Aircraft;
import model.Airport;
import model.Flight;
import model.Reservation;
import project.Main;


/**
 * 
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class CustomerLogin extends JPanel {
	private int myWidth;
	private int myHeight;
	private JButton myLogout;
//	private JButton editInfor;
	private Font font;
	private JScrollPane ticketScrollPane;	
//	private JFrame myFrame;
	private int passID;
	private ImageIcon icon = new ImageIcon(Main.class.getResource("/image/icon2.png"));
	private String cusEmail;
	private String cusPassword;
	private JPanel ticketPanel;
	
	/**
	 * 
	 * 
	 * @param width
	 * @param height
	 */
	public CustomerLogin(int width, int height, JButton logout, JFrame theFrame, int passengerID,
			String email, String password){
		setBackground(Color.GREEN.brighter());
		font = new Font("SansSerif", Font.BOLD, 20);
		myWidth = width;
		myHeight = height;
		myLogout = logout;
//		myFrame = theFrame;
		passID = passengerID;
		cusEmail = email;
		cusPassword = password;
		myLogout.setFont(font);
		setPreferredSize(new Dimension(myWidth, myHeight));	
		
		ticketScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ticketScrollPane.setPreferredSize(new Dimension(830, 400));	
		
		ticketPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(ticketPanel, BoxLayout.Y_AXIS);
		ticketPanel.setLayout(boxLayout);
		ticketPanel.setBackground(Color.BLACK); 

		listOfTicket();
		
		ticketScrollPane.revalidate();
		ticketScrollPane.setViewportView(ticketPanel);
		
//		editInfor = new JButton("Edit Personal Information");
//		editInfor.setFont(font);
//		editInfor.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("passId = " + passID);
//				fillingInfor(2, passID);
////				removeAll();
////				add(fillInfor(2, passID),BorderLayout.CENTER); 
////				
////				updateUI();
//			}
//		});

		add(ticketScrollPane);
		
		
		
		add(logout, BorderLayout.SOUTH);
//		add(Box.createHorizontalStrut(200));
//		add(editInfor, BorderLayout.SOUTH);
	}
	
	public JPanel ticketList(int flightID, int departFrom, int arrivalAt, 
			Time departTime, Time arrivalTime, double basePrice, 
			Date departDate, Date arrivaltDate, int aircraft, int seat){

		JPanel tickList = new JPanel();
		GridLayout layout = new GridLayout(0, 4);
		tickList.setLayout(layout);
		tickList.setBackground(Color.LIGHT_GRAY);
		JButton cancelTicket = new JButton("Cancel!!!"  + flightID);
		cancelTicket.setFont(font);
		cancelTicket.setBackground(Color.GREEN);

		Airport depart = DatabaseAccess.getAirport(departFrom);
		Airport arrive = DatabaseAccess.getAirport(arrivalAt);
		Aircraft craft = DatabaseAccess.getAircraft(aircraft);
		
		
 
		//-------- Ticket information-------------------------	
		tickList.add(new JLabel(depart.getAirportName() + " --> " + arrive.getAirportName()));
		tickList.add(new JLabel(departTime + " --> " + arrivalTime));
		tickList.add(new JLabel(""));
		tickList.add(new JLabel(Double.toString(basePrice)));
		tickList.add(new JLabel(departDate.toString() + " --> " + arrivaltDate.toString()));
		tickList.add(new JLabel(craft.toString()));
		tickList.add(new JLabel("Seat: " + seat));
		tickList.add(cancelTicket); 
		cancelTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog (null, "Ticket Cancel" ,
					 "Information", JOptionPane.INFORMATION_MESSAGE);

			DatabaseAccess.dropFlight(passID, flightID);
			ticketPanel.removeAll();
			listOfTicket(); 
			ticketPanel.updateUI();
			}
		});
		
		return tickList;
	}
	

//	public JFrame fillingInfor(int fill, int passengerID){
//		JFrame inforFrame = new JFrame();
//		inforFrame.setSize(myWidth, myHeight);
//		inforFrame.setIconImage(icon.getImage());
//		inforFrame.setResizable(false);
//		inforFrame.setLocationRelativeTo(null);
//		inforFrame.setVisible(true);
//
//		FillingCustoInfor fillingInfor = new FillingCustoInfor(myWidth, myHeight, inforFrame, 
//				fill, passengerID, 0,0,0.0, cusEmail, cusPassword); // set defult value
//		
//		inforFrame.getContentPane().removeAll();
//		inforFrame.getContentPane().add(fillingInfor, BorderLayout.CENTER);
//
//		return inforFrame;
//	}
	
	/**
	 * list of each ticket
	 */
	public void listOfTicket(){
		List<Reservation> departFlight = DatabaseAccess.getReservations(passID);		
		
		// departure flight which is add element to display each ticket
		for(int i = 0; i < departFlight.size(); i++){
			
			Flight f = departFlight.get(i).getFlight();
			int seat = departFlight.get(i).getSeatAssignment();
			int flightID = f.getFlightsID();
			int departFrom = f.getDepartureFrom();
			int arrivalAt = f.getArrivalAt();
			Time departTime = f.getDepartureTime();
			Time arrivalTime = f.getArrivalTime();		
			double basePrice = departFlight.get(i).getPricePaid(); 
			Date departDate = f.getDepartureDate();
			Date arrivaltDate = f.getArrivalDate();
			int aircraft = f.getAircraftID();
			
			ticketPanel.add(ticketList(flightID, departFrom, arrivalAt, departTime, arrivalTime, 
					basePrice, departDate, arrivaltDate, aircraft, seat));
			ticketPanel.setPreferredSize(new Dimension(800, 70*(i+1)+ 100));
			ticketPanel.add(Box.createHorizontalStrut(5));
		}

	}
}