The EventIndex class is the data structure that holds the index of events.
It is a singleton, which ensures only one index can ever exist.

The EventList class holds the list of events and subevents themselves.

The Event class is the superclass of the SubEvent class.
The SubEvent class is able to use the values and methods in the Event class due to inheritance.
This design upholds the open/closed principle, because it allows for further subclasses of Event to be made.
This can allow for the creation of classes that, for example, have participants or end dates, without the modification of my code.

The main() method is in the assignment2-19103284\src\main\java\a2\Application.java file.

I added 4 events to the JSON document that was provided on BlackBoard:
1. The Irish Rebellion of 1798 - no subevents
2. Easter Rising - no subevents
3. Civil Rights Movement - North of Ireland - 3 subevents
4. Cold War - 4 subevents
This document is in the package - jsonData.json (found at assignment2-19103284\jsonData.json)

Both the class diagram and the use case diagram can be found in the UML folder: assignment2-19103284\UML

The use case diagram is designed as follows:
There is a use case for the reading of the JSON document. The only interacting actor is the JSON document itself.
The JSON document is an actor since it is outside the system.
The user first encounters the Choose Single Year or Range use case. This is reflected in the main() method, where they must choose either 1 or 2.
The use case Enter Year shares an include relationship with the Choose Single Year or Range use case.
This is because, regardless of which option is chosen, the user will have to enter a year.
Choose Single Year or Range and another use case, Enter End of Year Range share an extend relationship.
This is because the latter is only ever encountered if the user chooses a range during the former.
There is a use case Return Events. This extends Enter Year, because the first only follows directly from the second if range ending year isn't required.
Return Events and Enter End of Range Year share an include relationship, because the former follows directly from the latter.
The Return Subevents use case shares an extend relationship with the Return Events use case.
This is because a subevent is only returned if the event returned contains subevents.