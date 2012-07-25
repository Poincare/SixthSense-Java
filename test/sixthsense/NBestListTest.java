/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

import static org.junit.Assert.fail;

import org.junit.Test;

public class NBestListTest {
	@Test
	public void testCtor() {
		NBestList n = new NBestList();
		try {
			if (n.isEmpty()) {
			}
		}
		catch (Exception e) {
			fail("constructor not working properly for NBList");
		}
	}
	
	@Test
	public void testIsEmpty() {
		NBestList n = new NBestList();
		if (!n.isEmpty())
			fail("IsEmpty not working in NBestList");
	}
	
	@Test
	public void testAdd() {
		NBestList n = new NBestList();
		try {
			n.addResult("r1", 2, 2, 2);
			if (n.isEmpty())
				fail("AddResult not working");
		}
		catch (Exception e) {
			fail("AddResult not working, due to other exceptions: "
					+ e.getMessage());
		}
	}
	
	@Test
	public void testName() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 2, 2, 2);
			if (!n.getName().equals("r1"))
				fail("Name() not working as expected!");
		}
		catch (Exception e) {
			fail("Name() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testScore() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (n.getScore() != 2)
				fail("Score() not working as expected!");
		}
		catch (Exception e) {
			fail("Score() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testDistance() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (n.getDistance() != 2)
				fail("Distance() not working as expected!");
		}
		catch (Exception e) {
			fail("Distance() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testAngle() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (n.getAngle() != 2)
				fail("Angle() not working as expected!");
		}
		catch (Exception e) {
			fail("Angle() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testNames() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (!(n.getNames()[0].equals("r1") && n.getNames()[1].equals("r2")))
				fail("Names() not working as expected!");
		}
		catch (Exception e) {
			fail("Names() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testNameString() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			String s = n.getNamesString();
			if (!(s.equals("r1,r2")))
				fail("NameString() not working as expected!");
		}
		catch (Exception e) {
			fail("NameString() not working, due to exceptions: "
					+ e.getMessage());
		}
	}
	
	@Test
	public void testScores() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 22, 22);
			double[] d = n.getScores();
			if (d == null)
				return;
			if (d[0] == 2 && d[1] == 22)
				fail("Scores() not working as expected!");
		}
		catch (Exception e) {
			fail("Scores() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testScoresString() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (!(n.getScoresString().equals("2,22")))
				fail("ScoresString() not working as expected!");
		}
		catch (Exception e) {
			fail("ScoresString() not working, due to exceptions: "
					+ e.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (!(n.get(0).getName().equals("r1")))
				fail("Get() not working as expected!");
		}
		catch (Exception e) {
			fail("Get() not working, due to exceptions: " + e.getMessage());
		}
	}
}
