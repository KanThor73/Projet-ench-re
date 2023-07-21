package Exceptions;

public class IHMException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IHMException() {
		super();
	}

	public IHMException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
