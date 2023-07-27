package Exceptions;

public class BOException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BOException() {
		super();
	}

	public BOException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
