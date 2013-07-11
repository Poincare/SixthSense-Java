/**
 * 
 */
package sixthsense;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import classes.Category;
import classes.GeometricRecognizer;
import classes.Gesture;
import classes.PointR;

/**
 * @author Antoine Lavail
 * 
 */
public class GeometricRecognizerTest {

	/**
	 * Test method for
	 * {@link com.antoinelavail.wuv.sixthsense_java.classes.GeometricRecognizer#saveGesture(java.lang.String, java.util.List)}
	 * .
	 */
	@Test
	public void testSaveGesture() {
		GeometricRecognizer geo = new GeometricRecognizer();

		List<PointR> points = new ArrayList<PointR>();
		PointR p1 = new PointR(355, 236, 329031);
		PointR p2 = new PointR(354, 236, 329062);
		PointR p3 = new PointR(353, 236, 329062);
		points.add(p1);
		points.add(p2);
		points.add(p3);

		assertFalse(geo.saveGesture(null, points));
		assertFalse(geo.saveGesture(null, null));
		assertFalse(geo.saveGesture("test", null));

		if (geo.saveGesture("./src/resources/tests/testsave.xml", points)) {
			assertTrue(geo.loadGesture("/resources/tests/testsave.xml"));
		} else {
			fail("Error in saveGesture");
		}

		assertTrue(geo.saveGesture("./src/resources/tests/testsave.xml",
				points));
	}

	/**
	 * Test method for
	 * {@link com.antoinelavail.wuv.sixthsense_java.classes.GeometricRecognizer#loadGesture(java.lang.String)}
	 * .
	 */
	@Test
	public void testLoadGesture() {
		GeometricRecognizer geo = new GeometricRecognizer();
		assertEquals(0, geo.getNumGestures());
		// Load xmltest.xml
		String filename = "/resources/tests/xmltest.xml";

		List<PointR> points = new ArrayList<PointR>();
		PointR p1 = new PointR(355, 236, 329031);
		PointR p2 = new PointR(354, 236, 329062);
		PointR p3 = new PointR(353, 236, 329062);
		points.add(p1);
		points.add(p2);
		points.add(p3);
		Gesture p = new Gesture("xmltest", points);
		if (geo.loadGesture(filename)) {
			assertTrue(p.getPoints().get(0)
					.equals(geo.getGestures().get(0).getPoints().get(0)));
			assertTrue(p.getPoints().get(1)
					.equals(geo.getGestures().get(0).getPoints().get(1)));
			assertTrue(p.getPoints().get(2)
					.equals(geo.getGestures().get(0).getPoints().get(2)));
		} else {
			fail("Error in loadGesture");
		}
	}

	/**
	 * Test method for
	 * {@link com.antoinelavail.wuv.sixthsense_java.classes.GeometricRecognizer#assembleBatch(java.lang.String[])}
	 * .
	 */
	@Test
	public void testAssembleBatch() {
		GeometricRecognizer geo = new GeometricRecognizer();

		String[] filenames = { "/resources/gestures/clock1.xml", "/resources/gestures/clock2.xml",
				"/resources/gestures/draw1.xml", "/resources/gestures/draw2.xml",
				"/resources/gestures/menu1.xml", "/resources/gestures/menu2.xml",
				"/resources/gestures/photo1.xml", "/resources/gestures/photo2.xml",
				"/resources/gestures/weather1.xml", "/resources/gestures/weather2.xml", };

		assertEquals(5, geo.assembleBatch(filenames).size());

		assertEquals("draw", geo.assembleBatch(filenames).get(0).getName());
		assertEquals("photo", geo.assembleBatch(filenames).get(1).getName());
		assertEquals("clock", geo.assembleBatch(filenames).get(2).getName());
		assertEquals("menu", geo.assembleBatch(filenames).get(3).getName());
		assertEquals("weather", geo.assembleBatch(filenames).get(4).getName());

		String[] filenames2 = { "/resources/gestures/clock1.xml", "/resources/gestures/clock2.xml",
				"/resources/gestures/draw1.xml" };

		assertNull(geo.assembleBatch(filenames2));

		try {
			geo.assembleBatch(null);
		} catch (NullPointerException e) {
			// Pass
		}
		
		String[] filenames3 = { "nullxmlfile" };
		assertNull(geo.assembleBatch(filenames3));
	}

	/**
	 * Test method for
	 * {@link com.antoinelavail.wuv.sixthsense_java.classes.GeometricRecognizer#testBatch(int, java.lang.String, java.util.List, java.lang.String)}
	 * .
	 */
	@Test
	public void testTestBatch() {
		GeometricRecognizer geo = new GeometricRecognizer();
		
		String[] filenames = { "/resources/gestures/clock1.xml", "/resources/gestures/clock2.xml",
				"/resources/gestures/draw1.xml", "/resources/gestures/draw2.xml",
				"/resources/gestures/menu1.xml", "/resources/gestures/menu2.xml",
				"/resources/gestures/photo1.xml", "/resources/gestures/photo2.xml",
				"/resources/gestures/weather1.xml", "/resources/gestures/weather2.xml", };

		List<Category> categories = geo.assembleBatch(filenames);
		assertTrue(!categories.isEmpty());
		// Uncomment to verify the testBatch method (I've commented this one to avoid to much testBatch files...)
		assertTrue(geo.testBatch(1, "fast", categories, "./src/resources/tests/testBatch/"));
	}

	/**
	 * Test method for
	 * {@link com.antoinelavail.wuv.sixthsense_java.classes.GeometricRecognizer#createRotationGraph(java.lang.String, java.lang.String, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testCreateRotationGraph() {
		GeometricRecognizer geo = new GeometricRecognizer();

		String file1 = "/resources/gestures/clock1.xml";
		String file2 = "/resources/gestures/clock2.xml";
		String dir = "./src/resources/tests";
		assertTrue(geo.createRotationGraph(file1, file2, dir, true));
	}

}
