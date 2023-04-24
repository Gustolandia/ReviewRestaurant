package edu.tus.review.model;

import javax.persistence.*;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String restaurant;//make
	private String username;//model
	private int rating;//year
	private String description;//color
	
	
	public Review() {}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	
	public String getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
