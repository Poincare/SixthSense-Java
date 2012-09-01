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
		
		PointR pr1 = new PointR(1,6,9);
		assertFalse(o.equals(pr1));
		
		PointR pr2 = new PointR(2,4,10);
		assertFalse(o.equals(pr2));
		
		PointR pr3 = new PointR(2,3,8);
		assertFalse(o.equals(pr3));
		
		PointR pr4 = new PointR(2,4,6);
		assertFalse(o.equals(pr4));		
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
		assertTrue(a.getDistance(b) == 5.0f);
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
	public void testTranslateCentroid() {
		ArrayList<PointR> originalList = new ArrayList<PointR>();
		originalList.add(new PointR(1, 1, 0));
		originalList.add(new PointR(2, 2, 0));
		originalList.add(new PointR(3, 9, 0));
		
		PointR newCentroid = new PointR(7, 9);
		
		PointR.translateCentroidTo(originalList, newCentroid);
		
		assertTrue(PointR.getCentroid(originalList).equals(newCentroid));
		
		ArrayList<PointR> newList = new ArrayList<PointR>();
		newList.add(new PointR(6, 6, 0));
		newList.add(new PointR(7, 7, 0));
		newList.add(new PointR(8, 14, 0));
		
		assertTrue(areEqual(originalList,newList));
	}
	
	@Test
	public void testTranslateBBox() {
		// REMINDER: (0,0) is the TOP LEFT corner of a monitor!
		//         -2
		// 
		//         -1
		//
		// -2  -1   0   1   2
		//
		//          1
		//
		//          2
		ArrayList<PointR> originalSquare = new ArrayList<PointR>();
		originalSquare.add(new PointR(3, 3, 0));
		originalSquare.add(new PointR(-3, 3, 0));
		originalSquare.add(new PointR(-3, -3, 0));
		originalSquare.add(new PointR(3, -3, 0));
		
		PointR toPt = new PointR(-6,-6);
		
		PointR.translateBBoxTo(originalSquare, toPt);
		
		ArrayList<PointR> translatedSquare = new ArrayList<PointR>();
		translatedSquare.add(new PointR(0, 0, 0));
		translatedSquare.add(new PointR(-6, 0, 0));
		translatedSquare.add(new PointR(-6, -6, 0));
		translatedSquare.add(new PointR(0, -6, 0));
		
		assertTrue(areEqual(originalSquare,translatedSquare));
		
		////////////////////////////////
		
		ArrayList<PointR> originalDiamond = new ArrayList<PointR>();
		originalDiamond.add(new PointR(3, 0, 0));
		originalDiamond.add(new PointR(0,-3, 0));
		originalDiamond.add(new PointR(-3, 0, 0));
		originalDiamond.add(new PointR(0, 3, 0));
		
		toPt = new PointR(0,0);
		
		PointR.translateBBoxTo(originalDiamond, toPt);
		
		ArrayList<PointR> translatedDiamond = new ArrayList<PointR>();
		translatedDiamond.add(new PointR(6, 3, 0));
		translatedDiamond.add(new PointR(3, 0, 0));
		translatedDiamond.add(new PointR(0, 3, 0));
		translatedDiamond.add(new PointR(3, 6, 0));
		
		assertTrue(areEqual(originalDiamond,translatedDiamond));
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
	public void testAngleInRadians(){				
		PointR origin = new PointR(0, 0);
		PointR right = new PointR(5, 0);
		PointR left = new PointR(-5,0);
		PointR up = new PointR(0,5);
		PointR down = new PointR(0,-5);
		
		PointR p2 = new PointR(4, 2);
		
		boolean positiveOnly = true;		

		double angleInRadiansR = PointR.getAngleInRadians(origin, right, positiveOnly);
		double angleInRadiansL = PointR.getAngleInRadians(origin, left, positiveOnly);
		double angleInRadiansU = PointR.getAngleInRadians(origin, up, positiveOnly);
		double angleInRadiansD = PointR.getAngleInRadians(origin, down, positiveOnly);

		assertTrue(angleInRadiansR == 0 * Math.PI / 2);
		assertTrue(angleInRadiansU == 1 * Math.PI / 2);
		assertTrue(angleInRadiansL == 2 * Math.PI / 2);
		assertTrue(angleInRadiansD == 3 * Math.PI / 2);		

		assertTrue(PointR.getAngleInRadians(origin, p2, true) == Math.atan2(2, 4));
	}
	
	@Test
	public void testAngleInDegrees(){
		PointR origin = new PointR(0, 0);
		PointR right = new PointR(5, 0);
		PointR left = new PointR(-5,0);
		PointR up = new PointR(0,5);
		PointR down = new PointR(0,-5);		
		PointR p2 = new PointR(5,5);
		
		boolean positiveOnly = true;		

		double angleInDegreesR = PointR.getAngleInDegrees(origin, right, positiveOnly);
		double angleInDegreesL = PointR.getAngleInDegrees(origin, left, positiveOnly);
		double angleInDegreesU = PointR.getAngleInDegrees(origin, up, positiveOnly);
		double angleInDegreesD = PointR.getAngleInDegrees(origin, down, positiveOnly);

		assertTrue(angleInDegreesR == 0);
		assertTrue(angleInDegreesU == 90);
		assertTrue(angleInDegreesL == 180);
		assertTrue(angleInDegreesD == 270);		

		assertTrue(PointR.getAngleInDegrees(origin, p2, true) == 45);
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
	
	@Test
	public void testTranslateByReference(){
		PointR origin = new PointR(0,0);
		
		ArrayList<PointR> points = new ArrayList<PointR>();		
		points.add(origin);
		
		SizeR identity = new SizeR(0, 0);
		
		// Identity operation: Do a "translation" which doesn't change the original
		ArrayList<PointR> translatedPoints = PointR.translateBy(points, identity, true);
		
		assertTrue(areEqual(translatedPoints,points));

		// Add a bunch of points to the list, one in each quadrant		
		PointR p1 = new PointR(1,1);
		PointR p2 = new PointR(5,-7);
		PointR p3 = new PointR(-3,6);
		PointR p4 = new PointR(-8,-2);		
		
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);

		// First translation: Right 4, up 9
		SizeR sz1 = new SizeR(4,9);
		
		ArrayList<PointR> translatedPoints1 = PointR.translateBy(points, sz1, true);
		ArrayList<PointR> expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(4,9));
		expectedTranslation.add(new PointR(5,10));
		expectedTranslation.add(new PointR(9,2));
		expectedTranslation.add(new PointR(1,15));
		expectedTranslation.add(new PointR(-4,7));
				
		assertTrue(areEqual(translatedPoints1, expectedTranslation));

		// Second translation: Right 5, down 7
		SizeR sz2 = new SizeR(5,-7);
		
		ArrayList<PointR> translatedPoints2 = PointR.translateBy(points, sz2, true);
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(5,-7));
		expectedTranslation.add(new PointR(6,-6));
		expectedTranslation.add(new PointR(10,-14));
		expectedTranslation.add(new PointR(2,-1));
		expectedTranslation.add(new PointR(-3,-9));
				
		assertTrue(areEqual(translatedPoints2, expectedTranslation));		
		
		// Third translation: Left 3, up 6
		SizeR sz3 = new SizeR(-3,6);
		
		ArrayList<PointR> translatedPoints3 = PointR.translateBy(points, sz3, true);
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(-3,6));
		expectedTranslation.add(new PointR(-2,7));
		expectedTranslation.add(new PointR(2,-1));
		expectedTranslation.add(new PointR(-6,12));
		expectedTranslation.add(new PointR(-11,4));
				
		assertTrue(areEqual(translatedPoints3, expectedTranslation));

		// Fourth translation: Left 8, Down 2
		SizeR sz4 = new SizeR(-8,-2);
		
		ArrayList<PointR> translatedPoints4 = PointR.translateBy(points, sz4, true);
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(-8,-2));
		expectedTranslation.add(new PointR(-7,-1));
		expectedTranslation.add(new PointR(-3,-9));
		expectedTranslation.add(new PointR(-11,4));
		expectedTranslation.add(new PointR(-16,-4));
				
		assertTrue(areEqual(translatedPoints4, expectedTranslation));
		
		// When passed by reference, the original list should not be altered
		assertFalse(areEqual(translatedPoints4, points));
		
		// Test the "Pass By Value" through the boolean operator.
		// First time should pass, basically the same call as the previous
		ArrayList<PointR> translatedPoints5 = PointR.translateBy(points, sz4, false);
		assertTrue(areEqual(translatedPoints5, expectedTranslation));
		// points should be translated
		assertTrue(areEqual(translatedPoints5,points));
		
		// Second time should fail, because points was set to translatedPoints5 in the previous test		
		ArrayList<PointR> translatedPoints6 = PointR.translateBy(points, sz4, false);
		assertFalse(areEqual(translatedPoints6, expectedTranslation));		
	}

	@Test
	public void testTranslateByValue(){		
		ArrayList<PointR> points = new ArrayList<PointR>();		
		points.add(new PointR(0,0));
		
		SizeR identity = new SizeR(0, 0);
		
		// Identity operation: Do a "translation" which doesn't change the original
		ArrayList<PointR> translatedPoints = PointR.translateBy(points, identity, true);
		
		assertTrue(areEqual(translatedPoints,points));

		points.add(new PointR(1,1));
		points.add(new PointR(5,-7));
		points.add(new PointR(-3,6));
		points.add(new PointR(-8,-2));
		
		// First translation: Right 4, up 9
		SizeR sz1 = new SizeR(4,9);
		
		ArrayList<PointR> translatedPoints1 = PointR.translateBy(points, sz1);
		ArrayList<PointR> expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(4,9));
		expectedTranslation.add(new PointR(5,10));
		expectedTranslation.add(new PointR(9,2));
		expectedTranslation.add(new PointR(1,15));
		expectedTranslation.add(new PointR(-4,7));
				
		assertTrue(areEqual(translatedPoints1, expectedTranslation));
		assertTrue(areEqual(translatedPoints1, points));
		
		// Second translation: Right 5, down 7
		SizeR sz2 = new SizeR(5,-7);
		
		points = new ArrayList<PointR>();		
		points.add(new PointR(0,0));
		points.add(new PointR(1,1));
		points.add(new PointR(5,-7));
		points.add(new PointR(-3,6));
		points.add(new PointR(-8,-2));
		
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(5,-7));
		expectedTranslation.add(new PointR(6,-6));
		expectedTranslation.add(new PointR(10,-14));
		expectedTranslation.add(new PointR(2,-1));
		expectedTranslation.add(new PointR(-3,-9));
		
		ArrayList<PointR> translatedPoints2 = PointR.translateBy(points, sz2);
				
		assertTrue(areEqual(translatedPoints2, expectedTranslation));		
		
		// Third translation: Left 3, up 6
		SizeR sz3 = new SizeR(-3,6);
		
		points = new ArrayList<PointR>();		
		points.add(new PointR(0,0));
		points.add(new PointR(1,1));
		points.add(new PointR(5,-7));
		points.add(new PointR(-3,6));
		points.add(new PointR(-8,-2));
		
		ArrayList<PointR> translatedPoints3 = PointR.translateBy(points, sz3);
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(-3,6));
		expectedTranslation.add(new PointR(-2,7));
		expectedTranslation.add(new PointR(2,-1));
		expectedTranslation.add(new PointR(-6,12));
		expectedTranslation.add(new PointR(-11,4));
				
		assertTrue(areEqual(translatedPoints3, expectedTranslation));

		// Fourth translation: Left 8, Down 2
		SizeR sz4 = new SizeR(-8,-2);
		
		points = new ArrayList<PointR>();	
		points.add(new PointR(0,0));
		points.add(new PointR(1,1));
		points.add(new PointR(5,-7));
		points.add(new PointR(-3,6));
		points.add(new PointR(-8,-2));
		
		ArrayList<PointR> translatedPoints4 = PointR.translateBy(points, sz4);
		expectedTranslation = new ArrayList<PointR>();
		expectedTranslation.add(new PointR(-8,-2));
		expectedTranslation.add(new PointR(-7,-1));
		expectedTranslation.add(new PointR(-3,-9));
		expectedTranslation.add(new PointR(-11,4));
		expectedTranslation.add(new PointR(-16,-4));
				
		assertTrue(areEqual(translatedPoints4, expectedTranslation));
	}
	
	/**
	 * Is the first list equivalent to the second list?
	 * This is in the Test class because there may be a better way to compare lists
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean areEqual(ArrayList<PointR> first, ArrayList<PointR> second){
		
		if(first.size() != second.size())
			return false;
		
		for(int i = 0; i < first.size(); i++){
			PointR firstPoint = first.get(i);
			PointR secondPoint = second.get(i);
			
			if(!firstPoint.equals(secondPoint))
				return false;			
		}
		
		return true;		
	}
	
	@Test
	public void testAreEqual(){
		ArrayList<PointR> l1 = new ArrayList<PointR>();
		ArrayList<PointR> l2 = new ArrayList<PointR>();
		
		assertTrue(areEqual(l1,l2));
		l1.add(new PointR(1,1));
		assertFalse(areEqual(l1,l2));
		l2.add(new PointR(1,1));
		assertTrue(areEqual(l1,l2));
		l1.add(new PointR(3,1));
		l2.add(new PointR(1,3));
		say("l1x:"+l1.get(1).X);
		say("l2x:"+l2.get(1).X);
		assertFalse(areEqual(l1,l2));	
	}
	
	public static void say(String message){
		System.out.println(message);
	}
}
