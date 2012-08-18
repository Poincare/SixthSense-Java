/**
 * Authors: Dhaivat Pandya, Aroop Ganguly
 */
package sixthsense;

import java.util.List;

/*
 * RectangleR:
 * 
 * A utility class that basically implements the idea of a rectangle.
 */
public class RectangleR {
	// digits to which measurements are rounded
	private int digits = 4;
	// abcd
	// denote top left corner
	private double x;
	private double y;
	// dimensions of rectangle
	private double width;
	private double height;
	
	// constructor to copy values over
	public RectangleR(double X, double Y, double W, double H) {
		x = X;
		y = Y;
		width = W;
		height = H;
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
		double center_x = x + width / 2;
		double center_y = y + height / 2;
		PointR res = new PointR(center_x, center_y);
		return res;
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
		
		if(points.size() < 4) {
			throw new NotEnoughPointsException();
		}
		
		for (PointR p : points) {
			if (p.X > maxX) {
				maxX = p.X;
			}
			if (p.Y > maxY) {
				maxY = p.Y;
			}
			if (p.X < minX) {
				minX = p.X;
			}
			if (p.Y < minY) {
				minY = p.Y;
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
