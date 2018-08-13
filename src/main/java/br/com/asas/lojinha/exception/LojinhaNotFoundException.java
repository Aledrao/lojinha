package br.com.asas.lojinha.exception;

public class LojinhaNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5674311049246997399L;
	
	public LojinhaNotFoundException() {
		super();
	}
	
	public LojinhaNotFoundException(String message) {
		super(message);
	}
	
	public LojinhaNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public LojinhaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
