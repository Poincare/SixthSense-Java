package sixthsense;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class GestureTest {
	@Test
	public void testCtorForNameMemberInit() {
		Gesture g = new Gesture();
		if (g.Name != "") {
			fail("Gesture not initialized properly");
		}
	}
	
	@Test
	public void testCtorForNameAndRawPointsInit() {
		Gesture g = new Gesture("new_gesture", new ArrayList<Object>());
		if (g.Name != "new_gestute" || g.RawPoints != null) {
			fail("Gesture not initialized properly");
		}
		//to be further implemented once GeometricRecognizer is implemented
	}
	
	@Test
	public void testDuration() {
	//to be implemented once GeometricRecognizer is implemented
	}
	
	@Test
	public void testCompareTo() {
		Gesture g1 = new Gesture("gest1", new ArrayList<Object>());
		Gesture g2 = new Gesture("gest2", new ArrayList<Object>());
		try {
			if (g1.CompareTo(g2) == 0)
				fail("Gesture compareTo not working properly");
		}
		catch (Exception e) {
			fail("Gesture compareTo not working properly");
		}
	}
	
	@Test
	public void testParseName() {
		if (!Gesture.ParseName("C:\\A\b.txt").equals("b")) {
			fail("parse name not working properly");
		}
	}
}
