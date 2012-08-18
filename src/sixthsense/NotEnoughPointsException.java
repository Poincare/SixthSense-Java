package sixthsense;

public class NotEnoughPointsException extends Exception {
	public String getMessage() {
		return "there aren't enough points in the given list";
	}
}
