package com.perfios.firealarm;

public class Fire {
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Fire(Room room) {
		super();
		this.room = room;
	}

}
