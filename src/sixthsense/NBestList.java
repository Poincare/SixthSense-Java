/**
 * Authors: Aroop Ganguly
 */
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
	
	public void addResult(String name, double score, double distance,
			double angle) {
		NBestResult r = new NBestResult(name, score, distance, angle);
		_nBestList.add(r);
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double getAngle() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.getAngle();
		}
		return 0.0;
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double getDistance(){
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.getDistance();
		}
		
		return 0;
	}
	
	/**
	 * acts as an indexer for the NBestList, and returns a Result at a specified
	 * index
	 * @throws NBestListEmptyException 
	 */
	public NBestResult get(int index) {		
		if (0 <= index && index < _nBestList.size()) {
			return (NBestResult) _nBestList.get(index);
		}
		
		return null;
	}
	
	public boolean isEmpty() {
		return _nBestList.size() == 0;
	}
	
	/**
	 * Gets the gesture name of the top result of the NBestList.
	 **/
	public String getName() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.getName();
		}
		return "";
	}
	
	public String[] getNames() {
		String[] s = new String[_nBestList.size()];
		if (_nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) _nBestList.get(i)).getName();
			}
			return s;
		}
		
		return null;
	}
	
	public String getNamesString() {
		String s = "";
		if (_nBestList.size() > 0) {
			for (int i = 0; i < _nBestList.size(); i++) {
				s += _nBestList.get(i).getName() + ",";
			}
		}
		return ((s.endsWith(",")) ? s.substring(0, s.length() - 1) : s);
	}
	
	/**
	 * Gets the [0..1] matching score of the top result of the NBestList.
	 **/
	public double getScore() {
		if (_nBestList.size() > 0) {
			NBestResult r = (NBestResult) _nBestList.get(0);
			return r.getScore();
		}
		return -1.0;
	}
	
	public double[] getScores() {
		double[] s = new double[_nBestList.size()];
		if (_nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) _nBestList.get(0)).getScore();
			}
		}
		return s;
	}
	
	public String getScoresString() {
		String s = "";
		if (_nBestList.size() > 0) {
			for (int i = 0; i < _nBestList.size(); i++) {
				s += Math.round(_nBestList.get(i).getScore()) + ",";
			}
		}
		return ((s.endsWith(",")) ? s.substring(0, s.length() - 1) : s);
	}
	
	/**
	 * Sorts the NBestResult List in the Descending order of their Scores
	 */
	@SuppressWarnings("unchecked")
	public void sortDescending() {
		Collections.sort((List<NBestResult>) _nBestList);
	}
}
