package model;

public class User 
{
	private String emailAdress;
	private String password;
	private int passengerID;

	public User(String emailAddress, String password, int passengerID) 
	{
		this.emailAdress = emailAddress;
		this.password = password;
		this.passengerID = passengerID;
	}
	
	public boolean isEmployee()
	{
		return passengerID == 0;
	}
	
	public boolean auth(String attempt)
	{
		return attempt.equals(password);
	}
	
	public int getPassengerID() {
		return passengerID;
	}
}
