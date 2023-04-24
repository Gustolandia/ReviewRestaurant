package edu.tus.review.service;

import edu.tus.review.dao.ReviewRepository;
import edu.tus.review.model.Review;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tus.review.errors.ErrorMessages;
import edu.tus.review.errors.ErrorValidation;
import edu.tus.review.exception.ReviewNotFoundException;
import edu.tus.review.exception.ReviewValidationException;

@Service
public class ReviewService {
	Review review;

	@Autowired
	ErrorValidation errorValidation;

	@Autowired
	ReviewRepository reviewRepo;
	
	public List<Review> getAllReviews(){
		return reviewRepo.findAll();
	}
	
	public Optional<Review> getReviewById(Long id){
		return reviewRepo.findById(id);
	}
	
	public List<Review> getReviewByRestaurant(String restaurant){
		return reviewRepo.findByRestaurant(restaurant);
	}



	public Review createReview(Review review) throws ReviewValidationException {
		this.review = review;
		if (errorValidation.checkRestaurantAndUsernameNotAllowed(review)) {
			throw new ReviewValidationException(ErrorMessages.INVALID_USERNAME_RESTAURANT.getMsg());
		}

		if (errorValidation.ratingNotOK(review)) {
			throw new ReviewValidationException(ErrorMessages.INVALID_RATING.getMsg());
		}
		if (errorValidation.descriptionNotOK(review)) {
			throw new ReviewValidationException(ErrorMessages.INVALID_DESCRIPTION.getMsg());
		}
		return reviewRepo.save(review);
	}
	
	public void deleteReview(Long id) throws ReviewNotFoundException {
		try {
			Review review = reviewRepo.findById(id).get();
			reviewRepo.delete(review);
		}catch(Exception e) {
			throw new ReviewNotFoundException("Review Not Found");
		}
	}

}
