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

	public Category(String name, Gesture firstExample) {
		_name = name;
		_prototypes = new ArrayList<Gesture>();
		AddExample(firstExample);
	}

	public Category(String name, ArrayList<?> examples) {
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

	public Gesture getGesture(int i) {
		if (0 <= i && i < _prototypes.size()) {
			return (Gesture) _prototypes.get(i);
		}
		else {
			return null;
		}
	}

	public void AddExample(Gesture p) {
		boolean success = true;
		try {
			// first, ensure that p's name is right
			String name = ParseName(p.Name);
			if (name != _name)
				throw new Exception(
						"Prototype name does not equal the name of the category to which it was added.");

			// second, ensure that it doesn't already exist
			for (int i = 0; i < _prototypes.size(); i++) {
				Gesture p0 = (Gesture) _prototypes.get(i);
				if (p0.Name == p.Name)
					throw new Exception(
							"Prototype name was added more than once to its category.");
			}
		}
		catch (Exception ex) {
			System.console().writer().write(ex.getMessage());
			success = false;
		}
		if (success) {
			_prototypes.add(p);
		}
	}

	public static String ParseName(String s) {
		String category = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			if (!Character.isDigit(s.charAt(i))) {
				category = s.substring(0, i + 1);
				break;
			}
		}
		return category;
	}
}