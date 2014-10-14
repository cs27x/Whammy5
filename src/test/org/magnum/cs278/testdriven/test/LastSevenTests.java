package org.magnum.cs278.testdriven.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.magnum.cs278.testdriven.*;

public class LastSevenTests {
	
	private App app = new App();
	
	@Test
	public void testGetEventsInMonth() throws Exception {
		try{
			app.getAllEventsInMonth(null);
			app.getAllEventsInMonth("");
			app.getAllEventsInMonth("     ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(app.getAllEventsInMonth("chickenfingers"), 0);
		assertEquals(app.getAllEventsInMonth("Jan-2014"), 1);
		assertEquals(app.getAllEventsInMonth("Feb-2014"), 3);
	}
	
	@Test
	public void testCheckLocation() throws Exception {
		try {
			app.checkLocation(null);
			app.checkLocation("");
			app.checkLocation("   ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(app.checkLocation("chickenFried"), 0);
		assertEquals(app.checkLocation("Church Street Park"), 32);
		assertEquals(app.checkLocation("  Church Street Park"), 32);
		
	}
	
	@Test
	public void testGetSpecialParkPermitsByAttendance() throws Exception{
		assertNotNull(app.getParkSpecialPermitsByAttendance());
	}

}
