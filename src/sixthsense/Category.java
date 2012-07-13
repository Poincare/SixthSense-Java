/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

import java.util.ArrayList;

public class Category {
	private String _name;
	private ArrayList<Gesture> _prototypes;
	
	public Category(String name) {
		_name = name;
		_prototypes = null;
	}
	
	public Category(String name, Gesture firstExample) throws Exception {
		_name = name;
		_prototypes = new ArrayList<Gesture>();
		AddExample(firstExample);
	}
	
	public Category(String name, ArrayList<?> examples) throws Exception {
		_name = name;
		_prototypes = new ArrayList<Gesture>(examples.size());
		for (int i = 0; i < examples.size(); i++) {
			Gesture p = (Gesture) examples.get(i);
			AddExample(p);
		}
	}
	
	public String Name() {
		return _name;
	}
	
	public int Examples() {
		return _prototypes.size();
	}
	
	public Gesture getGesture(int i) throws Exception {
		if (_prototypes == null)
			throw new Exception("prototypes not initialised");
		if (0 <= i && i < _prototypes.size()) {
			return (Gesture) _prototypes.get(i);
		}
		else {
			return null;
		}
	}
	
	public void AddExample(Gesture p) throws Exception {
		boolean success = true;
		// first, ensure that p's name is right
		String name = ParseName(p.Name);
		if (name != _name) {
			success = false;
			throw new Exception(
					"Prototype name does not equal the name of the category to which it was added.");
		}
		// second, ensure that it doesn't already exist
		for (int i = 0; i < _prototypes.size(); i++) {
			Gesture p0 = (Gesture) _prototypes.get(i);
			if (p0.Name == p.Name) {
				success = false;
				throw new Exception(
						"Prototype name was added more than once to its category.");
			}
		}
		if (success) {
			_prototypes.add(p);
		}
	}
	
	public static String ParseName(String s) {
		String category = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			if (Character.isDigit(s.charAt(i))) {
				category = s.substring(0, i);
				if (Character.isDigit(s.charAt(i - 1)))
					continue;
				else
					break;
			}
		}
		return category;
	}
}