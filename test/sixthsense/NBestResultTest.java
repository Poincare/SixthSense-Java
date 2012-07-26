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
}
