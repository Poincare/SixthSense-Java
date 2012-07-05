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
	
	public final NBestResult Empty = new NBestResult("", -1d, -1d, 0d);
	
	public String get_name() {
		return _name;
	}
	
	public double get_score() {
		return _score;
	}
	
	public double get_distance() {
		return _distance;
	}
	
	public double get_angle() {
		return _angle;
	}
	
	public boolean IsEmpty() {
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
				if (r.get_name().equals(get_name())
						&& r.get_distance() == get_distance()
						&& r.get_angle() == get_angle())
					return 0;
				else
					return -1;
			}
		}
		else {
			try {
				throw new Exception("object is not a Result");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}
}
