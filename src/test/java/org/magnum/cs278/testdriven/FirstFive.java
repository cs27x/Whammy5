package org.magnum.cs278.testdriven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

public class FirstFive {

	private App app = new App();
	
	// Test 1
	@Test
	public void testGetThreeThingsToDo() throws Exception {
		List<Event> whatToDo = app.getThreeThingsToDo();
		assertEquals(3, whatToDo.size());
		
		DateTime today = DateTime.now();
		
		for(Event thingToDo : whatToDo){
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			
			try{
				DateTime eventDate = Event.DATE_TIME_FORMAT.parseDateTime(thingToDo.getDate());
				assertTrue(eventDate.isAfter(today));
			}catch(IllegalArgumentException arg){
				//The data in data.nashville.gov is..unfortunately...not
				//perfectly clean and we have to ignore the garbage...
			}
		}
	}
	
	// Test 2
	@Test
	public void testGetParkSpecialPermits() throws Exception {
		List<Event> events = app.getParkSpecialPermits();
		assertTrue(events.size() > 0);
		for(Event event : events){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
		}
	}
	
	// Test 3
	@Test
	public void testGetEventsWithLocation() throws Exception {
		List<Event> sanFranEvents = app.getEventsWithLocation("San Francisco");
				
		for(Event event : sanFranEvents){
			assertNotNull(event);
			assertNotNull(event.getLocation());
			assertNotNull(event.getName());
			assertNotNull(event.getAttendance());
			assertNotNull(event.getDate());
			assertEquals(event.getLocation(), "San Francisco");
		}
	}
	
	// Test 4
	@Test
	public void testTodaysEvents() throws Exception {
		List<Event> whatToDo = app.getTodaysEvents();
		
		for(Event thingToDo : whatToDo){
			assertNotNull(thingToDo);
			assertNotNull(thingToDo.getDate());
			
			try{
				DateTime eventDate = Event.DATE_TIME_FORMAT.parseDateTime(thingToDo.getDate());
				assertTrue(eventDate.isEqualNow());
			}catch(IllegalArgumentException arg){
			}
		}
	}
	
	// Test 5
	@Test
	public void testEventsInMarch2014() throws Exception {
		List<Event> pubs = app.getEventsForMonth("Mar-2014");
		assertTrue(pubs.size() > 0); // At least one March-2014 event.
		for(Event temp : pubs) {
			assertNotNull(temp);
			assertNotNull(temp.getLocation());
			assertNotNull(temp.getName());
			assertNotNull(temp.getAttendance());
			assertNotNull(temp.getDate());
			assertEquals(temp.getMonth(),"Mar-2014");
		}
	}
	
	// Test 6
	@Test
	public void testGetFirstEventOfMonth() throws Exception {
		String month = "Feb-2014";
		String testEventName = "Cupid's Chase";
		
		Event first = app.getFirstEventOfMonth(month);
		
		assertTrue(first.getName().equals(testEventName));
	}
	
	// Test 7
	@Test
	public void testGetEventsForMonth() throws Exception {
		
		List<Event> events = app.getEventsForMonth("Jan-2014");
		for(Event temp : events) {
			assertNotNull(temp);
			assertNotNull(temp.getLocation());
			assertNotNull(temp.getName());
			assertNotNull(temp.getAttendance());
			assertNotNull(temp.getDate());
			assertEquals(temp.getMonth(),"Jan-2014");
		}
		
	}
}

