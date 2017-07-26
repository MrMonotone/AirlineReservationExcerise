package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Flight {
	private int flightsID;
	private int aircraftID;
	private double basePrice;
	private int departureFrom;
	private Date departureDate;
	private Time departureTime;
	private int arrivalAt;
	private Date arrivalDate;
	private Time arrivalTime;
	
	public Flight(int flightsID, int aircraftID, double basePrice, 
				  int departureFrom, Date departureDate, Time departureTime,
				  int arrivalAt, Date arrivalDate, Time arrivalTime) {
		this.flightsID = flightsID;
		this.aircraftID = aircraftID;
		this.basePrice = basePrice;
		this.departureFrom = departureFrom;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalAt = arrivalAt;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
	}
	
	public int getFlightsID() {
		return flightsID;
	}

	public void setFlightsID(int flightsID) {
		this.flightsID = flightsID;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public int getAircraftID() {
		return aircraftID;
	}

	public Date getDepartureDate() {
		return departureDate;
	}
	public int getDepartureFrom(){
		return departureFrom;
	}
	public int getArrivalAt(){
		return arrivalAt;
	}
	public Time getDepartureTime(){
		return departureTime;
	}
	public Time getArrivalTime(){
		return arrivalTime;
	}
	public Date getArrivalDate(){
		return arrivalDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return flightsID + "," + departureFrom + "," + arrivalAt + ","+ 
			departureTime + "," + arrivalTime + "," + basePrice + "," + departureDate + "," + 
			arrivalDate + "," + aircraftID;
	}
	
}
