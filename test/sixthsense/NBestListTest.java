/**
 * Authors: Aroop Ganguly, Dhaivat Pandya
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
				fail("getName() not working as expected!");
		}
		catch (Exception e) {
			fail("getName() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testNameSizeZero() {
		try {
			NBestList n = new NBestList();
			if (!n.getName().equals(""))
				fail("getName() not working as expected!");
		}
		catch (Exception e) {
			fail("getName() not working, due to exceptions: " + e.getMessage());
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
	public void testScoreSizeZero() {
		try {
			NBestList n = new NBestList();
			if (n.getScore() != -1.0)
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
				fail("getDistance() not working as expected!");
		}
		catch (Exception e) {
			fail("getDistance() not working, due to exceptions: "
					+ e.getMessage());
		}
	}
	
	@Test
	public void testDistanceSizeZero() {
		try {
			NBestList n = new NBestList();
			if (n.getDistance() != 0)
				fail("getDistance() not working as expected!");
		}
		catch (Exception e) {
			fail("NBestList threw an unexpected exception");
		}
	}
	
	@Test
	public void testAngle() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (n.getAngle() != 2)
				fail("getAngle not working as expected!");
		}
		catch (Exception e) {
			fail("getAngle not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testAngleSizeZero() {
		try {
			NBestList n = new NBestList();
			if (n.getAngle() != 0.0)
				fail("getAngle not working as expected!");
		}
		catch (Exception e) {
			fail("getAngle not working, due to exceptions: " + e.getMessage());
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
	public void testNamesSizeZero() {
		try {
			NBestList n = new NBestList();
			
			if (!(n.getNames() == null))
				fail("Names() not working as expected! Value of s: " + n.getNames());
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
	public void testNameStringZeroSize() {
		try {
			NBestList n = new NBestList();
			String s = n.getNamesString();
			if (!(s.equals("")))
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
			if (d[0] != 2 && d[1] != 22)
				fail("Scores() not working as expected!");
		}
		catch (Exception e) {
			fail("Scores() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testScoresSizeZero() {
		try {
			NBestList n = new NBestList();
			double[] d = n.getScores();
			if (d[0] != 0.0 && d[1] != 0.0)
				fail("Scores() not working as expected!");
		}
		catch (Exception e) {
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
	public void testScoresStringSizeZero() {
		try {
			NBestList n = new NBestList();
			if (!(n.getScoresString().equals("")))
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
				fail("get() not working as expected!");
		}
		catch (Exception e) {
			fail("get() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetLessThanZero() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (!(n.get(-1) == null))
				fail("get() not working as expected!");
		}
		catch (Exception e) {
			fail("get() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testGetGreaterThanSize() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			if (!(n.get(2) == null))
				fail("get() not working as expected!");
		}
		catch (Exception e) {
			fail("get() not working, due to exceptions: " + e.getMessage());
		}
	}
	
	@Test
	public void testSortString() {
		try {
			NBestList n = new NBestList();
			n.addResult("r1", 2, 2, 2);
			n.addResult("r2", 22, 222, 23);
			n.sortDescending();
			
			if (!(n.get(0).getName().equals("r2"))
					&& !(n.get(1).getName().equals("r1")))
				fail("get() not working as expected!");
		}
		catch (Exception e) {
			fail("get() not working, due to exceptions: " + e.getMessage());
		}
	}
}
