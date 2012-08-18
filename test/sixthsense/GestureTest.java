package sixthsense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GestureTest {
	@Test
	public void testRawPoints() {
		Gesture g = new Gesture();
		ArrayList<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		
		g.setRawPoints(a);
		
		assertEquals(a, g.getRawPoints());
		
	}

	@Test
	public void testCtorForNameMemberInit() {
		Gesture g = new Gesture();
		if (g.getName() != "") {
			fail("Gesture not initialized properly");
		}
	}
	
	@Test
	public void testCtorForNameAndRawPointsInit() {
		Gesture g = new Gesture("new_gesture", new ArrayList<PointR>());
		if (!g.getName().equals("new_gesture") || g.getRawPoints() == null) {
			fail("Gesture not initialized properly");
		}
		// to be further implemented once GeometricRecognizer is implemented
	}
	
	@Test
	public void testDuration() {
		// to be implemented once GeometricRecognizer is implemented
	}
	
	@Test
	public void testCompareTo() {
		Gesture g1 = new Gesture("gest1", new ArrayList<PointR>());
		Gesture g2 = new Gesture("gest2", new ArrayList<PointR>());
		try {
			if (g1.compareTo(g2) == 0)
				fail("Gesture compareTo not working properly");
		}
		catch (Exception e) {
			fail("Gesture compareTo not working properly");
		}
	}
	
	@Test
	public void testParseName() {
		if (!Gesture.ParseName("C:\\a\\b.txt").equals("b")) {
			fail("parse name not working properly");
		}
	}
}
