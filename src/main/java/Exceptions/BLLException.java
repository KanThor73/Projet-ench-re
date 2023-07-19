package Exceptions;

public class BLLException extends Exception {

	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(String message, Throwable e) {
		super(message, e);
	}

	public String getMessage() {
		return "BLLException - " + super.getMessage();
	}
	
	public String getSuperMessage() {
		return super.getMessage();
	}
}
