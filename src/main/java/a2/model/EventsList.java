package a2.model;

import java.util.ArrayList;

//this class holds the events and subevents
public class EventsList {
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
}