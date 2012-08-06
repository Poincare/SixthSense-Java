/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

@SuppressWarnings("rawtypes")
public class NBestResult implements Comparable {
	private String _name;
	private double _score;
	private double _distance;
	private double _angle;
	
	public NBestResult(String name, double score, double distance, double angle) {
		_name = name;
		_score = score;
		_distance = distance;
		_angle = angle;
	}
	
	// public final NBestResult Empty = new NBestResult("", -1d, -1d, 0d);
	public String getName() {
		return _name;
	}
	
	public double getScore() {
		return _score;
	}
	
	public double getDistance() {
		return _distance;
	}
	
	public double getAngle() {
		return _angle;
	}
	
	public boolean isEmpty() {
		return _score == -1d;
	}
	
	@Override
	// sorts in descending order of Score
	public int compareTo(Object obj) {
		if (obj.getClass().equals(NBestResult.class)) {
			NBestResult r = (NBestResult) obj;
			if (_score < r._score)
				return 1;
			else if (_score > r._score)
				return -1;
			else {
				if (r.getName().equals(getName())
						&& r.getDistance() == getDistance()
						&& r.getAngle() == getAngle())
					return 0;
				else
					return -1;
			}
		}
		else {
			return -1;
		}
	}
}
