package classes;

public class NBestListEmptyException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "NBestList passed is empty";
	}
}
