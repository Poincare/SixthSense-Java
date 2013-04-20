package sixthsense;

public class NotEnoughPointsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "there aren't enough points in the given list";
	}
}
