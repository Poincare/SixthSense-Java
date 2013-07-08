/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package sixthsense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import classes.PointR;

public class PointRTest {
	@Test
	public void test() {
		PointR pr = new PointR(1, 6, 9);
		if (!(pr.getX() == 1 && pr.getY() == 6 && pr.getT() == 9)) {
			fail("PointR value constructor incorrect");
		}
	}

	@Test
	public void testCopyConstructor() {
		PointR pr = new PointR(1, 6, 9);
		PointR pp = new PointR(pr);
		if (!(pr.getX() == pp.getX() && pr.getY() == pp.getY() && pr.getT() == pp
				.getT())) {
			fail("PointR copy constructor incorrect");
		}
	}

	@Test
	public void testZeroTConstructor() {
		PointR pr = new PointR(1, 1);
		PointR pr2 = new PointR(1, 1, 0);

		assertEquals(pr, pr2);
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

	@Test
	public void testDistance() {
		PointR a = new PointR(1, 1, 0);
		PointR b = new PointR(4, 5, 0);
		assertTrue(((float) PointR.getDistance(a, b)) == 5.0f);
	}

	@Test
	public void testInstanceDistance() {
		PointR a = new PointR(1, 1, 0);
		PointR b = new PointR(4, 5, 0);
		assertTrue(a.getDistance(b) == PointR.getDistance(a, b));
	}

	@Test
	public void testCentroid() {
		List<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		a.add(new PointR(2, 2, 0));
		a.add(new PointR(3, 9, 0));
		PointR centroid = PointR.getCentroid(a);
		assertTrue(centroid.equals(new PointR(2, 4)));
	}

	@Test
	public void testPathLength() {
		List<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		a.add(new PointR(4, 5, 0));
		a.add(new PointR(7, 9, 0));
		double length = PointR.getPathLength(a);
		assertTrue(length == 10.0);
	}

	@Test
	public void testFindBox() {
		ArrayList<PointR> points = new ArrayList<PointR>();
		PointR p1 = new PointR(0, 0);
		PointR p2 = new PointR(4, 0);
		PointR p3 = new PointR(0, 2);
		PointR p4 = new PointR(4, 2);
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		if (PointR.findBox(points).getMaxSide() != 4)
			fail("PointR.FindBox not working properly");
	}

	@Test
	public void testAngleinRadians() {
		PointR p1 = new PointR(0, 0);
		PointR p2 = new PointR(4, 2);
		if (PointR.getAngleInRadians(p1, p2, true) != Math.atan2(2, 4))
			fail("PointR pathlength calculation not working properly");
		if (PointR.getAngleInRadians(p1, p2, false) != Math.atan2(2, 4))
			fail("PointR pathlength calculation not working properly");
		PointR p3 = new PointR(4, 0);
		// -90 degrees is straight up
		assertEquals(-Math.PI / 2.0, PointR.getAngleInRadians(p2, p3, false),
				0.001);
		// With positiveOnly
		assertEquals((-Math.PI / 2.0) + (Math.PI * 2.0),
				PointR.getAngleInRadians(p2, p3, true), 0.001);
		PointR p4 = new PointR(4, 6);
		// 90 degrees is straight down
		assertEquals(Math.PI / 2.0, PointR.getAngleInRadians(p2, p4, true),
				0.001);
		PointR p5 = p4;
		// Identical points
		assertEquals(0.0, PointR.getAngleInRadians(p4, p5, true), 0.001);
	}

	@Test
	public void testRotateByRadians() {
		
		// Size equals to 0
		List<PointR> points = new ArrayList<PointR>();
		List<PointR> result = PointR.rotateByRadians(points, 0);
		assertEquals(0, result.size());
		
		PointR p1a = new PointR(2, 2);
		PointR p2a = new PointR(4, 6);
		//c : 3, 4
		points.add(p1a);
		points.add(p2a);
		// 30 degrees
		double radians = Math.PI / 6;
		List<PointR> newPoints = PointR.rotateByRadians(points, radians);
		PointR p1b = new PointR(3.133974596216, 1.767949192431);
		PointR p2b = new PointR(2.866025403784, 6.232050807569);
		assertEquals(newPoints.get(0).getX(), p1b.getX(), 0.000001);
		assertEquals(newPoints.get(1).getX(), p2b.getX(), 0.0000001);
		assertEquals(newPoints.get(0).getY(), p1b.getY(), 0.0000001);
		assertEquals(newPoints.get(1).getY(), p2b.getY(), 0.0000001);
	}
}
