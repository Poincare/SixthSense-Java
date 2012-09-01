/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package sixthsense;

import java.util.ArrayList;
import java.util.List;

/*
 * PointR
 * 
 * A class that implements the idea of a point
 */
public class PointR {
	public double X, Y;
	public int T;
	
	// basic constructor to copy over values
	public PointR(double x, double y, int t) {
		X = x;
		Y = y;
		T = t;
	}
	
	// copy over x and y, set t to 0
	public PointR(double x, double y) {
		this(x, y, 0);
	}
	
	// Copy constructor
	public PointR(PointR p) {
		T = p.T;
		X = p.X;
		Y = p.Y;
	}
	
	// Static utility to judge distance between two points
	public static double getDistance(PointR a, PointR b) {
		return Math.sqrt((a.X - b.X) * (a.X - b.X) + (a.Y - b.Y) * (a.Y - b.Y));
	}
	
	// Instance utility to judge distance between the instance and another point
	public double getDistance(PointR b) {
		return PointR.getDistance(this, b);
	}
	
	// basic equals override; checks all values
	public boolean equals(Object a) {
		if (a instanceof PointR) {
			PointR b = (PointR) a;
			if (b.X == X && b.Y == Y && b.T == T) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	// Static utility to get the centroid of a list of points
	public static PointR getCentroid(List<PointR> points) {
		double xsum = 0;
		double ysum = 0;
		int n = points.size();
		for (PointR p : points) {
			xsum += p.X;
			ysum += p.Y;
		}
		return new PointR(xsum / n, ysum / n);
	}
	
	// get the path length of a list of points - the order of the points is the
	// order of the list from index 0 to n
	public static double getPathLength(List<PointR> points) {
		double total_distance = 0;
		for (int i = 1; i < points.size(); i++) {
			total_distance += PointR.getDistance(points.get(i - 1),
					points.get(i));
		}
		return total_distance;
	}
	
	public static RectangleR findBox(ArrayList<PointR> points) {
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		for (PointR p : points) {
			if (p.X < minX)
				minX = p.X;
			if (p.X > maxX)
				maxX = p.X;
			if (p.Y < minY)
				minY = p.Y;
			if (p.Y > maxY)
				maxY = p.Y;
		}
		return new RectangleR(minX, minY, maxX - minX, maxY - minY);
	}
	
	/***
	 * determines the angle, in degrees, between two points. the angle is
	 * defined by the circle centered on the start point with a radius to the
	 * end point, where 0 degrees is straight right from start (+x-axis) and 90
	 * degrees is straight down (+y-axis).
	 */
	public static double getAngleInDegrees(PointR start, PointR end,
			Boolean positiveOnly) {
		double radians = getAngleInRadians(start, end, positiveOnly);
		return rad2Deg(radians);
	}
	
	/***
	 * determines the angle, in radians, between two points. the angle is
	 * defined by the circle centered on the start point with a radius to the
	 * end point, where 0 radians is straight right from start (+x-axis) and
	 * PI/2 radians is straight down (+y-axis).
	 */
	public static double getAngleInRadians(PointR start, PointR end,
			boolean positiveOnly) {
		double radians = 0.0;
		if (start.X != end.X) {
			radians = Math.atan2(end.Y - start.Y, end.X - start.X);
		}
		else // pure vertical movement
		{
			if (end.Y < start.Y)
				radians = -Math.PI / 2.0; // -90 degrees is straight up
			else if (end.Y > start.Y)
				radians = Math.PI / 2.0; // 90 degrees is straight down
		}
		if (positiveOnly && radians < 0.0) {
			radians += Math.PI * 2.0;
		}
		return radians;
	}
	
	public static double rad2Deg(double rad) {
		return (rad * 180d / Math.PI);
	}
	
	public static double deg2Rad(double deg) {
		return (deg * Math.PI / 180d);
	}
	
	/*** rotate the points by the given degrees about their centroid */
	public static ArrayList<PointR> rotateByDegrees(ArrayList<PointR> points,
			double degrees) {
		double radians = deg2Rad(degrees);
		return rotateByRadians(points, radians);
	}
	
	/** rotate the points by the given radians about their centroid */
	public static ArrayList<PointR> rotateByRadians(ArrayList<PointR> points,
			double radians) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		PointR c = getCentroid(points);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		double cx = c.X;
		double cy = c.Y;
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			double dx = p.X - cx;
			double dy = p.Y - cy;
			PointR q = new PointR(0, 0);
			q.X = dx * cos - dy * sin + cx;
			q.Y = dx * sin + dy * cos + cy;
			newPoints.add(q);
		}
		return newPoints;
	}
	
	/***
	 * Rotate a point 'p' around a point 'c' by the given radians. Rotation
	 * (around the origin) amounts to a 2x2 matrix of the form: [ cos A -sin A ]
	 * [ p.x ] [ sin A cos A ] [ p.y ] Note that the C//# Math coordinate system
	 * has +x-axis stright right and +y-axis straight down. Rotation is
	 * clockwise such that from +x-axis to +y-axis is +90 degrees, from +x-axis
	 * to -x-axis is +180 degrees, and from +x-axis to -y-axis is -90 degrees.
	 */
	public static PointR rotatePoint(PointR p, PointR c, double radians) {
		PointR q = new PointR(0, 0);
		q.X = (p.X - c.X) * Math.cos(radians) - (p.Y - c.Y) * Math.sin(radians)
				+ c.X;
		q.Y = (p.X - c.X) * Math.sin(radians) + (p.Y - c.Y) * Math.cos(radians)
				+ c.Y;
		return q;
	}
	
