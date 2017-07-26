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
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import databaseConnection.DatabaseAccess;
import model.Aircraft;
import model.Airport;
import model.Flight;
import model.Passenger;
import model.Reservation;

/**
 * Set up component.
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class EmployeeLogin extends JPanel implements ListSelectionListener {
	
	private JList<String> idList;
	private JPanel inforPane;
	private DefaultListModel<String> customerName;
	private JScrollPane idScrollPane;
	private JSplitPane splitPane;	
	private int myWidth;
	private int myHeight;
	public JButton logout;
	private Font font;
	private JScrollPane ticketScrollPane;
	private JPanel ticketPanel;
	private Dimension rightPanel = new Dimension(610, 400);
	
	int num;

	public EmployeeLogin(int width, int height, JButton theLogout) {
		myWidth = width;
		myHeight = height;
		logout = theLogout;
		setBackground(Color.PINK.darker().darker());
		font = new Font("SansSerif", Font.BOLD, 20);
		setPreferredSize(new Dimension(myWidth, myHeight));	
		
		List<Passenger> passenger = DatabaseAccess.getPassengers(); 
		// List of customer name 
		customerName = new DefaultListModel<String>();
		for(int i =0; i< passenger.size(); i++){  
			customerName.addElement(passenger.get(i).getPassengerID() + "-" + passenger.get(i).getPassengerFirstName());
		}
		
		//add id data to the list--------------------------------------------------
		idList = new JList<String>(customerName);
		idList.setFont(font);		
		
		// add JList to the ScrollPane
		idScrollPane = new JScrollPane(idList);
	
		
		// panel to show customer information -------------------------------------------------------
		inforPane = new JPanel();
		inforPane.setPreferredSize(rightPanel);
		ticketScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ticketScrollPane.setPreferredSize(rightPanel);	
		
		ticketPanel = new JPanel();
		BoxLayout boxLayoutTwo = new BoxLayout(ticketPanel, BoxLayout.Y_AXIS);
		ticketPanel.setLayout(boxLayoutTwo);
		ticketPanel.setBackground(Color.BLACK); 
		
		ticketScrollPane.revalidate();
		ticketScrollPane.setViewportView(ticketPanel);

		inforPane.add(ticketScrollPane);
		
		listOfTicket();
		
		// add component to userInforPane panel----------------------------------------------
		inforPane.setBackground(Color.GRAY);		

		//split Panel--------------------------------------------------------------------------
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, idScrollPane,
				inforPane);
		splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(200);
        splitPane.setPreferredSize(new Dimension(830, 400));
       
        
		add(splitPane);
		add(logout, BorderLayout.SOUTH);
	}

	public JPanel ticketList(int flightID, int departFrom, int arrivalAt, 
			Time departTime, Time arrivalTime, double basePrice, 
			Date departDate, Date arrivaltDate, int aircraft, int seat, int customerID){
				
		JPanel tickList = new JPanel();
		GridLayout layout = new GridLayout(0, 4);
		tickList.setLayout(layout);
		tickList.setBackground(Color.LIGHT_GRAY);
		JButton cancel = new JButton("Cancel!!!");
		cancel.setFont(font);
		cancel.setBackground(Color.WHITE);
		
		Airport depart = DatabaseAccess.getAirport(departFrom);
		Airport arrive = DatabaseAccess.getAirport(arrivalAt);
		Aircraft craft = DatabaseAccess.getAircraft(aircraft);

		//-------- Ticket information-------------------------
		
		tickList.add(new JLabel(depart.getAirportName() + " --> " + arrive.getAirportName()));
		tickList.add(new JLabel(departTime + " --> " + arrivalTime));
		tickList.add(new JLabel(""));
		tickList.add(new JLabel(Double.toString(basePrice)));
		tickList.add(new JLabel(departDate.toString() + " --> " + arrivaltDate.toString()) );
		tickList.add(new JLabel("    " + craft.toString()));
		tickList.add(new JLabel("Seat: " + seat));
		tickList.add(cancel); 
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.out.println("cancel it");
			JOptionPane.showMessageDialog (null, "Ticket Cancel",
					 "Information", JOptionPane.INFORMATION_MESSAGE); 
			DatabaseAccess.dropFlight(customerID, flightID);
			tickList.removeAll();
			listOfTicket(); 
			tickList.updateUI();
			}
		});
		
		return tickList;
	}
	
	/**
	 * change the customer information in the JTextArea 
	 * when employee click select on customerName
	 */
	public void valueChanged(ListSelectionEvent e) {
		//Unused inherited method.
	}
	
	/**
	 * list of each ticket
	 */
	public void listOfTicket(){
		idList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				
				if (!arg0.getValueIsAdjusting()) {
					ticketPanel.removeAll();
					ticketPanel.updateUI();
					num = idList.getSelectedIndex()+1;

					String[] CusID = idList.getSelectedValue().split("-");
					int customerID = Integer.parseInt(CusID[0]);
					
					List<Reservation> departFlight = DatabaseAccess.getReservations(customerID);		
					
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
								basePrice, departDate, arrivaltDate, aircraft, seat, customerID));
						ticketPanel.setPreferredSize(new Dimension(600, 70*(i+1)+ 100));
						ticketPanel.add(Box.createHorizontalStrut(5));
						
						ticketPanel.updateUI();
					}
				}	
			}
		});
	}
}