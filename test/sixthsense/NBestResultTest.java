/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

import static org.junit.Assert.fail;

import org.junit.Test;

public class NBestResultTest {
	@Test
	public void testCtorForMemberInit() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (!r.getName().equals("res1") || r.getScore() != 2.0
				|| r.getDistance() != 3.0 || r.getAngle() != 34.0) {
			fail("Contructor not working properly for NBestResult");
		}
	}
	
	@Test
	public void testIsEmpty() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (r.isEmpty()) {
			fail("IsEmpty not working properly for NBestResult");
			return;
		}
		NBestResult r1 = new NBestResult("res1", -1d, 2.0, 34.0);
		if (!r1.isEmpty())
			fail("IsEmpty not working properly for NBestResult");
	}
	
	@Test
	public void testCompareTo() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		NBestResult r2 = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (r.compareTo(r2) != 0)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToNameDiff() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		NBestResult r2 = new NBestResult("res2", 2.0, 3.0, 34.0);
		if (r.compareTo(r2) != -1)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToDistanceDiff() {
		NBestResult r = new NBestResult("res1", 2.0, 4.0, 34.0);
		NBestResult r2 = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (r.compareTo(r2) != -1)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToAngleDiff() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 31.0);
		NBestResult r2 = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (r.compareTo(r2) != -1)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToScoreLess() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		NBestResult r2 = new NBestResult("res1", 1.0, 3.0, 34.0);
		if (r.compareTo(r2) != -1)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToScoreMORE() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		NBestResult r2 = new NBestResult("res1", 3.0, 3.0, 34.0);
		if (r.compareTo(r2) != 1)
			fail("CompareTo not working properly for nBestResult");
	}
	
	@Test
	public void testCompareToNonResult() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		Integer a = 0;
		if (r.compareTo(a) == 0)
			fail("CompareTo not working properly for nBestResult");
	}
}
