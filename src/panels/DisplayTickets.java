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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import databaseConnection.DatabaseAccess;
import model.Aircraft;
import model.Airport;
import model.Flight;
import project.Main;

/**
 * Set up component.  need to calculate the price
 */
@SuppressWarnings("serial") //Not implementing serialization suppressing warning.
public class DisplayTickets extends JPanel implements ListSelectionListener {
	
	private JScrollPane idScrollPane;	 
	private Font font;
	private ImageIcon icon = new ImageIcon(Main.class.getResource("/image/icon2.png"));
	private int myWidth;
	private int myHeight;
	int departFlightID;
	int arrivalFlightID;
	String dateFlight;
	int travelType;
	String returnDate;
	int ppl;
	private List<Flight> returnFlight;

	public DisplayTickets(int width, int height, int departID, int arrivalID, String date,
			int travelTypes, String returnDateBack, int people) throws ParseException {
		System.out.println("travelType = " + travelType + " returnDate= " + returnDate +  " people= " + people);
		myWidth = width;
		myHeight = height;
		departFlightID = departID;
		arrivalFlightID = arrivalID;
		dateFlight = date;
		travelType = travelTypes;
		returnDate = returnDateBack;
		ppl = people;
		setBackground(Color.DARK_GRAY);
		font = new Font("SansSerif", Font.BOLD, 20);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//----------------------------------------------------------------------------------------------------------		
		idScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		idScrollPane.setPreferredSize(new Dimension(830, 410));	
		
		JPanel ticketPanel = new JPanel();
		BoxLayout boxLayoutTwo = new BoxLayout(ticketPanel, BoxLayout.Y_AXIS);
		ticketPanel.setLayout(boxLayoutTwo);
		ticketPanel.setBackground(Color.BLACK); 

		// departure flight
		List<Flight> departFlight = DatabaseAccess.getFlights(departFlightID, arrivalFlightID, 
				new Date(sdf.parse(dateFlight).getTime()));		
		
		// departure flight which is add element to display each ticket
		for(int i = 0; i < departFlight.size(); i++){

			Flight f = departFlight.get(i);
			
			int flightID = f.getFlightsID();
			int departFrom = f.getDepartureFrom();
			int arrivalAt = f.getArrivalAt();
			Time departTime = f.getDepartureTime();
			Time arrivalTime = f.getArrivalTime();		
			double price = DatabaseAccess.calculatePrice(f);
			System.out.println("from display ticket -- basePrice = " + f.getBasePrice() + "  newPrice = "+ price);
			Date departDate = f.getDepartureDate();
			Date arrivaltDate = f.getArrivalDate();
			int aircraft = f.getAircraftID();
			
			ticketPanel.add(ticketList(flightID, departFrom, arrivalAt, departTime, arrivalTime, 
					price, departDate, arrivaltDate, aircraft));
			ticketPanel.setPreferredSize(new Dimension(800, 70*(i+1)+ 100));
			ticketPanel.add(Box.createHorizontalStrut(5));
		}
		if(travelType == 2){
			returnFlight = DatabaseAccess.getFlights(arrivalFlightID, departFlightID, 
					new Date(sdf.parse(returnDate).getTime()));
			
			JLabel label = new JLabel("---------Return Flight ----------                          ");
			label.setForeground(Color.white);
			label.setFont(new Font("Serif", Font.BOLD, 30));
			ticketPanel.add(label);
			ticketPanel.add(Box.createHorizontalStrut(5));
			
			// return flight, add element to display each ticket 
			for(int i = 0; i < returnFlight.size(); i++){
				Flight rf = returnFlight.get(i);
				
				int returnFlightID = rf.getFlightsID();
				int returnFrom = rf.getDepartureFrom();
				int returnAt = rf.getArrivalAt();
				Time returnDepartTime = rf.getDepartureTime();
				Time returnArrivalTime = rf.getArrivalTime();
				double returnBasePrice = DatabaseAccess.calculatePrice(rf); 
				Date returnDepartDate = rf.getDepartureDate();
				Date returnArrivaltDate = rf.getArrivalDate();
				int returnAircraft = rf.getAircraftID();

				ticketPanel.add(ticketList(returnFlightID, returnFrom, returnAt, returnDepartTime,
						returnArrivalTime, returnBasePrice, returnDepartDate, returnArrivaltDate, returnAircraft));
				ticketPanel.setPreferredSize(new Dimension(800, (70*(i+1)+ 100)*2));
				ticketPanel.add(Box.createHorizontalStrut(5));
			}
			
		}
		
		idScrollPane.revalidate();
		idScrollPane.setViewportView(ticketPanel);
		add(idScrollPane);

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
	 * @param flightID
	 * @param departFrom
	 * @param arrivalAt
	 * @param departTime
	 * @param arrivalTime
	 * @param basePrice
	 * @param departDate
	 * @param arrivaltDate
	 * @param aircraft
	 * @return
	 */
	public JPanel ticketList(int flightID, int departFrom, int arrivalAt, 
			Time departTime, Time arrivalTime, double basePrice, 
			Date departDate, Date arrivaltDate, int aircraft){

		Airport depart = DatabaseAccess.getAirport(departFrom);
		Airport arrive = DatabaseAccess.getAirport(arrivalAt);
		Aircraft craft = DatabaseAccess.getAircraft(aircraft);
		JPanel tickList = new JPanel();
		GridLayout layout = new GridLayout(0, 3);
		tickList.setLayout(layout);
		tickList.setBackground(Color.LIGHT_GRAY);
		JButton buy = new JButton("BUY!!!!");
		buy.setFont(font);
		buy.setBackground(Color.GREEN);
		
		
		//-------- Ticket information-------------------------
		tickList.add(new JLabel(depart.getAirportName() + " --> " + arrive.getAirportName()));
		tickList.add(new JLabel(departTime + " --> " + arrivalTime));
		tickList.add(new JLabel(Double.toString(basePrice)));
		tickList.add(new JLabel(departDate.toString() + " --> " + arrivaltDate.toString()));
		tickList.add(new JLabel(craft.toString()));
		
		tickList.add(buy); 
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.out.println("ticket buy " + flightID);
			fillingInfor(flightID, ppl, basePrice);
			}
		});
		
		return tickList;
	}
	/**
	 * need to login or create user before buy ticket 
	 * @param flightIds
	 * @param people
	 * @param price
	 * @return
	 */
	public JFrame fillingInfor(int flightIds, int people, double price){
		JFrame inforFrame = new JFrame();
		inforFrame.setSize(myWidth, myHeight);
		inforFrame.setIconImage(icon.getImage());
		inforFrame.setResizable(false);
		inforFrame.setLocationRelativeTo(null);
		inforFrame.setVisible(true);
		
		Login login = new Login(myWidth, myHeight, inforFrame, flightIds, people, price);
		
		inforFrame.getContentPane().removeAll();
		inforFrame.getContentPane().add(login, BorderLayout.CENTER);

		return inforFrame;
	}
	
	
	
}