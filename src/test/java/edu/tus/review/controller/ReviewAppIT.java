package edu.tus.review.controller;


import edu.tus.review.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewAppIT {
	
	@Value(value="${local.server.port}")
	private int port;
	
	TestRestTemplate restTemplate;
	HttpHeaders headers;
	
	@BeforeEach
	public void setup() {
		restTemplate =new TestRestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@Test
	@Sql({"/testdata.sql"})
	public void addReviewSuccessIntTest()
	{
		
		HttpEntity<Review> request = new HttpEntity<Review>(buildReview(),headers);
		ResponseEntity<String>	response =	restTemplate.postForEntity("http://localhost:"+port+"/api/reviews", request, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	@Sql({"/testdata.sql"})
	public void addReviewInvalidRestaurantUsernameTest()
	{
		Review review=buildReview();
		review.setRestaurant("Thai King");
		review.setUsername("Kerry");
		HttpEntity<Review> request = new HttpEntity<Review>(review,headers);
		ResponseEntity<String> response =	restTemplate.postForEntity("http://localhost:"+port+"/api/reviews", request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

		
	}
	
	
	@Test
	@Sql({"/testdata.sql"})
	public void addReviewInvalidRatingTest()
	{
		Review review=buildReview();
		review.setRating(2019);
		HttpEntity<Review> request = new HttpEntity<Review>(review,headers);
		ResponseEntity<String> response =	restTemplate.postForEntity("http://localhost:"+port+"/api/reviews", request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}
	@Test
	@Sql({"/testdata.sql"})
	public void addReviewInvalidDescriptionTest()
	{
		Review review=buildReview();
		review.setDescription(".");
		HttpEntity<Review> request = new HttpEntity<Review>(review,headers);
		ResponseEntity<String> response =	restTemplate.postForEntity("http://localhost:"+port+"/api/reviews", request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	}
	@Test
	public void getReviewSuccessIntTest()
	{
		
		ResponseEntity<String>	response =	restTemplate.getForEntity("http://localhost:"+port+"/api/reviews", String.class);
		assertTrue(((HttpStatus.OK).equals(response.getStatusCode()))||((HttpStatus.NO_CONTENT).equals(response.getStatusCode())));
	}
	
	@Test
	public void getReviewByIdSuccessIntTest()
	{
		ResponseEntity<String>	response =	restTemplate.getForEntity("http://localhost:"+port+"/api/reviews/2", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void getReviewByIdInvalidIntTest()
	{
		
		ResponseEntity<String>	response =	restTemplate.getForEntity("http://localhost:"+port+"/api/reviews/8", String.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	
	
	Review buildReview() {
		Review review = new Review();
		review.setRestaurant("Burger King");
		review.setUsername("Mary");
		review.setRating(3);
		review.setDescription("Decent Burger");
		return review;
	}

}

