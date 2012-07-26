/**
 * Authors: Dhaivat Pandya
 */
package sixthsense;

import static org.junit.Assert.*;

import org.junit.Test;

public class SizeRTest {
	SizeR sr;
	
	public SizeRTest() {
		sr = new SizeR(1, 2);
	}
	
	@Test
	public void testValueConstructor() {
		assertTrue(sr.getCX() == 1 & sr.getCY() == 2);
	}
	
	@Test
	public void testCopyConstructor() {
		SizeR sr2 = new SizeR(sr);
		assertTrue(sr2.getCX() == sr.getCX() && sr2.getCY() == sr2.getCY());
	}
	
	@Test
	public void testGetWidthHeight() {
		assertTrue(sr.getWidth() == sr.getCX() && sr.getHeight() == sr.getCY());
	}
	
	@Test
	public void testEquals() {
		SizeR sr2 = new SizeR(1, 2);
		assertTrue(sr2.equals(sr) && sr.equals(sr2));
	}
	
	@Test
	public void testUnequal() {
		SizeR sr2 = new SizeR(2, 4);
		assertFalse(sr2.equals(sr));
	}
}
