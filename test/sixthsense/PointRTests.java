/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package sixthsense;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.Console;
import java.util.*;

public class PointRTests {
	@Test
	public void testPointConstruction() {
		PointR pr = new PointR(1, 6, 9);		
		assertTrue(pr.X == 1);
		assertTrue(pr.Y == 6);
		assertTrue(pr.T == 9);
	}
	
	@Test
	public void testCopyConstructor() {
		PointR pr = new PointR(1, 6, 9);
		PointR pp = new PointR(pr);
		assertTrue(pr.X == pp.X);
		assertTrue(pr.Y == pp.Y);
		assertTrue(pr.T == pp.T);
	}
	
	@Test
	public void testZeroTConstructor() {
		PointR pr = new PointR(1, 1);
		PointR pr2 = new PointR(1, 1, 0);
		
		assertEquals(pr, pr2);
	}
	
	@Test
	public void testEqualsGenericObject() {
		Object o = new Object();
		PointR pr = new PointR(1, 6, 9);
		assertFalse(pr.equals(o));
	}
	
	@Test
	public void testEqualsWrongPoint() {
		PointR o = new PointR(2, 4, 8);
		PointR pr = new PointR(1, 6, 9);
		assertFalse(pr.equals(o));
	}
	
	@Test
	public void testEqualsRightPoint() {
		PointR o = new PointR(1, 6, 9);
		PointR pr = new PointR(1, 6, 9);
		assertTrue(pr.equals(o));
		assertTrue(o.equals(pr));
	}
	
	@Test
	public void testEqualsSameObject() {
		PointR o = new PointR(1, 6, 9);
		assertTrue(o.equals(o));
	}
	
	@Test
	public void testDistance() {
		PointR a = new PointR(1, 1, 0);
		PointR b = new PointR(4, 5, 0);
		assertTrue(((float) PointR.getDistance(a, b)) == 5.0f);
	}
	
	@Test
	public void testInstanceDistance() {
		PointR a = new PointR(1, 1, 0);
		PointR b = new PointR(4, 5, 0);
		assertTrue(a.getDistance(b) == PointR.getDistance(a, b));
	}
	@Test
	public void testCentroid() {
		List<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		a.add(new PointR(2, 2, 0));
		a.add(new PointR(3, 9, 0));
		PointR centroid = PointR.getCentroid(a);
		assertTrue(centroid.equals(new PointR(2, 4)));
	}
	
	@Test
	public void testPathLength() {
		List<PointR> a = new ArrayList<PointR>();
		a.add(new PointR(1, 1, 0));
		a.add(new PointR(4, 5, 0));
		a.add(new PointR(7, 9, 0));
		double length = PointR.getPathLength(a);
		assertTrue(length == 10.0);
	}
	
	@Test
	public void testFindBox() {
		ArrayList<PointR> points = new ArrayList<PointR>();
		PointR p1 = new PointR(0, 0);
		PointR p2 = new PointR(4, 0);
		PointR p3 = new PointR(0, 2);
		PointR p4 = new PointR(4, 2);
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		
		assertTrue(PointR.findBox(points).getMaxSide() == 4);
	}
	
	@Test
	public void testAngleinRadians() {
//		ArrayList<PointR> points = new ArrayList<PointR>();
		PointR p1 = new PointR(0, 0);
		PointR p2 = new PointR(4, 2);
//		points.add(p1);
//		points.add(p2);
		
		assertTrue(PointR.getAngleInRadians(p1, p2, true) == Math.atan2(2, 4));
		
		if (PointR.getAngleInRadians(p1, p2, true) != Math.atan2(2, 4))
			fail("PointR pathlength calculation not working properly");
	}
	
	@Test
	public void testAngleInRadians3OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(5, 0);
		boolean positiveOnly = true;
		double angleInRadians = PointR.getAngleInRadians(start, end, positiveOnly);
		
