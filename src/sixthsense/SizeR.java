/*
 * Authors: Dhaivat Pandya
 */

package sixthsense;

public class SizeR {
	//width
	private double cx;
	//height
	private double cy;
	
	public double getCX() {
		return cx;
	}
	
	public double getCY() {
		return cy;
	}
	
	public double getWidth() {
		return cx;
	}
	
	public double getHeight() {
		return cy;
	}
	
	public SizeR(double x, double y) {
		cx = x;
		cy = y;
	}
	
	public SizeR(SizeR p) {
		cx = p.getCX();
		cy = p.getCY();
	}
	
	public boolean equals(Object a) {
		if(a instanceof SizeR) {
			SizeR sr = (SizeR)a;
			if(this.getCX() == sr.getCX() && this.getCY() == sr.getCY()) {
				return true;
			}
		}
		return false;
	}
	
}
