package sixthsense;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointRTests {

	@Test
	public void test() {
		PointR pr = new PointR(1, 6, 9);
		if(!(pr.X == 1 && pr.Y == 6 && pr.T == 9)) {
			fail("PointR value constructor incorrect");
		}
	}
	
	@Test 
	public void testCopyConstructor() {
		PointR pr = new PointR(1, 6, 9);
		PointR pp = new PointR(pr);
		if(!(pr.X == pp.X && pr.Y == pp.Y && pr.T == pp.T)) {
			fail("PointR copy constructor incorrect");
		}
	}
	
	@Test
	public void testEqualsGenericObject() {
		Object o = new Object();
		PointR pr = new PointR(1, 6, 9);
		assertFalse(pr.equals(o));
		
		
	}
	
	@Test
	public void testEqualsWrongPoint() {
		PointR o = new PointR(2, 4, 8);
		PointR pr = new PointR(1, 6, 9);
		assertFalse(pr.equals(o));
	}
	
	@Test
	public void testEqualsRightPoint() {
		PointR o = new PointR(1, 6, 9);
		PointR pr = new PointR(1, 6, 9);
		
		assertTrue(pr.equals(o));
		assertTrue(o.equals(pr));
	}
	
	@Test
	public void testEqualsSameObject() {
		PointR o = new PointR(1, 6, 9);
		
		assertTrue(o.equals(o));
	}

}
