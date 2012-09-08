package sixthsense;

public class NBestListEmptyException extends Exception {
	
	static final long serialVersionUID = 0;
	
	public String getMessage() {
		return "NBestList passed is empty";
	}
}
