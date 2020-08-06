package a2;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import a2.model.Event;
import a2.model.EventIndex;
import a2.model.EventsList;

public class Application{
	public static void main(String[] args) throws IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		int input;//stores the user choice below (if they want a specific year or range)
		Year year;
		Year yearEnd = null;
		/*
		 * this calls the static instance of the EventIndex class (which is a singleton)
		 * if an instance hasn't been created, it is now created
		 */
		EventIndex index = EventIndex.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//reading in from the JSON file using the ObjectMapper class and assigning the data to an EventsList object
			EventsList list = mapper.readValue(new File("jsonData.json"), EventsList.class);
			for(Event event: list.getEvents()) {//for each event (from the JSON file) in the list
				index.add(event);//add the event to the index
			}
		//Handling exceptions
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.print("Incorrect subevent found \n");//this ensures that an error message is printed, without crashing the programme
		}

		System.out.println("Would you like to get events from a specific year, or within a range of years?");
		System.out.println("Enter 1 for a specific year.");
		System.out.println("Enter 2 for a range of years.");

		input = sc.nextInt();
		
		while(input < 1 || input > 2) {
			System.out.print("Error. Enter 1 or 2: ");
			input = sc.nextInt();
		}
		
		while(input == 1 || input == 2) {
			if(input == 1) {
				System.out.print("Enter the year: ");
				year = Year.of(sc.nextInt());//taking in the year from the scanner and making it of the Year class
				index.checkYear(year);//calling the checkYear(Year) method - see the EventIndex class
			}else {
				System.out.print("Enter the year at the start of the range: ");
				year = Year.of(sc.nextInt());
				System.out.print("Enter the year at the end of the range: ");
				yearEnd = Year.of(sc.nextInt());
				/*
				 * this ensures that the years in the range are either equal, or the second is after the first
				 * if they years are equal, it is ok to return the year itself, still calling the checkRange(Year, Year) method
				 */
				if(!year.isAfter(yearEnd)) {
					index.checkRange(year, yearEnd);
				}else {//if the second year is before the first, then the user is forced to enter the years again
					System.out.println("Incorrect range. Try again. Make sure you enter a later year as the second year.");
				}
			}
		}
		sc.close();
	}
}