/*
Authors: Dhaivat Pandya
*/

package sixthsense;

public class RectangleR {
	//digits to which measurements are rounded
	private int digits = 4;
	
	//denote top left corner
	private double x;
	private double y;
	
	//dimensions of rectangle
	private double width;
	private double height;
	
	public RectangleR(double X, double Y, double W, double H) {
		x = X;
		y = Y;
		width = W;
		height = H;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public PointR getTopleft() {
		return new PointR(x, y);
	}
	
	public PointR getBottomRight() {
		return new PointR(x+width, y+height);
	}
	
	public PointR getCenter() {
		double center_x = x+width/2;
		double center_y = y+height/2;
		
		PointR res = new PointR(center_x, center_y);
		
		return res;
	}
	
	public double getMaxSide() {
		return Math.max(width, height);
	}

	public double getMinSide() {
		return Math.min(width, height);
	}

	public double getDiagonal() {
		return PointR.distance(getTopleft(), getBottomRight());
	}
	
	public boolean equals(Object b) {
		if(b instanceof RectangleR) {
			RectangleR c = (RectangleR)b;
			if(c.x == this.x && c.y == this.y && c.height == this.height && c.width == this.width) {
				return true;
			}
		}
		return false;
	}
	
	//port complete
}







