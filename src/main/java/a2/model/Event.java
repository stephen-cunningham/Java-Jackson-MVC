package a2.model;

import java.time.Year;
import java.util.ArrayList;

public class Event implements Comparable<Event>{
	private String name;
	private String type;
	private Year year;
	private ArrayList<SubEvent> subevents;
	
	//using a default constructor to ensure Jackson can work with the class
	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<SubEvent> getSubevents() {
		return subevents;
	}

	public void setSubevents(ArrayList<SubEvent> subEvents) {
		this.subevents = subEvents;
	}
	
	//implementation of the Comparable compareTo method
	public int compareTo(Event o) {
		if(this.getYear().compareTo(o.getYear()) > 0) {
			return 1;
		}else if(this.getYear().compareTo(o.getYear()) < 0) {
			return -1;
		}else {
			return this.getName().compareTo(o.getName());//if the years are equal, it defaults to sorting by name (which is default alphabetical)
		}
	}

	@Override
	public String toString() {
		String output;
		
		output = "The event is " + this.getName() + " and it happened in " + this.getYear() + ".";
		//if the subevent has a type, this is added to the output
		if(this.getType() != null) {
			output += " It was a " + this.getType() + ".";
		}
		if(this.subevents != null) {//this runs only if the events has subevent(s)
			System.out.println("\n");
			for(SubEvent s: this.subevents) {
				output += s.toString();//adding the overridden toString() method in the subevent to the output here in the toString() of the event
			}
		}
		return output;
	}
}