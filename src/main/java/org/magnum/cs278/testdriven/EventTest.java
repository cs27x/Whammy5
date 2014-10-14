package org.magnum.cs278.testdriven;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class EventTest {

	@Test
	public void testGetDateTime() throws Exception {
		Event e = new Event("name","location","attend","Jan-2014","10/2/14");
		String test_date = "2014-10-02T00:00:00.000-05:00";
		String test_date2 = e.getDateTime().toString();
		assertTrue(test_date.equals(test_date2));
	}
	
	@Test
	public void testToString() throws Exception {
		Event e = new Event("name","location","attend","mon-14","1/1/1");
		String test_string = "name / location / attend / mon-14 / 1/1/1";
		String test_string2 = e.toString();
		assertTrue(test_string.equals(test_string2));
	}
	
	@Test
	public void testGetters() throws Exception {
		Event e = new Event("name","location","attend","mon-14","1/1/1");
		assertTrue(e.getName()=="name");
		assertTrue(e.getLocation()=="location");
		assertTrue(e.getAttendance()=="attend");
		assertTrue(e.getMonth()=="mon-14");
		assertTrue(e.getDate()=="1/1/1");
	}
	
	@Test
	public void testAttendanceComparator() throws Exception {
		EventAttendanceComparator e = new EventAttendanceComparator();
		Event event1 = new Event("name","location","100","mon-14","1/1/1");
		Event event2 = new Event("name","location","100","mon-14","1/1/1");
		assertEquals(0,e.compare(event1, event2));
	}
	
}
