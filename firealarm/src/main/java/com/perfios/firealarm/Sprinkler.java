package com.perfios.firealarm;

public class Sprinkler {
	private Room room;
	private boolean isOn;
	public Sprinkler(Room room, boolean isOn) {
		super();
		this.room = room;
		this.isOn = isOn;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public boolean getIsOn() {
		return isOn;
	}
	public void setIsOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	
}
