/**
 * Author: Aroop Ganguly
 */
package sixthsense;

import java.util.ArrayList;

public class Gesture // : IComparable
{
	private String name;
	private ArrayList<PointR> rawPoints; // raw points (for drawing) -- read in
	
	public ArrayList<PointR> getRawPoints() {
		return rawPoints;
	}
	
	public void setRawPoints(ArrayList<PointR> rawPoints) {
		this.rawPoints = rawPoints;
	}
	
	// from
	// XML
	public ArrayList<PointR> points; // resampled points (for matching) -- done
										// when
	
	// loaded
	public Gesture() {
		this.setName("");
		this.rawPoints = null;
		this.points = null;
	}
	
	// when a new prototype is made, its raw points are resampled into n
	// equidistantly spaced
	// points, then it is scaled to a preset size and translated to a preset
	// origin. this is
	// the same treatment applied to each candidate stroke, and it allows us to
	// thereafter
	// simply step through each point in each stroke and compare those points'
	// distances.
	// in other words, it removes the challenge of determining corresponding
	// points in each gesture.
	// after resampling, scaling, and translating, we compute the
	// "indicative angle" of the
	// stroke as defined by the angle between its centroid point and first
	// point.
	public Gesture(String name, ArrayList<PointR> points) {
		this.setName(name);
		this.rawPoints = points; // copy (saved for
									// drawing)
		// resample first (influences calculation of centroid)
		// Points = Utils.Resample(points,
		// GeometricRecognizer.NumResamplePoints);
		//
		// // rotate so that the centroid-to-1st-point is at zero degrees
		// double radians = Utils.AngleInRadians(Utils.Centroid(Points),
		// (PointR) Points[0], false);
		// Points = Utils.RotateByRadians(Points, -radians); // undo angle
		//
		// // scale to a common (square) dimension
		// Points = Utils.ScaleTo(Points, GeometricRecognizer.ResampleScale);
		//
		// // finally, translate to a common origin
		// Points = Utils.TranslateCentroidTo(Points,
		// GeometricRecognizer.ResampleOrigin);
	}
	
	public int getDuration() {
		if (rawPoints.size() >= 2) {
			PointR p0 = (PointR) rawPoints.get(0);
			PointR pn = (PointR) rawPoints.get(rawPoints.size() - 1);
			return pn.T - p0.T;
		}
		else {
			return 0;
		}
	}
	
	// sorts in descending order of Score
	public int compareTo(Object obj) throws Exception {
		if (obj.getClass().equals(Gesture.class)) {
			Gesture g = (Gesture) obj;
			return getName().compareTo(g.getName());
		}
		else
			throw new Exception("object is not a Gesture");
	}
	
	// / <summary>
	// / Pulls the gesture name from the file name, e.g., "circle03" from
	// "C:\gestures\circles\circle03.xml".
	// / </summary>
	// / <param name="s"></param>
	// / <returns></returns>
	public static String ParseName(String filename) {
		int start = filename.lastIndexOf('\\');
		int end = filename.lastIndexOf('.');
		return filename.substring(start + 1, end);
	}
	
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
}