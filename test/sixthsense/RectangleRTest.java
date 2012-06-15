package sixthsense;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleRTest {
	RectangleR rr;
	
	public RectangleRTest() {
		rr = new RectangleR(2, 3, 4, 5);
	}

	@Test
	public void testValueConstructor() {
		assertTrue((rr.getX() == 2 && rr.getY() == 3 && rr.getWidth() == 4 && rr.getHeight() == 5));
		
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

}

