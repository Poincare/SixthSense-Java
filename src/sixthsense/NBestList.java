package sixthsense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBestList {
	public static NBestList Empty = new NBestList();
	private ArrayList<NBestResult> _nBestList;
	
	public NBestList() {
		_nBestList = new ArrayList<NBestResult>();
	}
	
	public void AddResult(String name, double score, double distance,
			double angle) {
		NBestResult r = new NBestResult(name, score, distance, angle);
		_nBestList.add(r);
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double Angle() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.get_angle();
		}
		return 0.0;
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double Distance() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.get_distance();
		}
		return -1.0;
	}
	
	/**
	 * acts as an indexer for the NBestList, and returns a Result at a specified
	 * index
	 */
	public NBestResult Get(int index) {
		if (0 <= index && index < _nBestList.size()) {
			return (NBestResult) _nBestList.get(index);
		}
		return null;
	}
	
	public boolean IsEmpty() {
		return _nBestList.size() == 0;
	}
	
	/**
	 * Gets the gesture name of the top result of the NBestList.
	 **/
	public String Name() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.get_name();
		}
		return "";
	}
	
	public String[] Names() {
		String[] s = new String[_nBestList.size()];
		if (_nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) _nBestList.get(i)).get_name();
			}
		}
		return s;
	}
	
	public String NamesString() {
		String s = "";
		if (_nBestList.size() > 0) {
			for (NBestResult r : _nBestList) {
				s += String.format("{0},", r.get_name());
			}
		}
		return ((s.endsWith(",")) ? s.trim() : s);
	}
	
	/**
	 * Gets the [0..1] matching score of the top result of the NBestList.
	 **/
	public double Score() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.get_score();
		}
		return -1.0;
	}
	
	public double[] Scores() {
		double[] s = new double[_nBestList.size()];
		if (_nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) _nBestList.get(0)).get_score();
			}
		}
		return s;
	}
	
	public String ScoresString() {
		String s = "";
		if (_nBestList.size() > 0) {
			for (NBestResult r : _nBestList) {
				s += String.format("{0},", Math.round(r.get_score()));
			}
		}
		return ((s.endsWith(",")) ? s.trim() : s);
	}
	
	@SuppressWarnings("unchecked")
	public void SortDescending() {
		Collections.sort((List<NBestResult>) _nBestList);
	}
}
