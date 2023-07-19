package Exceptions;

public class DALException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
	}

	public DALException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
