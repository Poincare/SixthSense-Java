/**
 * Author: Aroop Ganguly
 */
package classes;

import java.util.ArrayList;
import java.util.List;

//: IComparable ?
public class Gesture implements Comparable<Gesture>
{
	private String name;
	// raw points (for drawing) -- read in from XML
	private List<PointR> rawPoints;
	// resampled points (for matching) -- done when loaded
	private List<PointR> points;

	public List<PointR> getRawPoints() {
		return rawPoints;
	}

	public void setRawPoints(List<PointR> rawPoints) {
		this.rawPoints = rawPoints;
	}

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
	public Gesture(String name, List<PointR> points) {
		this.setName(name);
		if (points != null) {
			// copy (saved for drawing)
			this.rawPoints = new ArrayList<PointR>(points);
			if (points.size() != 0) {
				// resample first (influences calculation of centroid)
				this.points = PointR.resample(points, GeometricRecognizer.NUMRESAMPLEPOINTS);
				// rotate so that the centroid-to-1st-point is at zero degrees
				double radians = PointR.getAngleInRadians(PointR.getCentroid(this.points), this.points.get(0), false);
				// undo angle
				this.points = PointR.rotateByRadians(this.points, -radians);
				// scale to a common (square) dimension
				this.points = PointR.scaleTo(this.points, GeometricRecognizer.RESAMPLESCALE);
				// finally, translate to a common origin
				this.points =  PointR.translateCentroidTo(this.points, GeometricRecognizer.RESAMPLEORIGIN);
			}
		}
		else {
			this.rawPoints = null;
			this.points = null;
		}
	}

	/**
	 * @return the points
	 */
	public List<PointR> getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(List<PointR> points) {
		this.points = points;
	}

	public int getDuration() {
		if (rawPoints.size() >= 2) {
			PointR p0 = (PointR) rawPoints.get(0);
			PointR pn = (PointR) rawPoints.get(rawPoints.size() - 1);
			return pn.getT() - p0.getT();
		}
		else {
			return 0;
		}
	}

	// / <summary>
	// / Pulls the gesture name from the file name, e.g., "circle03" from
	// "C:\gestures\circles\circle03.xml".
	// / </summary>
	// / <param name="s"></param>
	// / <returns></returns>
	public static String parseName(String filename) {
		String os = System.getProperty("os.name").toLowerCase();
		int start = 0;
		if (os.indexOf("win") >= 0) {
			start = filename.lastIndexOf('\\');	
		}
		else {
			start = filename.lastIndexOf('/');
		}
		int end = filename.lastIndexOf('.');
		return filename.substring(start + 1, end);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}


	/** 
	 * Sorts in descending order of Score
	 */
	@Override
	public int compareTo(Gesture o) {
		return getName().compareTo(o.getName());
	}
}