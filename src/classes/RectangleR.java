/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package classes;

import java.util.List;

/*
 * RectangleR:
 * 
 * A utility class that basically implements the idea of a rectangle.
 */
public class RectangleR {
	
	private static final int NBDIGITS = 4;
	// digits to which measurements are rounded
	private int digits = NBDIGITS;
	// abcd
	// denote top left corner
	private double x;
	private double y;
	// dimensions of rectangle
	private double width;
	private double height;
	
	// constructor to copy values over
	public RectangleR(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	// get x value
	public double getX() {
		return x;
	}
	
	// get y value
	public double getY() {
		return y;
	}
	
	// get width of the RectangleR
	public double getWidth() {
		return width;
	}
	
	// get height of the RectangleR
	public double getHeight() {
		return height;
	}
	
	// get topleft corner
	public PointR getTopleft() {
		return new PointR(x, y);
	}
	
	// get bottomright corner
	public PointR getBottomRight() {
		return new PointR(x + width, y + height);
	}
	
	// get center/centroid of the rectangle
	public PointR getCenter() {
		double centerX = x + width / 2;
		double centerY = y + height / 2;
		return new PointR(centerX, centerY);
	}
	
	// get the length of the longest side of the rectangle
	public double getMaxSide() {
		return Math.max(width, height);
	}
	
	// get the length of the shortest side of the rectangle
	public double getMinSide() {
		return Math.min(width, height);
	}
	
	// get the length of the diagonal of the rectangle
	public double getDiagonal() {
		return PointR.getDistance(getTopleft(), getBottomRight());
	}
	
	// basic equals override - checks equality of everything, not just if
	// translated
	public boolean equals(Object b) {
		if (b instanceof RectangleR) {
			RectangleR c = (RectangleR) b;
			if (c.x == this.x && c.y == this.y && c.height == this.height
					&& c.width == this.width) {
				return true;
			}
		}
		return false;
	}
	
	// Find the largest box with the specified points
	public static RectangleR findBox(List<PointR> points) throws NotEnoughPointsException {
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		
		if(points.size() < NBDIGITS) {
			throw new NotEnoughPointsException();
		}
		
		for (PointR p : points) {
			if (p.getX() > maxX) {
				maxX = p.getX();
			}
			if (p.getY() > maxY) {
				maxY = p.getY();
			}
			if (p.getX() < minX) {
				minX = p.getX();
			}
			if (p.getY() < minY) {
				minY = p.getY();
			}
		}
		return new RectangleR(minX, minY, maxX - minX, maxY - minY);
	}
	
	// getter-setter -- Mostly from legacy C# code
	public int getDigits() {
		return digits;
	}
	
	public void setDigits(int digits) {
		this.digits = digits;
	}
}
