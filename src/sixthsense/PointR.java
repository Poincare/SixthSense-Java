/*
Authors: Dhaivat Pandya
*/

package sixthsense;

import java.util.List;

/* 
 * PointR
 * 
 * A class that implements the idea of a point
 */
public class PointR {
	public double X, Y;
	public int T;
	
	//basic constructor to copy over values
	public PointR(double x, double y, int t) {
		X = x;
		Y = y;
		T = t;
	}
	
	//copy over x and y, set t to 0
	public PointR(double x, double y) {
		this(x, y, 0);
	}
	
	//Copy constructor
	public PointR(PointR p) {
		T = p.T;
		X = p.X;
		Y = p.Y;
	}
	
	//Static utility to judge distance between two points
	public static double distance(PointR a, PointR b) {
		return Math.sqrt((a.X-b.X)*(a.X-b.X)+(a.Y-b.Y)*(a.Y-b.Y));
	}
	
	//Instance uitlity to judge distance between the instance and another point
	public double distance(PointR b) {
		return PointR.distance(this, b);
	}
	
	//basic equals override; checks all values
	public boolean equals(Object a) {
		if(a instanceof PointR) {
			PointR b = (PointR)a;
			if(b.X == X && b.Y == Y && b.T == T) {
				return true;
			}
			return false;
		}
		return false;
	} 
	
	//Static utility to get the centroid of a list of points
	public static PointR getCentroid(List<PointR> points) {
		double xsum = 0;
		double ysum = 0;
		int n = points.size();
		
		for(PointR p : points ) {
			xsum += p.X;
			ysum += p.Y;
		}
		
		return new PointR(xsum/n, ysum/n);
	}
	
	//get the path length of a list of points - the order of the points is the order of the list from index 0 to n
	public static double getPathLength(List<PointR> points) {
		double total_distance = 0;
		
		for(int i = 1; i<points.size(); i++) {
			total_distance += PointR.distance(points.get(i-1), points.get(i));
		}
		
		return total_distance;
	}
	
}
