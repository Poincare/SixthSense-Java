package sixthsense;

public class NotEnoughPointsException extends Exception {
	
	static final long serialVersionUID = 0;
	
	public String getMessage() {
		return "there aren't enough points in the given list";
	}
}
