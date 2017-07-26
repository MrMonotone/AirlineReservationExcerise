package model;

public class Airport {
	private int airportID;
	private String airportName;
	private String airportCity;
	private String airportState;
	private String airportCountry;
	
	public Airport(int airportID, String airportName, String airportCity, String airportState, String airportCountry) {
		this.airportID = airportID;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportState = airportState;
		this.airportCountry = airportCountry;
	}
	
	public int getAirportID() {
		return airportID;
	}

	public void setAirportID(int airportID) {
		this.airportID = airportID;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCity() {
		return airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	public String getAirportState() {
		return airportState;
	}

	public void setAirportState(String airportState) {
		this.airportState = airportState;
	}

	public String getAirportCountry() {
		return airportCountry;
	}

	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}

	@Override
	public String toString() {
		return airportName + " " + airportCity + " " + airportState + " " + airportCountry;
	}
}
