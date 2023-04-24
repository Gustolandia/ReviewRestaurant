package edu.tus.review.exception;

public abstract class ReviewException extends Exception {

	/**
	 * Create a new exception with an error message.
	 * @param message a String explaining the error which occurred.
	 */
	protected ReviewException(final String message) {
		super(message);
	}

	// needed because Exceptions must be serializable
	private static final long serialVersionUID = 6013983962125460401L;

}