package a2.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

//this implements the singleton pattern
public class EventIndex {
	private static EventIndex indexHold;//declaring a static instance of the EventIndex class
	private TreeMap<Year, TreeSet<Event>> index = new TreeMap<Year, TreeSet<Event>>();//declaring a TreeMap that will be used to organise the events
	
	private EventIndex() {
		this.index = new TreeMap<Year, TreeSet<Event>>();
	}

	//this ensures that, if the static instance hasn't been instantiated, it calls the constructor above to create one
	//the single instance is returned
	public static EventIndex getInstance() {
		if(indexHold == null) {
			indexHold = new EventIndex();
		}
		return indexHold;
	}
	
	//this adds events to the index
	public void add(Event event) throws IllegalAccessException  {
		TreeSet<Event> set = new TreeSet<Event>();//creating a TreeSet that will be temporarily used to store the event
		
		if(index.containsKey(event.getYear())) {//if the index already contains an event of the year of the incoming event
			set = index.get(event.getYear()); //the value from the map is stored in the TreeSet
			set.add(event);//adding our current event to the existing set of events for that year
		}else{
			set.add(event);//if the year of the incoming event is new, add our current event
		}
		
		if(event.getSubevents() != null) {//if the event contains subevents
			for(SubEvent s: event.getSubevents()) {//for each subevents
				if(s.getYear().isBefore(event.getYear())){//if the year of the subevent is before the year of the event
					ArrayList<SubEvent> sEvents = event.getSubevents();//temporarily store the subevents in an ArrayList
					sEvents.remove(s);//remove this subevent from the list (since we don't want it, as it is illegal, i.e. not allowed because it precedes the event)
					event.setSubevents(sEvents);//setting the subevents of the event to everything other than the illegal subevent
					this.index.put(event.getYear(), set);//adding the set of events to the index
					throw new IllegalAccessException ("Subevent happened before event");//this handles the error by throwing the execption with the given message
				}
			}
		}
		this.index.put(event.getYear(), set);
	}

	public Map<Year, TreeSet<Event>> getTreeSetEvent(){
		return index;
	}
	
	//this checks if the year is contained in the index, i.e. if that year has any stored events 
	public void checkYear(Year y) {
		if(index.containsKey(y)) {
			TreeSet<Event> set = index.get(y);
			for(Event e: set) {
				System.out.println(e.toString());//if the year has events, print them out using their respective toString() methods
			}
		}else {
			System.out.println("This year doesn't have any recorded events.");
		}
	}
	
	//this checks if the years in the range are contained in the index, i.e. if those years have any stored events
	public void checkRange(Year y1, Year y2) {
		Boolean check = false;//this is used to check if there is anything in the given range
		while(!y1.equals(y2.plusYears(1))) {//.plusYears(1) ensures that the far extermity (e.g. 1970 in 1950-1970) is accounted for
			if(index.containsKey(y1)) {
				check = true;//if there is anything in the given range, this is set to true
				TreeSet<Event> set = index.get(y1);
				for(Event e: set) {
					System.out.println(e.toString());
				}
			}
			y1=y1.plusYears(1);//this increments the year at the end of each iteration of the while loop
		}
		if(!check) {//if check is still false, the user is informed that the range they entered holds nothing
			System.out.println("Nothing was found for your range!");
		}
	}
}