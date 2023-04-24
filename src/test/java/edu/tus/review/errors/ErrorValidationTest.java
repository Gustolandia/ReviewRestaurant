package edu.tus.review.errors;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.tus.review.model.Review;

class ErrorValidationTest {
	public ErrorValidation errorValidation;
	Review review;

	@BeforeEach
	void setUp() {
		errorValidation = new ErrorValidation();
		review = buildReview();
	}

//	@Test
//	void testValidCheckRestaurantAndUsernameNotAllowed() {
//		assertFalse(errorValidation.checkRestaurantAndUsernameNotAllowed(review));
//	}
//	@Test
//	void testInvalidCheckRestaurantAndUsernameNotAllowed() {
//		review.setRestaurant("Thai King");
//		review.setUsername("Kerry");
//		assertTrue(errorValidation.checkRestaurantAndUsernameNotAllowed(review));
//	}
	

	@Test
	void testValidRatingNotOK() {
		review.setRating(2019);
		assertTrue(errorValidation.ratingNotOK(review));
	}

	@Test
	void tesInvalidRatingNotOK() {
		assertFalse(errorValidation.ratingNotOK(review));
	}


	@Test
	void testValidDescriptionNotOK() {
		review.setDescription(".");
		assertTrue(errorValidation.descriptionNotOK(review));
	}

	@Test
	void testInvalidDescriptionNotOK() {
		assertFalse(errorValidation.descriptionNotOK(review));
	}


	Review buildReview() {
		Review review = new Review();
		review.setRestaurant("Burger King");
		review.setUsername("Alice");
		review.setRating(3);
		review.setDescription("Decent Burger");
		return review;
	}

}

