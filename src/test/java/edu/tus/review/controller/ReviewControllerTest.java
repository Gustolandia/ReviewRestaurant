package edu.tus.review.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import edu.tus.review.service.ReviewService;
import edu.tus.review.errors.ErrorMessage;
import edu.tus.review.errors.ErrorMessages;
import edu.tus.review.exception.ReviewValidationException;
import edu.tus.review.model.Review;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
	
	@Autowired
	ReviewController reviewController;
	
	@MockBean
	ReviewService reviewService;
	

	@Test
	public void addReviewTestSuccess() throws ReviewValidationException
	{
		Review review = buildReview();
		Review savedReview = buildReview();
		savedReview.setId(1L);
		when(reviewService.createReview(review)).thenReturn(savedReview);
		ResponseEntity<?> response	=reviewController.addReview(review);
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		Review reviewAdded= (Review) response.getBody();
		reviewAdded.getId();
		assertEquals(1L,reviewAdded.getId());
		assertTrue(reviewAdded.equals(savedReview));
	}
	
	@Test
	public void addReviewTestFail() throws ReviewValidationException
	{
		Review review = buildReview();
		Review savedReview = buildReview();
		savedReview.setId(1L);
		when(reviewService.createReview(review)).thenThrow(new ReviewValidationException(ErrorMessages.INVALID_RATING.getMsg()));		
		ResponseEntity<?> response	=reviewController.addReview(review);
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		ErrorMessage errorMsg= (ErrorMessage) response.getBody();
		assertEquals(ErrorMessages.INVALID_RATING.getMsg(),errorMsg.getErrorMessage());
	}
	
	
	
	Review buildReview() {
		Review review = new Review();
		review.setRestaurant("Mercedes");
		review.setUsername("E220");
		review.setRating(2021);
		review.setDescription("GREEN");
		return review;
	}

}