		// Multiple of Zero for consistency with other tests
		// and visualization of the situation
		assertTrue(angleInRadians == 0 * Math.PI / 2);
	}

	@Test
	public void testAngleInRadians12OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(0, 5);
		boolean positiveOnly = true;
		double angleInRadians = PointR.getAngleInRadians(start, end, positiveOnly);
		
		assertTrue(angleInRadians == 1 * Math.PI / 2);
	}

	@Test
	public void testAngleInRadians9OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(-5, 0);	
		boolean positiveOnly = true;
		double angleInRadians = PointR.getAngleInRadians(start, end, positiveOnly);

		assertTrue(angleInRadians == 2 * Math.PI / 2);
	}

	@Test
	public void testAngleInRadians6OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(0, -5);
		boolean positiveOnly = true;
		double angleInRadians = PointR.getAngleInRadians(start, end, positiveOnly);

		assertTrue(angleInRadians == 3 * Math.PI / 2);
	}
	/*********************************************************************/
	@Test
	public void testAngleInDegrees3OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(5, 0);
		boolean positiveOnly = true;
		double angleInDegrees = PointR.getAngleInDegrees(start, end, positiveOnly);
		
		assertTrue(angleInDegrees == 0);
	}

	@Test
	public void testAngleInDegrees12OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(0, 5);
		boolean positiveOnly = true;
		double angleInDegrees = PointR.getAngleInDegrees(start, end, positiveOnly);
		
		assertTrue(angleInDegrees == 90);
	}

	@Test
	public void testAngleInDegrees9OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(-5, 0);	
		boolean positiveOnly = true;
		double angleInDegrees = PointR.getAngleInDegrees(start, end, positiveOnly);
		
		assertTrue(angleInDegrees == 180);
	}

	@Test
	public void testAngleInDegrees6OClock(){
		PointR start = new PointR(0, 0);
		PointR end = new PointR(0, -5);
		boolean positiveOnly = true;
		double angleInDegrees = PointR.getAngleInDegrees(start, end, positiveOnly);
		
		assertTrue(angleInDegrees == 270);
		
		
	}
	
	@Test
	public void testDeg2Rad(){
		
		// These are inexact calculations. How close do we need to be?
		double marginOfError = 0.0000000000001;

		// 0 degrees	=	0 Radians
		assertTrue(PointR.deg2Rad(0) == 0);
		
		// [degree#]		[segment#]		[totalSegments] 
		// 90 degrees 	=	1 PI		/	2 Radians		
		assertTrue(PointR.deg2Rad(90) - (Math.PI / (double)2) < marginOfError);
		// 180 degrees	= 	2 PI 		/ 	2 Radians
		assertTrue(PointR.deg2Rad(180) - Math.PI < marginOfError);
		// 270 degrees	=	3 PI		/	2 Radians
		assertTrue(PointR.deg2Rad(270) - ((double)3 * Math.PI / (double)2) < marginOfError);
		
		// At 2 divisions per half-circle, we divide the segment number by 2 Radians
		// At 6 divisions per half-circle, we divide the segment number by 6 Radians

		// [degree#]		[segment#]		[totalSegments]
		// 30 degrees	=	1 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(30) == Math.PI / (double)6);
		// 60 degrees	=	2 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(60) - ((double)2 * Math.PI / (double)6) < marginOfError);
		// 120 degrees	=	4 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(120) - ((double)4 * Math.PI / (double)6) < marginOfError);
		// 150 degrees	=	5 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(150) - ((double)5 * Math.PI / (double)6) < marginOfError);
		// 210 degrees	=	7 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(210) - ((double)7 * Math.PI / (double)6) < marginOfError);		
		// 240 degrees	=	8 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(240) - ((double)8 * Math.PI / (double)6) < marginOfError);
		// 300 degrees	=	10 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(300) - ((double)10 * Math.PI / (double)6) < marginOfError);
		// 330 degrees	=	11 PI		/	6 Radians
		assertTrue(PointR.deg2Rad(330) - ((double)11 * Math.PI / (double)6) < marginOfError);
						
		// Determine the limit of the accuracy
		for( double i = 1; i <= 33; i++)
		{	
			// Margin of Error decreases from e^-1 to e^-33
			// If it decreases further, the algorithm is inaccurate
			marginOfError = Math.exp(-i);
			
			// number degrees between tested points on the circle
			double degreeIncrement = 30;
			
			// number of segments in the circle 
			// based on the distance between points 
			double totalSegments = 180 / degreeIncrement;						

			for(double segmentNumber = 0; segmentNumber < 20; segmentNumber ++)
			{
				double degrees = segmentNumber * degreeIncrement;
				double computedRadians = PointR.deg2Rad(degrees);
				double expectedRadians = segmentNumber / totalSegments * Math.PI;
				double difference = computedRadians - expectedRadians;
				
//				System.out.println("Degrees: " + degrees);
//				System.out.println("Computed Radians: " + computedRadians);
//				System.out.println("Expected Radians: " + expectedRadians);
//				System.out.println("Margin of Error: " + marginOfError);
//				System.out.println("Difference: " + difference);
				
				if(difference >= marginOfError){
					System.out.println();
					fail("Accurate to within e^-" + i);
				}
			}
		}
	}
	
}
