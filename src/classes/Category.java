/**
 * Authors: Aroop Ganguly
 */
package classes;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<Gesture> prototypes;
	
	public Category(String name) {
		this.name = name;
		prototypes = null;
	}
	
	public Category(String name, Gesture firstExample) throws Exception {
		this.name = name;
		prototypes = new ArrayList<Gesture>();
		addExample(firstExample);
	}
	
	public Category(String name, List<?> examples) throws Exception {
		this.name = name;
		prototypes = new ArrayList<Gesture>(examples.size());
		for (int i = 0; i < examples.size(); i++) {
			Gesture p = (Gesture) examples.get(i);
			addExample(p);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getExamples() {
		return prototypes.size();
	}
	
	public Gesture getGesture(int i) throws Exception {
		if (prototypes == null) {
			throw new Exception("prototypes not initialised");
		}
		if (0 <= i && i < prototypes.size()) {
			return (Gesture) prototypes.get(i);
		}
		else {
			throw new Exception("prototypes not initialized");
		}
	}
	
	public final void addExample(Gesture p) throws Exception {
		// first, ensure that p's name is right
		String nameStr = parseName(p.getName());
		if (!this.name.equals(nameStr)) {
			throw new Exception(
					"Prototype name does not equal the name of the category to which it was added.");
		}
		// second, check if prototypes exist or not
		if (prototypes == null) {
			prototypes = new ArrayList<Gesture>();
			prototypes.add(p);
			return;
		}
		else {
			// third ensure that this gesture doesn't already exist in the
			// prototypes
			for (int i = 0; i < prototypes.size(); i++) {
				Gesture p0 = (Gesture) prototypes.get(i);
				if (p0.getName() == p.getName()) {
					throw new Exception(
							"Prototype name was added more than once to its category.");
				}
			}
		}
		// if the protoypes were not null and this gesture did not exist until
		// now then add the gesture
		prototypes.add(p);
	}
	
	public static String parseName(String s) {
		//TODO: Verify this line below
		String category = s;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (Character.isDigit(s.charAt(i))) {
				category = s.substring(0, i);
				if (Character.isDigit(s.charAt(i - 1))) {
					continue;
				}
				else {
					break;
				}
			}
		}
		return category;
	}
}
