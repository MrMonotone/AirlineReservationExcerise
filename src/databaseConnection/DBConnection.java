package databaseConnection;

// DBConnection.java  name: DBConnection Class
// Writen by Nikolay Kravchuk.
// Date: 05/15/2014

import java.sql.*;
import java.util.List;
import java.util.Vector;

import model.Aircraft;
import model.Airport;
import model.Flight;
import model.Passenger;
import model.Reservation;
import model.User;


// Connection class that handels DB connection.
public class DBConnection {

	private Connection Conn ; 
	// Initialize connection 
	public DBConnection( String theConnectionURL, String theUserName, String theUserPassword)
	{
		// try connect 
	      try {
	         //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         Class.forName("com.mysql.jdbc.Driver");
	         Conn = DriverManager.getConnection(theConnectionURL, theUserName, theUserPassword);
	         // debug to console
	         System.out.println("connected");	       
	      } catch (Exception e) {
	    	  // show error
	         e.printStackTrace();
	      }
	}
	
	public String SelectQuery(String query, int column){
	    Statement statement;
	    String result = "";
	    int i =0;
		try {
			statement = Conn.createStatement();
	        String queryString; 
	        if (query == ""){
	        	queryString = "select * from sysobjects where type='u'";
	        } else {
	        	queryString = query;
	        }
	        ResultSet rset = statement.executeQuery(queryString);
	        	
//	        result = rset.getString(2);
	        while (rset.next()) {
	           result = rset.getString(1);
//	           System.out.println("inclass " + result);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public User getUser(String emailAddress)
	{
		User result = null;
		Statement statement;
		String test = "select * from Users where emailAddress = \"" + emailAddress + "\"";
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	result = new User(rset.getString("emailAddress"), rset.getString("password"), rset.getInt("passengerID"));
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public void createUser(String email, String password, int passengerID)
	{
		 // create new user 
		  UpdateQuery("insert into Users ("
					+ "emailAddress, password, passengerID)"
					 +" VALUES( \"" // values for columns
					 + email + "\", \""
					 + password + "\", \""
					 + passengerID + "\")");
		
	}
	// INSERT INTO `TCSS445Airline`.`Passengers` (`passengerID`, `passengerFirstName`, `passengerLastName`, `passengerPhoneNumber`, `passengerEmailAddress`, `passengerAddress1`, `passengerAddress2`, `passengerCity`, `passengerState`, `passengerCountry`, `passportCountry`, `passengerGender`, `passengerMeal`) VALUES ('4', 'jason', 'num', '3423423432', 'jason@mail.com', '213 come', 'fff', 'tacoma', 'WA', 'USA', 'HongKong', 'Male', 'Meat');

	public void createPassenger(Passenger p)
	{
		//INSERT INTO Passengers (`passengerFirstName`, `passengerLastName`, `passengerPhoneNumber`, `passengerEmailAddress`, `passengerAddress1`, `passengerAddress2`, `passengerCity`, `passengerState`, `passengerCountry`, `passportCountry`, `passengerGender`, `passengerMeal`) VALUES ('test', 'test', 'a', 'a', 's', 's', 's', 's', 'd', 'd', 'd', 'd');

		UpdateQuery("INSERT INTO `TCSS445Airline`.`Passengers` (`passengerFirstName`," 
		+ " `passengerLastName`, `passengerPhoneNumber`, `passengerEmailAddress`, `passengerAddress1`," 
				+ " `passengerAddress2`, `passengerCity`, `passengerState`, `passengerCountry`, " 
		+ "`passportCountry`, `passengerGender`, `passengerMeal`) " 
				+ "VALUES ('" + p.getPassengerFirstName() + "', '" + p.getPassengerLastName() + "', '" 
		+p.getPassengerPhoneNumber() + "', '" + p.getPassengerEmailAddress() + "'," 
		+ " '" + p.getPassengerAddress1() + "', '" + p.getPassengerAddress2() + "', '" + p.getPassengerCity() 
		+ "', '" + p.getPassengerState() + "', '" + p.getPassengerCountry() 
		+ "', '" + p.getPassportCountry() + "', '" + p.getPassengerGender() + "', '" + p.getPassengerMeal() +"')");

	}
	
	public void updatePassenger(Passenger p)
	{
		 // create new order 
		// UPDATE `TCSS445Airline`.`Passengers` SET `passengerFirstName`='employeeFirstName2', `passengerLastName`='employeeLastName2', `passengerPhoneNumber`='445-998-98762', `passengerEmailAddress`='employee@mail.com2', `passengerAddress1`='432 44th St2', `passengerAddress2`='2', `passengerCity`='Tacoma2', `passengerState`='WA2', `passengerCountry`='USAw', `passportCountry`='USA2', `passengerGender`='Female2', `passengerMeal`='No Food2' WHERE `passengerID`='4';

		  UpdateQuery("UPDATE `TCSS445Airline`.`Passengers` "
		  			+ "SET `passengerFirstName`='" + p.getPassengerFirstName() 
		  			+ "', `passengerLastName`='" + p.getPassengerLastName() 
		  			+ "', `passengerPhoneNumber`='" + p.getPassengerPhoneNumber() 
		  			+ "', `passengerEmailAddress`='" + p.getPassengerEmailAddress()
		  			+ "', `passengerAddress1`='"+ p.getPassengerAddress1() 
		  			+ "', `passengerAddress2`='" + p.getPassengerAddress2() 
		  			+ "', `passengerCity`='" + p.getPassengerCity() 
		  			+ "', `passengerState`='" + p.getPassengerState() 
		  			+ "', `passengerCountry`='" + p.getPassengerCountry() 
		  			+ "', `passportCountry`='" + p.getPassportCountry() 
		  			+ "', `passengerGender`='" + p.getPassengerGender() 
		  			+ "', `passengerMeal`='"+ p.getPassengerMeal() 
		  			+ "' WHERE `passengerID`='" + p.getPassengerID() + "'");
	}
	
	public List<Airport> getDepartures()
	{
		List<Airport> result = new Vector<Airport>();
		Statement statement;
		String test = "SELECT distinct airportID, airportName, airportCity, airportState, airportCountry from Flights join Airports on departureFrom = airportID";
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	Airport a = new Airport(rset.getInt("airportID"), rset.getString("airportName"), rset.getString("airportCity"),
	        						rset.getString("airportState"), rset.getString("airportCountry"));
	        result.add(a);
	        }
	        
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public List<Airport> getArrivals(int airportID)
	{
		List<Airport> result = new Vector<Airport>();
		Statement statement;
		String test = "SELECT distinct airportID, airportName, airportCity, airportState, airportCountry from Flights join Airports on arrivalAt = airportID where departureFrom = " + airportID;
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	Airport a = new Airport(rset.getInt("airportID"), rset.getString("airportName"), rset.getString("airportCity"),
	        						rset.getString("airportState"), rset.getString("airportCountry"));
	        result.add(a);
	        }
	        
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public Passenger getPassenger(int passengerID)
	{
	    Statement statement;
	    Passenger p = null;
		try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Passengers where passengerID = " + passengerID);
	        while (rset.next()) {
	        	p  = new Passenger(rset.getInt("passengerID"), rset.getString("passengerFirstName"), rset.getString("passengerLastName"), rset.getString("passengerPhoneNumber"), 
	        						rset.getString("passengerEmailAddress"), rset.getString("passengerAddress1"), rset.getString("passengerAddress2"), rset.getString("passengerCity"), 
	        						rset.getString("passengerState"), rset.getString("passengerCountry"), rset.getString("passportCountry"), rset.getString("passengerGender"), rset.getString("passengerMeal"));
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	
	// update query
	public int UpdateQuery(String query){
		Statement stm;
		int result = 0;
		try {
			stm = Conn.createStatement();
			stm.executeUpdate(query); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return result;
	}

	public List<Flight> getFlights(int passengerID) {
		List<Flight> results = new Vector<Flight>();
	    Statement statement;
		try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Reservations r join Flights f on r.flightID = f.flightID where passengersID = " + passengerID);
	        while (rset.next()) {
	        	Flight f  = new Flight(rset.getInt("flightID"), rset.getInt("aircraftID"), rset.getDouble("basePrice"), rset.getInt("departureFrom"), 
	        						rset.getDate("departureDate"),rset.getTime("departureTime"), rset.getInt("arrivalAt"), rset.getDate("arrivalDate"), rset.getTime("arrivalTime"));
	        	results.add(f);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public void dropFlight(int passengerID, int flightID) {
//		int roundtrip = Integer.parseInt(SelectQuery("Select * from Reservations where passengerID = " + passengerID + " && flightID = " + flightID, 5));
//		if (roundtrip != 0)
//		{
//			UpdateQuery("Delete from Reservations where passengerID = " + passengerID + " && flightID = " + roundtrip);
//		}
		UpdateQuery("Delete from Reservations where passengersID = " + passengerID + " and flightID = " + flightID );
//		System.out.println("dropFlight " + "Delete from Reservations where passengersID = " + passengerID + " and flightID = " + flightID );
	}

	public Aircraft getAircraft(int aircraftID) {
		Aircraft result = null;
		Statement statement;
	    try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("SELECT * from Aircrafts WHERE aircraftID = " + aircraftID);
	        while (rset.next())
	        {
	        	result = new Aircraft(rset.getInt("aircraftID"), rset.getString("make"), rset.getString("model"), rset.getInt("capacity"));
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public List<Reservation> getReservation(int passengersID)
	{
		List<Reservation> result = new Vector<Reservation>();
		Statement statement;
		Reservation r;
		Flight f;
	    try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Reservations r join Flights f on r.flightID = f.flightID where passengersID = " + passengersID);
	        while (rset.next())
	        {
	        	f = new Flight(rset.getInt("flightID"), rset.getInt("aircraftID"), rset.getDouble("basePrice"), rset.getInt("departureFrom"), 
						rset.getDate("departureDate"),rset.getTime("departureTime"), rset.getInt("arrivalAt"), rset.getDate("arrivalDate"), rset.getTime("arrivalTime"));
	        	r = new Reservation(rset.getInt("passengersID"), f, rset.getInt("seatAssignment"), rset.getDouble("pricePaid"), rset.getInt("roundTrip"));
	        	result.add(r);
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public void createReservation(int passengersID, int flightID, double pricePaid, int seat)
	{
		  UpdateQuery("insert into Reservations ("
					+ "passengersID, flightID, pricePaid, seatAssignment)"
					 +" VALUES(" // values for columns
					 + passengersID + ","
					 + flightID + ","
					 + pricePaid + ","
					 + seat
					 + ") " );
	}

	public List<Passenger> getPassengers() {
		List<Passenger> result = new Vector<Passenger>();
	    Statement statement;
		try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Passengers");
	        while (rset.next()) {
	        	Passenger p  = new Passenger(rset.getInt("passengerID"), rset.getString("passengerFirstName"), rset.getString("passengerLastName"), rset.getString("passengerPhoneNumber"), 
	        						rset.getString("passengerEmailAddress"), rset.getString("passengerAddress1"), rset.getString("passengerAddress2"), rset.getString("passengerCity"), 
	        						rset.getString("passengerState"), rset.getString("passengerCountry"), rset.getString("passportCountry"), rset.getString("passengerGender"), rset.getString("passengerMeal"));
	        result.add(p);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<String> getAirportStrings()
	{
		List<String> result = new Vector<String>();
		Statement statement;
		String test = "SELECT * from Airports";
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	Airport a = new Airport(rset.getInt("airportID"), rset.getString("airportName"), rset.getString("airportCity"),
	        						rset.getString("airportState"), rset.getString("airportCountry"));
	        	result.add(a.toString());
	        }
	        
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
		
	}

	public int getBookedSeatCount(int flightID) {
		
		int result = 0;
		Statement statement;
		String test = "Select count(seatAssignment) from Reservations where flightID = " + flightID;
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	result = rset.getInt(1);
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		
		return result;
	}
	
	public List<Flight> getFlights(int departID, int arrivalID, Date departDate, Date arrivalDate)
	{
		List<Flight> results = new Vector<Flight>();
	    Statement statement;
	    System.out.println(arrivalDate.toString());
		try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Reservations join Flights where departureFrom = " + departID + " && arrivalAt = "
	        										+ arrivalID +" && datediff(day,departureDate, \""+ departDate.toString() + "\") = 0 && datediff(day,arrivalDate, \""
	        										+ arrivalDate.toString() + "\") = 0");
	        while (rset.next()) {
	        	Flight f  = new Flight(rset.getInt("flightID"), rset.getInt("aircraftID"), rset.getDouble("basePrice"), rset.getInt("departureFrom"), 
						rset.getDate("departureDate"),rset.getTime("departureTime"), rset.getInt("arrivalAt"), rset.getDate("arrivalDate"), rset.getTime("arrivalTime"));
	        	results.add(f);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public List<Flight> getFlights(int departID, int arrivalID, Date departDate)
	{
		List<Flight> results = new Vector<Flight>();
	    Statement statement;
	    System.out.println(departDate.toString());
		try {
			statement = Conn.createStatement();
	        ResultSet rset = statement.executeQuery("Select * from Flights where departureFrom = " + departID + " && arrivalAt = "
	        										+ arrivalID +" && datediff(departureDate, \""+ departDate.toString() + "\") = 0");
	        while (rset.next()) {
	        	Flight f  = new Flight(rset.getInt("flightID"), rset.getInt("aircraftID"), rset.getDouble("basePrice"), rset.getInt("departureFrom"), 
						rset.getDate("departureDate"),rset.getTime("departureTime"), rset.getInt("arrivalAt"), rset.getDate("arrivalDate"), rset.getTime("arrivalTime"));
	        	results.add(f);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public List<String> getArrivalStrings(int departID) {
		List<String> result = new Vector<String>();
		Statement statement;
		String test = "SELECT distinct airportID, airportName, airportCity, airportState, airportCountry from Flights join Airports on arrivalAt = airportID where departureFrom = " + departID;
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	Airport a = new Airport(rset.getInt("airportID"), rset.getString("airportName"), rset.getString("airportCity"),
	        						rset.getString("airportState"), rset.getString("airportCountry"));
	        result.add(a.toString());
	        }
	        
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	public Airport getAirport(int airportID)
	{
		Airport result = null;
		Statement statement;
		String test = "select * from Airports where airportID = \"" + airportID + "\"";
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	result = new Airport(rset.getInt("airportID"), rset.getString("airportName"), rset.getString("airportCity"), rset.getString("airportState"), rset.getString("airportCountry"));
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	
	public List<Integer> getSeatsTaken(int flightID) {
		List<Integer> result = new Vector<Integer>();
		Statement statement;
		String test = "SELECT seatAssignment from Reservations where flightID = " + flightID;
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	result.add(rset.getInt(1));
	        }
	        
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
	public int getAircraftCapacity(int flightID)
	{
		int result = 0;
		Statement statement;
		String test = "Select capacity from Flights d join Aircrafts a on a.aircraftID = d.aircraftID where flightID = " + flightID;
	    try {
			statement = Conn.createStatement();
			
	        ResultSet rset = statement.executeQuery(test);
	        while (rset.next())
	        {
	        	result =rset.getInt(1);
	        }
	        
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
		return result;	
	}
}