package model;

public class Passenger 
{
	private int passengerID;
	private String passengerFirstName;
	private String passengerLastName;
	private String passengerPhoneNumber;
	private String passengerEmailAddress;
	private String passengerAddress1;
	private String passengerAddress2;
	private String passengerCity;
	private String passengerState;
	private String passengerCountry;
	private String passportCountry;
	private String passengerGender;
	private String passengerMeal;
	
	public Passenger(int passengerID, String passengerFirstName, String passengerLastName, String passengerPhoneNumber,
			String passengerEmailAddress, String passengerAddress1, String passengerAddress2, String passengerCity,
			String passengerState, String passengerCountry, String passportCountry, String passengerGender, String passengerMeal) {
		this.passengerID = passengerID;
		this.passengerFirstName = passengerFirstName;
		this.passengerLastName = passengerLastName;
		this.passengerPhoneNumber = passengerPhoneNumber;
		this.passengerEmailAddress = passengerEmailAddress;
		this.passengerAddress1 = passengerAddress1;
		this.passengerAddress2 = passengerAddress2;
		this.passengerCity = passengerCity;
		this.passengerState = passengerState;
		this.passengerCountry = passengerCountry;
		this.passportCountry = passportCountry;
		this.passengerGender = passengerGender;
		this.passengerMeal = passengerMeal;
	}

	public Passenger(String passengerFirstName, String passengerLastName, String passengerPhoneNumber,
			String passengerEmailAddress, String passengerAddress1, String passengerAddress2, String passengerCity,
			String passengerState, String passengerCountry, String passportCountry, String passengerGender, String passengerMeal) {
		this.passengerFirstName = passengerFirstName;
		this.passengerLastName = passengerLastName;
		this.passengerPhoneNumber = passengerPhoneNumber;
		this.passengerEmailAddress = passengerEmailAddress;
		this.passengerAddress1 = passengerAddress1;
		this.passengerAddress2 = passengerAddress2;
		this.passengerCity = passengerCity;
		this.passengerState = passengerState;
		this.passengerCountry = passengerCountry;
		this.passportCountry = passportCountry;
		this.passengerGender = passengerGender;
		this.passengerMeal = passengerMeal;
	}
	
	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public String getPassengerFirstName() {
		return passengerFirstName;
	}

	public void setPassengerFirstName(String passengerFirstName) {
		this.passengerFirstName = passengerFirstName;
	}

	public String getPassengerLastName() {
		return passengerLastName;
	}

	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}

	public String getPassengerPhoneNumber() {
		return passengerPhoneNumber;
	}

	public void setPassengerPhoneNumber(String passengerPhoneNumber) {
		this.passengerPhoneNumber = passengerPhoneNumber;
	}

	public String getPassengerEmailAddress() {
		return passengerEmailAddress;
	}

	public void setPassengerEmailAddress(String passengerEmailAddress) {
		this.passengerEmailAddress = passengerEmailAddress;
	}

	public String getPassengerAddress1() {
		return passengerAddress1;
	}

	public void setPassengerAddress1(String passengerAddress1) {
		this.passengerAddress1 = passengerAddress1;
	}

	public String getPassengerAddress2() {
		return passengerAddress2;
	}

	public void setPassengerAddress2(String passengerAddress2) {
		this.passengerAddress2 = passengerAddress2;
	}

	public String getPassengerCity() {
		return passengerCity;
	}

	public void setPassengerCity(String passengerCity) {
		this.passengerCity = passengerCity;
	}

	public String getPassengerState() {
		return passengerState;
	}

	public void setPassengerState(String passengerState) {
		this.passengerState = passengerState;
	}

	public String getPassengerCountry() {
		return passengerCountry;
	}

	public void setPassengerCountry(String passengerCountry) {
		this.passengerCountry = passengerCountry;
	}

	public String getPassportCountry() {
		return passportCountry;
	}

	public void setPassportCountry(String passportCountry) {
		this.passportCountry = passportCountry;
	}
	
	public String getPassengerGender() {
		return passengerGender;
	}

	public String getPassengerMeal() {
		return passengerMeal;
	}
	
}
