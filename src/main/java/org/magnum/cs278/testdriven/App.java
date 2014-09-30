package org.magnum.cs278.testdriven;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Iterator;

import java.util.Collections;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple application that demonstrates using Java dynamic Proxy objects and
 * reflection to swap out the implementation of one method on an object at
 * runtime.
 * 
 * @author jules
 *
 */
public class App {

	private static final String PARK_SPECIAL_PERMITS = "http://data.nashville.gov/resource/vygj-v677.json";

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final JavaType eventListType = objectMapper.getTypeFactory()
			.constructCollectionType(List.class, Event.class);

	/**
	 * The entry point to Java applications is a "main" method with the exact
	 * signature shown below.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		App app = new App();
		List<Event> evts = app.getParkSpecialPermits();
		for (Event e : evts) {
			System.out.println(e);
		}
	}

	public List<Event> getThreeThingsToDo() throws Exception {
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		DateTime now = DateTime.now();
		for (Event evt : evts) {
			if (evt.getDateTime().isAfter(now)) {
				toDo.add(evt);
				if (toDo.size() >= 3) {
					break;
				}
			}
		}

		return toDo;
	}

	public List<Event> getParkSpecialPermits() throws Exception {
		return objectMapper.readValue(new URL(
				PARK_SPECIAL_PERMITS),
				eventListType
				);
	}

	
	public List<Event> getEventsWithLocation(String location) throws Exception {
		List<Event> evts = getParkSpecialPermits();
		List<Event> evtsAtLocation = new ArrayList<Event>();
		for (Event evt : evts) {
			if (evt.getLocation().equals(location)) {
				evtsAtLocation.add(evt);
			}
		}
		return evtsAtLocation;
	}


	public List<Event> getTodaysEvents() throws Exception {
		List<Event> todaysEvents = new ArrayList<Event>();
		List<Event> events = getParkSpecialPermits();
		Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 0, 0, 0);
		for (Event evt : events) {
			if(evt.getDateTime().equals(calendar.getTimeInMillis())){
				todaysEvents.add(evt);
			}
		}
		return todaysEvents;
	}
		
	
	public List<Event> getMarchEvents2014() throws Exception {
		List<Event> march_evts = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();
		
		for (Event evt: evts) {
			if (evt.getMonth().equals("Mar-2014"))
				march_evts.add(evt);
		}
		return march_evts;
	}	
	
	public Event getFirstEventOfMonth(String month) throws Exception {
		List<Event> events = getParkSpecialPermits();
		Event ret = new Event("", "", "", "", "");
		boolean initial = true;
		for(Event event: events){
			if(event.getMonth().equals(month)){
				if(!initial){
					ret = event;
					initial = true;
					continue;
				}
				DateTime newDate = event.getDateTime();
				if(ret.getDateTime().isAfter(newDate)){
					ret = event;
				} //if
			} //if 
		} //for
		return ret;
	}

	public List<Event> getEventsForMonth(String date) throws Exception {
		List<Event> temp;
		temp = objectMapper.readValue(new URL(
				PARK_SPECIAL_PERMITS),
				eventListType
				);
		for(Iterator<Event> iter = temp.listIterator(); iter.hasNext();){
			Event a = iter.next();
			if (!a.getMonth().equals(date)){
				iter.remove();
			}
		}
		return temp;
	}
	

	public List<Event> getEventsLargerThan(int i)  throws Exception {
		// TODO Auto-generated method stub
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();
		
		for (Event evt : evts) {
			int tempAttendance = Integer.parseInt(evt.getAttendance());
			if (tempAttendance > i) {
				toDo.add(evt);
			}
		}
		return toDo;
	}
		
	public List<Event> getEventsInJune() throws Exception {
		List<Event> juneEvents = new ArrayList<Event>();
		List<Event> events = getParkSpecialPermits();
		
		for (Event event : events) {
			if (event.getMonth().contains("jun")) {
				juneEvents.add(event);
			}
		}
		
		return juneEvents;
	}
	
    public List<Event> getRiverfrontParkSpecialPermits() throws Exception {
        List<Event> evts = new ArrayList<Event>();

        for (Event evt : getParkSpecialPermits()) {
            if (evt.getLocation().toLowerCase().equals("riverfront park")) {
                evts.add(evt);
            }
        }
        return evts;
    }
	
	public List<Event> AttendanceGreaterThanFive() throws Exception {
		
		List<Event> toReturn = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		for (Event evt : evts) {
			
			if(Integer.parseInt(evt.getAttendance()) > 5){
				toReturn.add(evt);
			}
		}

		return toReturn;
	}

	public List<Event> getParkSpecialPermitsByAttendance() throws Exception {
		List<Event> evts = getParkSpecialPermits();

		Collections.sort(evts, new EventAttendanceComparator());
		
		return evts;
	}
	
	public List<Event> checkLocation(String location) throws Exception {
		List<Event> atDesiredLocation = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		for (Event evt : evts) {
			if (evt.getLocation().equals(location)) {
				atDesiredLocation.add(evt);
			}
		}

		return atDesiredLocation;
	}

	public List<Event> getAllEventsInMonth(String month) throws Exception {
		List<Event> toDo = new ArrayList<Event>();
		List<Event> evts = getParkSpecialPermits();

		DateTime now = DateTime.now();
		for (Event evt : evts) {
			if (evt.getMonth().equalsIgnoreCase(month) ) {
				toDo.add(evt);
			}
		}
		return toDo;
	}
}
