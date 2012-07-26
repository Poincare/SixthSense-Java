/**
 * Authors: Dhaivat Pandya
 */
package sixthsense;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RectangleRTest {
	RectangleR rr;
	
	public RectangleRTest() {
		rr = new RectangleR(2, 3, 4, 5);
	}
	
	@Test
	public void testValueConstructor() {
		assertTrue((rr.getX() == 2 && rr.getY() == 3 && rr.getWidth() == 4 && rr
				.getHeight() == 5));
	}
	
	@Test
	public void testTopLeft() {
		PointR tl = rr.getTopleft();
		assertTrue(tl.X == 2 && tl.Y == 3);
	}
	
	@Test
	public void testBottomRight() {
		PointR tl = rr.getBottomRight();
		PointR tt = new PointR(6.0, 8.0);
		assertTrue(tt.equals(tl));
	}
	
	@Test
	public void testCenterPoint() {
		PointR tl = rr.getCenter();
		PointR cc = new PointR(4.0, 5.5);
		assertTrue(tl.equals(cc));
	}
	
	@Test
	public void testMaxSide() {
		double length = rr.getMaxSide();
		assertTrue(length == 5);
	}
	
	@Test
	public void testMinSide() {
		double length = rr.getMinSide();
		assertTrue(length == 4);
	}
	
	@Test
	public void testDiagonal() {
		double length = rr.getDiagonal();
		assertTrue(length == Math.sqrt(41));
	}
	
	@Test
	public void testEquals() {
		RectangleR rr2 = new RectangleR(2, 3, 4, 5);
		assertTrue(rr2.equals(rr) && rr.equals(rr2));
	}
	
	@Test
	public void testUnequalHeight() {
		RectangleR rr2 = new RectangleR(2, 3, 4, 6);
		assertFalse(rr2.equals(rr));
	}
	
	@Test
	public void testUnequalWidth() {
		RectangleR rr2 = new RectangleR(2, 3, 5, 5);
		assertFalse(rr2.equals(rr));
	}
	
	@Test
	public void testUnequalX() {
		RectangleR rr2 = new RectangleR(3, 3, 4, 5);
		assertFalse(rr2.equals(rr));
	}
	
	@Test
	public void testUnequalY() {
		RectangleR rr2 = new RectangleR(2, 4, 4, 5);
		assertFalse(rr2.equals(rr));
	}
	
	@Test
	public void testFindBox() {
		List<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		a.add(new PointR(5, 8, 0));
		a.add(new PointR(9, 2, 0));
		RectangleR box = RectangleR.findBox(a);
		RectangleR rr2 = new RectangleR(1, 1, 8, 7);
		assertTrue(box.equals(rr2));
	}
}
