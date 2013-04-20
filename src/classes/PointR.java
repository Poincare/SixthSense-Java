/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package classes;

import java.util.ArrayList;
import java.util.List;

/*
 * PointR
 * 
 * A class that implements the idea of a point
 */
public class PointR {
	private double x, y;
	private int t;
	
	private static final double ANGLEROTATION = 180d;
	
	// basic constructor to copy over values
	public PointR(double x, double y, int t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
	
	// copy over x and y, set t to 0
	public PointR(double x, double y) {
		this(x, y, 0);
	}
	
	// Copy constructor
	public PointR(PointR p) {
		this.t = p.getT();
		this.x = p.getX();
		this.y = p.getY();
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the t
	 */
	public int getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(int t) {
		this.t = t;
	}

	// Static utility to judge distance between two points
	public static double getDistance(PointR a, PointR b) {
		return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
	}
	
	// Instance utility to judge distance between the instance and another point
	public double getDistance(PointR b) {
		return PointR.getDistance(this, b);
	}
	
	// basic equals override; checks all values
	public boolean equals(Object a) {
		if (a instanceof PointR) {
			PointR b = (PointR) a;
			if (b.getX() == getX() && b.getY() == getY() && b.getT() == getT()) {
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
			xsum += p.getX();
			ysum += p.getY();
		}
		return new PointR(xsum / n, ysum / n);
	}
	
	// get the path length of a list of points - the order of the points is the
	// order of the list from index 0 to n
	public static double getPathLength(List<PointR> points) {
		double totalDistance = 0;
		for (int i = 1; i < points.size(); i++) {
			totalDistance += PointR.getDistance(points.get(i - 1),
					points.get(i));
		}
		return totalDistance;
	}
	
	public static RectangleR findBox(List<PointR> points) {
		double minX = Double.MIN_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for (PointR p : points) {
			if (p.getX() < minX) {
				minX = p.getX();
			}
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getY() < minY) {
				minY = p.getY();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
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
		if (start.getX() != end.getX()) {
			radians = Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
		}
		// pure vertical movement
		else
		{
			if (end.getY() < start.getY()) {
				// -90 degrees is straight up
				radians = -Math.PI / 2.0;
			}
			else if (end.getY() > start.getY()) {
				// 90 degrees is straight down
				radians = Math.PI / 2.0;
			}
		}
		if (positiveOnly && radians < 0.0) {
			radians += Math.PI * 2.0;
		}
		return radians;
	}
	
	public static double rad2Deg(double rad) {
		return (rad * ANGLEROTATION / Math.PI);
	}
	
	public static double deg2Rad(double deg) {
		return (deg * Math.PI / ANGLEROTATION);
	}
	
	/*** rotate the points by the given degrees about their centroid */
	public static List<PointR> rotateByDegrees(List<PointR> points,
			double degrees) {
		double radians = deg2Rad(degrees);
		return rotateByRadians(points, radians);
	}
	
	/** rotate the points by the given radians about their centroid */
	public static List<PointR> rotateByRadians(List<PointR> points,
			double radians) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		PointR c = getCentroid(points);
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);
		double cx = c.getX();
		double cy = c.getY();
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			double dx = p.getX() - cx;
			double dy = p.getY() - cy;
			PointR q = new PointR(0, 0);
			q.setX(dx * cos - dy * sin + cx);
			q.setY(dx * sin + dy * cos + cy);
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
		q.setX((p.getX() - c.getX()) * Math.cos(radians) - (p.getY() - c.getY()) * Math.sin(radians)
				+ c.getX());
		q.setY((p.getX() - c.getX()) * Math.sin(radians) + (p.getY() - c.getY()) * Math.cos(radians)
				+ c.getY());
		return q;
	}
	
	/**
	 * translates the points so that the upper-left corner of their bounding box
	 * lies at 'toPt'
	 */
	public static List<PointR> translateBBoxTo(List<PointR> points,
			PointR toPt) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = PointR.findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() + (toPt.getX() - r.getX()));
			p.setY(p.getY() + (toPt.getY() - r.getY()));
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/*** translates the points so that their centroid lies at 'toPt' */
	public static List<PointR> translateCentroidTo(
			List<PointR> points, PointR toPt) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		PointR centroid = getCentroid(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() + (toPt.getX() - centroid.getX()));
			p.setY(p.getY() + (toPt.getY() - centroid.getY()));
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/** translates the points by the given delta amounts */
	public static List<PointR> translateBy(List<PointR> points,
			SizeR sz) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() + sz.getWidth());
			p.setY(p.getY() + sz.getHeight());
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/**
	 * scales the points so that they form the size given. does not restore the
	 * origin of the box
	 **/
	public static List<PointR> scaleTo(List<PointR> points, SizeR sz) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			if (r.getWidth() != 0d) {
				p.setX(p.getX() * (sz.getWidth() / r.getWidth()));
			}
			if (r.getHeight() != 0d) {
				p.setY(p.getY() * (sz.getHeight() / r.getHeight()));
			}
			newPoints.add(p);
		}
		return newPoints;
	}
	
	/***
	 * scales by the percentages contained in the 'sz' parameter. values of 1.0
	 * would result in the identity scale (that is, no change).
	 */
	public static List<PointR> scaleBy(List<PointR> points, SizeR sz) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() * sz.getWidth());
			p.setY(p.getY() * sz.getHeight());
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
	public static List<PointR> scaleToMax(List<PointR> points,
			RectangleR box) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() * (box.getMaxSide() / r.getMaxSide()));
			p.setY(p.getY() * (box.getMaxSide() / r.getMaxSide()));
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
	public static List<PointR> scaleToMin(List<PointR> points,
			RectangleR box) {
		List<PointR> newPoints = new ArrayList<PointR>(points.size());
		RectangleR r = findBox(points);
		for (int i = 0; i < points.size(); i++) {
			PointR p = points.get(i);
			p.setX(p.getX() * (box.getMinSide() / r.getMinSide()));
			p.setY(p.getY() * (box.getMinSide() / r.getMinSide()));
			newPoints.add(p);
		}
		return newPoints;
	}
	
	public static List<PointR> resample(List<PointR> points, int n) {
		// interval length
		double interval = getPathLength(points) / (n - 1);
		double d = 0.0;
		List<PointR> srcPts = new ArrayList<PointR>(points);
		List<PointR> dstPts = new ArrayList<PointR>(n);
		dstPts.add(srcPts.get(0));
		for (int i = 1; i < srcPts.size(); i++) {
			PointR pt1 = srcPts.get(i - 1);
			PointR pt2 = srcPts.get(i);
			double distance = getDistance(pt1, pt2);
			if ((d + distance) >= interval) {
				double qx = pt1.getX() + ((interval - d) / distance) * (pt2.getX() - pt1.getX());
				double qy = pt1.getY() + ((interval - d) / distance) * (pt2.getY() - pt1.getY());
				PointR q = new PointR(qx, qy);
				// append new point 'q'
				dstPts.add(q);
				// insert 'q' at position i in points s.t. 'q'
				srcPts.add(i, q);
				// will be the next i
				d = 0.0;
			}
			else {
				d += distance;
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
	public static double getPathDistance(List<PointR> path1,
			List<PointR> path2) {
		double distance = 0;
		for (int i = 0; i < path1.size(); i++) {
			distance += getDistance(path1.get(i), path2.get(i));
		}
		return distance / path1.size();
	}
}
