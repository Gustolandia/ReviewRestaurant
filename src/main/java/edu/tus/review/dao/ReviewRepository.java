package edu.tus.review.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.tus.review.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	//List<Review> findbyRestaurant(String Restaurant);
	List<Review> findByRestaurantContaining(String Restaurant); //Part of the name
	
	List<Review> findByRestaurant(String Restaurant); 

	//@Query("SELECT t FROM Tutorial t WHERE t.level BETWEEN ?1 AND ?2")
	//List<Review> findByLevelBetween(int start, int end);
	//https://www.bezkoder.com/spring-jpa-query/
	
	@Query("SELECT c FROM Review c WHERE rating >=?1 and rating <=?2")
	List<Review> findByRatingBetween(int start, int end);
	

}
