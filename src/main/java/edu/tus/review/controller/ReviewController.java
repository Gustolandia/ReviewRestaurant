package edu.tus.review.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tus.review.errors.ErrorMessage;
import edu.tus.review.exception.ReviewException;
import edu.tus.review.model.Review;
import edu.tus.review.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<?> getAllReviews(){
		ArrayList<Review> reviews=(ArrayList<Review>) reviewService.getAllReviews();
		if (reviews.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(reviews);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(reviews);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getReviewById(@PathVariable("id") Long id){
		Optional<Review> review= reviewService.getReviewById(id);
		if (review.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(review);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(review);
		}
	}

	@PostMapping
	public ResponseEntity<Object> addReview(@RequestBody Review review) {
		try {
			Review savedReview = reviewService.createReview(review);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
		} catch (ReviewException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable("id") Long id) {
		try {
			reviewService.deleteReview(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
