/**
 * Authors: Aroop Ganguly
 * */

package sixthsense;

import static org.junit.Assert.fail;

import org.junit.Test;

public class NBestListTest {
	@Test
	public void testCtor() {
		NBestList n = new NBestList();
		try {
			if (n.IsEmpty()) {
			}
		}
		catch (Exception e) {
			fail("constructor not working properly for NBList");
		}
	}
	
	@Test
	public void testIsEmpty() {
		NBestList n = new NBestList();
		if (!n.IsEmpty())
			fail("IsEmpty not working in NBestList");
	}
	
	@Test
	public void testAdd() {
		NBestList n = new NBestList();
		try {
			n.AddResult("r1", 2, 2, 2);
			if (n.IsEmpty())
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 2, 2, 2);
			if (!n.Name().equals("r1"))
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (n.Score() != 2)
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (n.Distance() != 2)
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (n.Angle() != 2)
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (!(n.Names()[0].equals("r1") && n.Names()[1].equals("r2")))
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (!(n.NamesString().equals("r1,r2")))
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (!(n.Scores()[0] == 2 && n.Scores()[1] == 22))
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (!(n.ScoresString().equals("2,22")))
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
			n.AddResult("r1", 2, 2, 2);
			n.AddResult("r2", 22, 222, 23);
			if (!(n.Get(0).get_name().equals("r1")))
				fail("Get() not working as expected!");
		}
		catch (Exception e) {
			fail("Get() not working, due to exceptions: " + e.getMessage());
		}
	}
}
