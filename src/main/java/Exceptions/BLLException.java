package Exceptions;

public class BLLException extends Exception {

	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}

	public BLLException(String message) {
		super(message);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
