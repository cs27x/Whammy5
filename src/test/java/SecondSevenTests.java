package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.magnum.cs278.testdriven.*;
import org.junit.Test;

public class SecondSevenTests {

	private App a = new App();
	private List<Event> list;
	
	
	@Test
	public void testGetEventsLargerThan() throws Exception {
		list = a.getEventsLargerThan(50);
		assertTrue(!list.equals(null));
		int att = 0, maxAtt = 0;
		for(Event e: list){
			att = Integer.parseInt(e.getAttendance());
			if(att > maxAtt)
				maxAtt = att;
			assertTrue(att > 50);
		}
		list = a.getEventsLargerThan(maxAtt);
		assertTrue(list.size() == 0);
		List<Event> allEvts = a.getParkSpecialPermits();
		list = a.getEventsLargerThan(-1);
		assertEquals(list.size(), allEvts.size());
		list = a.getEventsLargerThan(0);
		assertEquals(list.size(), allEvts.size());
	}

	@Test
	public void testGetEventsInJune() throws Exception{
		list = a.getEventsInJune();
		assertNotEquals(list, null);
		for(Event e: list){
			assertTrue(e.getMonth().toLowerCase().contains("jun"));
		}
		assertEquals(list, a.getEventsInJune());
		assertEquals(list, a.getAllEventsInMonth("jun"));
		
	}
	
	@Test
	public void testGetRiverfrontParkSpecialPermits() throws Exception{
		list = a.getRiverfrontParkSpecialPermits();
		assertNotEquals(list, null);
		
		for(Event e: list){
			assertTrue(e.getLocation().equalsIgnoreCase("Riverfront Park"));
		}
		
		for(Event e: a.getRiverfrontParkSpecialPermits()){
			assertTrue(e.getLocation().equalsIgnoreCase("Riverfront Park"));
		}
		//assertEquals(list, a.getEventsWithLocation("Riverfront Park"));
		//assertEquals(list, a.getRiverfrontParkSpecialPermits());
	}
	
	@Test
	public void testGetAttendanceGreaterThan5() throws Exception {
		list = a.AttendanceGreaterThanFive();
		assertNotEquals(list, null);
		for(Event e: list){
			assertTrue(Integer.parseInt(e.getAttendance()) > 5);
		}
		
		List<Event> list2 = a.AttendanceGreaterThanFive();
		//for(Event e)
		//assertEquals(list, a.getEventsLargerThan(5));
	}
}
