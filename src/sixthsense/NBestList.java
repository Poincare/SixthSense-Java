/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NBestList {
	public static final NBestList EMPTY = new NBestList();
	private List<NBestResult> nBestList;
	
	public NBestList() {
		nBestList = new ArrayList<NBestResult>();
	}
	
	public void addResult(String name, double score, double distance,
			double angle) {
		NBestResult r = new NBestResult(name, score, distance, angle);
		nBestList.add(r);
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double getAngle() {
		if (nBestList.size() > 0) {
			NBestResult r = (NBestResult) nBestList.get(0);
			return r.getAngle();
		}
		return 0.0;
	}
	
	/**
	 * Gets the average pixel distance of the top result of the NBestList.
	 **/
	public double getDistance(){
		if (nBestList.size() > 0) {
			NBestResult r = (NBestResult) nBestList.get(0);
			return r.getDistance();
		}
		
		return 0;
	}
	
	/**
	 * acts as an indexer for the NBestList, and returns a Result at a specified
	 * index
	 */
	public NBestResult get(int index) {
		if (0 <= index && index < nBestList.size()) {
			return (NBestResult) nBestList.get(index);
		}
		
		return null;
	}
	
	public boolean isEmpty() {
		return nBestList.size() == 0;
	}
	
	/**
	 * Gets the gesture name of the top result of the NBestList.
	 **/
	public String getName() {
		if (nBestList.size() > 0) {
			NBestResult r = (NBestResult) nBestList.get(0);
			return r.getName();
		}
		return "";
	}
	
	public String[] getNames() {
		String[] s = new String[nBestList.size()];
		if (nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) nBestList.get(i)).getName();
			}
			return s;
		}
		
		return null;
	}
	
	public String getNamesString() {
		String s = "";
		if (nBestList.size() > 0) {
			for (int i = 0; i < nBestList.size(); i++) {
				s += nBestList.get(i).getName() + ",";
			}
		}
		return ((s.endsWith(",")) ? s.substring(0, s.length() - 1) : s);
	}
	
	/**
	 * Gets the [0..1] matching score of the top result of the NBestList.
	 **/
	public double getScore() {
		if (nBestList.size() > 0) {
			NBestResult r = (NBestResult) nBestList.get(0);
			return r.getScore();
		}
		return -1.0;
	}
	
	public double[] getScores() {
		double[] s = new double[nBestList.size()];
		if (nBestList.size() > 0) {
			for (int i = 0; i < s.length; i++) {
				s[i] = ((NBestResult) nBestList.get(0)).getScore();
			}
		}
		return s;
	}
	
	public String getScoresString() {
		String s = "";
		if (nBestList.size() > 0) {
			for (int i = 0; i < nBestList.size(); i++) {
				s += Math.round(nBestList.get(i).getScore()) + ",";
			}
		}
		return ((s.endsWith(",")) ? s.substring(0, s.length() - 1) : s);
	}
	
	/**
	 * Sorts the NBestResult List in the Descending order of their Scores
	 */
	@SuppressWarnings("unchecked")
	public void sortDescending() {
		Collections.sort((List<NBestResult>) nBestList);
	}
}
