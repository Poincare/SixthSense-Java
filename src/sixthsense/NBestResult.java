/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

@SuppressWarnings("rawtypes")
public class NBestResult implements Comparable {
	private String name;
	private double score;
	private double distance;
	private double angle;
	
	public NBestResult(String name, double score, double distance, double angle) {
		this.name = name;
		this.score = score;
		this.distance = distance;
		this.angle = angle;
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public boolean isEmpty() {
		return score == -1d;
	}
	
	@Override
	// sorts in descending order of Score
	public int compareTo(Object obj) {
		if (obj.getClass().equals(NBestResult.class)) {
			NBestResult r = (NBestResult) obj;
			if (score < r.score) {
				return 1;
			}
			else if (score > r.score) {
				return -1;
			}
			else {
				if (r.getName().equals(getName())
						&& r.getDistance() == getDistance()
						&& r.getAngle() == getAngle()) {
					return 0;
				}
				else {
					return -1;
				}
			}
		}
		else {
			return -1;
		}
	}
}
