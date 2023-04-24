package edu.tus.review.errors;

public enum ErrorMessages {
	INVALID_USERNAME_RESTAURANT("Only one review per user for each restaurant"),
	INVALID_RATING("Cannot accept reviews above 5 or below 0"),
	INVALID_DESCRIPTION("Only accepting descriptions of at least 3 characters and max 500");
	
	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}
	
	public String getMsg(){
		return errorMessage;
	}
}
