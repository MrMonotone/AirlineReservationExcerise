package model;

public class Reservation {
	private int passengersID;
	private Flight flight;
	private int seatAssignment;
	private double pricePaid;
	private int roundTrip;
	public Reservation(int passengersID, Flight flight, int seatAssignment, double pricePaid, int roundTrip) {
		this.passengersID = passengersID;
		this.flight = flight;
		this.seatAssignment = seatAssignment;
		this.pricePaid = pricePaid;
		this.roundTrip = roundTrip;
	}
	
	public Flight getFlight()
	{
		return flight;
	}

	public int getPassengersID() {
		return passengersID;
	}

	public int getSeatAssignment() {
		return seatAssignment;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public int getRoundTrip() {
		return roundTrip;
	}
	
	
}