	/**
	 * translates the points so that the upper-left corner of their bounding box
	 * lies at 'toPt'
	 */
	public static ArrayList<PointR> translateBBoxTo(ArrayList<PointR> points,
			PointR toPt) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = PointR.findBox(points);
		
		double deltaX = toPt.X - r.getX();
		double deltaY = toPt.Y - r.getY();
		
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X += deltaX;
			p.Y += deltaY;
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/*** translates the points so that their centroid lies at 'toPt' */
	public static ArrayList<PointR> translateCentroidTo(
			ArrayList<PointR> points, PointR toPt) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		PointR centroid = getCentroid(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X += (toPt.X - centroid.X);
			p.Y += (toPt.Y - centroid.Y);
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/** translates the points by the given delta amounts 
	 * Because Java passes by value (and not by reference),
	 * the original list (its points) will be modified.
	 * */
	public static ArrayList<PointR> translateBy(ArrayList<PointR> points,
			SizeR sz) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X += sz.getWidth();
			p.Y += sz.getHeight();
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/**
	 * Translate all points' X and Y coordinates by the amount
	 * specified in sz. If the input list should remain un-altered,
	 * set byReference to True
	 * @param points
	 * @param sz
	 * @param byReference
	 * @return
	 */
	public static ArrayList<PointR> translateBy(ArrayList<PointR> points,
			SizeR sz, boolean byReference){
		
		if(!byReference)
			return translateBy(points,sz);
		
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			PointR newPoint = new PointR(p.X,p.Y);
			
			newPoint.X += sz.getWidth();
			newPoint.Y += sz.getHeight();
			
			newPoints.add(newPoint);
		}
		return newPoints;
	}
	
	/**
	 * scales the points so that they form the size given. does not restore the
	 * origin of the box
	 **/
	public static ArrayList<PointR> scaleTo(ArrayList<PointR> points, SizeR sz) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			if (r.getWidth() != 0d)
				p.X *= (sz.getWidth() / r.getWidth());
			if (r.getHeight() != 0d)
				p.Y *= (sz.getHeight() / r.getHeight());
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/***
	 * scales by the percentages contained in the 'sz' parameter. values of 1.0
	 * would result in the identity scale (that is, no change).
	 */
	public static ArrayList<PointR> scaleBy(ArrayList<PointR> points, SizeR sz) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X *= sz.getWidth();
			p.Y *= sz.getHeight();
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/***
	 * scales the points so that the length of their longer side matches the
	 * length of the longer side of the given box. thus, both dimensions are
	 * warped proportionally, rather than independently, like in the function
	 * ScaleTo.
	 */
	public static ArrayList<PointR> scaleToMax(ArrayList<PointR> points,
			RectangleR box) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X *= (box.getMaxSide() / r.getMaxSide());
			p.Y *= (box.getMaxSide() / r.getMaxSide());
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/***
	 * scales the points so that the length of their shorter side matches the
	 * length of the shorter side of the given box. thus, both dimensions are
	 * warped proportionally, rather than independently, like in the function
	 * ScaleTo.
	 */
	public static ArrayList<PointR> scaleToMin(ArrayList<PointR> points,
			RectangleR box) {
		ArrayList<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.X *= (box.getMinSide() / r.getMinSide());
			p.Y *= (box.getMinSide() / r.getMinSide());
			newPoints.add(p);
		}
		return newPoints;
	}
	
	public static ArrayList<PointR> resample(ArrayList<PointR> points, int n) {
		double I = getPathLength(points) / (n - 1); // interval length
		double D = 0.0;
		ArrayList<PointR> srcPts = new ArrayList<PointR>(points);
		ArrayList<PointR> dstPts = new ArrayList<PointR>(n);
		dstPts.add(srcPts.get(0));
		for (int i = 1; i < srcPts.size(); i++) {
			PointR pt1 = srcPts.get(i - 1);
			PointR pt2 = srcPts.get(i);
			double d = getDistance(pt1, pt2);
			if ((D + d) >= I) {
				double qx = pt1.X + ((I - D) / d) * (pt2.X - pt1.X);
				double qy = pt1.Y + ((I - D) / d) * (pt2.Y - pt1.Y);
				PointR q = new PointR(qx, qy);
				dstPts.add(q); // append new point 'q'
				srcPts.add(i, q); // insert 'q' at position i in points s.t. 'q'
									// will be the next i
				D = 0.0;
			}
			else {
				D += d;
			}
		}
		// somtimes we fall a rounding-error short of adding the last point, so
		// add it if so
		if (dstPts.size() == n - 1) {
			dstPts.add(srcPts.get(srcPts.size() - 1));
		}
		return dstPts;
	}
	
	/**
	 * computes the 'distance' between two point paths by summing their
	 * corresponding point distances. assumes that each path has been resampled
	 * to the same number of points at the same distance apart.
	 */
	public static double getPathDistance(ArrayList<PointR> path1,
			ArrayList<PointR> path2) {
		double distance = 0;
		for (int i = 0; i < path1.size(); i++) {
			distance += getDistance(path1.get(i), path2.get(i));
		}
		return distance / path1.size();
	}
}
