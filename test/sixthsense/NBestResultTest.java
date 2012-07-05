/**
 * Authors: Aroop Ganguly
 * */

package sixthsense;

import static org.junit.Assert.fail;

import org.junit.Test;

public class NBestResultTest {
	@Test
	public void testCtorForMemberInit() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (!r.get_name().equals("res1") || r.get_score() != 2.0
				|| r.get_distance() != 3.0 || r.get_angle() != 34.0) {
			fail("Contructor not working properly for NBestResult");
		}
	}
	
	@Test
	public void testIsEmpty() {
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		if (r.IsEmpty()) {
			fail("IsEmpty not working properly for NBestResult");
			return;
		}
		NBestResult r1 = new NBestResult("res1", 2.0, -1d, 34.0);
		if (!r1.IsEmpty())
			fail("IsEmpty not working properly for NBestResult");
	}
	
	@Test
	public void testCompareTo()
	{
		NBestResult r = new NBestResult("res1", 2.0, 3.0, 34.0);
		NBestResult r2 = new NBestResult("res1", 2.0, 3.0, 34.0);
		
		if(r.compareTo(r2)!=0) fail("CompareTo not working properly for nBestResult");
		
	}
}
