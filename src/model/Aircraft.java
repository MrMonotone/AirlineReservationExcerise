package model;

public class Aircraft {
	private int aircraftID;
	private String make;
	private String model;
	private int capacity;
	
	public Aircraft(int aircraftID, String make, String model, int capacity) {
		this.aircraftID = aircraftID;
		this.make = make;
		this.model = model;
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	@Override
	public String toString() {
		return make + " " + model;
	}
	
}
