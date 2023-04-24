package edu.tus.review.exception;

public class ReviewNotFoundException extends ReviewException {

	private static final long serialVersionUID = 334051992916748022L;

	public ReviewNotFoundException(final String errorMessage) {
		super(errorMessage);
	}

}

