package sixthsense;

public class PointR {
	public double X, Y;
	public int T;
	
	public PointR(double x, double y, int t) {
		X = x;
		Y = y;
		T = t;
	}
	
	public PointR(PointR p) {
		T = p.T;
		X = p.X;
		Y = p.Y;
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
