package com.mindtree.controller;

import com.mindtree.dto.RestaurantWithReviews;
import com.mindtree.dto.ReviewDto;
import com.mindtree.model.Review;
import com.mindtree.service.ReviewService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	
	


	@PostMapping("/add")
	public Review insertReview(@RequestHeader String authorization,@RequestBody Review review) {
		
		return reviewService.insertReview(review);
		 
	}
	
	
	@PutMapping("/{reviewId}")
	public ResponseEntity<Review> updateReview(@RequestHeader String authorization,@RequestBody ReviewDto reviewDto,@PathVariable Long reviewId){
		Review reviewUpdated=reviewService.updateReview(reviewDto,reviewId);
		return ResponseEntity.status(HttpStatus.OK).body(reviewUpdated);
	}
	
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@RequestHeader String authorization,@PathVariable("reviewId") Long reviewId){
		reviewService.deleteReview(reviewId);
		return ResponseEntity.status(HttpStatus.OK).body("Review is deleted with id : "+ reviewId);
	}
 	
	@GetMapping("/{id}")
	public ResponseEntity<RestaurantWithReviews> viewReviewsWithRestaurant(@RequestHeader String authorization,@PathVariable("id") Long id){
		RestaurantWithReviews restaurantWithReviewList = reviewService.viewReviewsWithRestaurant(id);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantWithReviewList);
	}
}
