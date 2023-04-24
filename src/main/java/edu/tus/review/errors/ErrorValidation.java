package edu.tus.review.errors;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.tus.review.dao.ReviewRepository;
import edu.tus.review.model.Review;

@Component
public class ErrorValidation {
	
	@Autowired
	public ReviewRepository reviewRepo;
	
	

	public boolean checkRestaurantAndUsernameNotAllowed(Review review) {
		return reviewRepo.findByRestaurant(review.getRestaurant()).stream().map(x->x.getUsername()).collect(Collectors.toList()).contains(review.getUsername());
	}
	
	public boolean ratingNotOK(Review review) {
		return ((review.getRating()>5)||(review.getRating()<0)); 
	}
	
	public boolean descriptionNotOK(Review review) {
		return (review.getDescription().length()<2||review.getDescription().length()>500); 
	}

}
