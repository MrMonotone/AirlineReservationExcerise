package databaseConnection;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import model.Aircraft;
import model.Airport;
import model.Flight;
import model.Passenger;
import model.Reservation;
import model.User;

public class DatabaseAccess {

	static String connectionUrl = "";
	static String connectionUser = "";
	static String connectionPassword = "";

	static DBConnection db = new DBConnection(connectionUrl, connectionUser, connectionPassword);
	
	public static User login(String email, String pass)
	{
		User result = db.getUser(email);
		if(result != null)
		{
			if(!result.auth(pass))
			{
				return null;
			}
		}
		return result;	
	}
	
	public static int createAccount(String email, String password, Passenger p)
	{
		User result = db.getUser(email);
		int pID = -1;
		if (result == null)
		{
			db.createPassenger(p);
			System.out.println("Select passengerID from Passengers where passengerEmailAddress = \"" + email + "\"");
			pID = Integer.parseInt(db.SelectQuery("Select passengerID from Passengers where passengerEmailAddress = \"" + email + "\"", 1));			
			db.createUser(email, password, pID);
		}
		return pID;
	}
	
	public static void updatePassenger(Passenger newPassenger)
	{
		db.updatePassenger(newPassenger);
	}
	
	public static void createPassenger(Passenger newPassenger)
	{
		db.createPassenger(newPassenger);
	}
	
	public static Passenger getPassenger(int passengerID)
	{
		return db.getPassenger(passengerID);
	}
	
	public static List<Flight> getFlights(int passengerID)
	{
		return db.getFlights(passengerID);
	}
	
	public static List<Passenger> getPassengers()
	{
		return db.getPassengers();
	}
	
	public static List<Reservation> getReservations(int passengersID)
	{
		return db.getReservation(passengersID);
	}
	
	public static List<Flight> getFlights(int departID, int arrivalID, Date departDate, Date arrivalDate)
	{
		return db.getFlights(departID, arrivalID, departDate, arrivalDate);
	}
	
	public static List<Flight> getFlights(int departID, int arrivalID, Date departDate)
	{
		return db.getFlights(departID, arrivalID, departDate);
	}
	
//	public static void createReservation(Passenger p, Flight f, double pricePaid)
//	{
//		db.createReservation(p.getPassengerID(), f.getFlightsID(), pricePaid);
//	}
	public static void createReservation(int id, int flightId, double pricePaid, int seat)
	{
		db.createReservation(id, flightId, pricePaid, seat);
	}
	
	
	public static void dropFlight(int passengerID, int flightID)
	{
		db.dropFlight(passengerID, flightID);
	}
	
	public static List<String> getAirports()
	{
		return db.getAirportStrings();
	}
	
	public static Airport getAirport(int airportID)
	{
		return db.getAirport(airportID);
	}
	
	public static List<String> getArrivalStrings(int departID)
	{
		return db.getArrivalStrings(departID);
	}
	
	public static Aircraft getAircraft(int aircraftID)
	{
		return db.getAircraft(aircraftID);
	}
	
	public static double calculatePrice(Flight f)
	{
		double result = f.getBasePrice();
		long depart = f.getDepartureDate().getTime();
		long now = Calendar.getInstance().getTimeInMillis();
		long difference = now - depart;
		
		//milliseconds of 2 weeks
		long milli2week = 1209600000;
		
		System.out.println("from database -- Time now = " + now + " time depart = " + depart + " defference = " + difference
				+ " milli2week = " + milli2week);
		if (difference < milli2week)
		{
			int booked = db.getBookedSeatCount(f.getFlightsID());
			int capacity = db.getAircraft(f.getAircraftID()).getCapacity();
			double percent = ((double)booked / (double)capacity) * 100;
			System.out.println("from database -- booked = " + booked + " capacity= " + capacity + " percent = " + percent);
			if (percent > 75.0)
			{
				result *= 2;
			}
			else if (percent < 50.0)
			{
				result /= 2;
			}		
		}
		
		return result;
	}
	public static List<Integer> getSeatsTaken(int flightID)
	{
		return db.getSeatsTaken(flightID);
	}
	
	public static int getAircraftCapacity(int flightID)
	{
		return db.getAircraftCapacity(flightID);
	}
}
