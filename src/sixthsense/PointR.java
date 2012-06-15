package sixthsense;

public class PointR {
	public double X, Y;
	public int T;
	
	public PointR(double x, double y, int t) {
		X = x;
		Y = y;
		T = t;
	}
	
	public PointR(double x, double y) {
		this(x, y, 0);
	}
	
	public PointR(PointR p) {
		T = p.T;
		X = p.X;
		Y = p.Y;
	}
	
	public static double distance(PointR a, PointR b) {
		return Math.sqrt((a.X-b.X)*(a.X-b.X)+(a.Y-b.Y)*(a.Y-b.Y));
	}
	
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
	
	
}
