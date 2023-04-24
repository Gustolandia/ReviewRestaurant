package edu.tus.review.exception;

public class ReviewValidationException extends ReviewException {

	private static final long serialVersionUID = 334051992916748022L;

	public ReviewValidationException(final String errorMessage) {
		super(errorMessage);
	}

}

