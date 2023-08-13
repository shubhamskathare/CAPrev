package com.mindtree.service;



import com.mindtree.dto.RestaurantWithReviews;
import com.mindtree.dto.ReviewDto;
import com.mindtree.model.Review;

public interface ReviewService {
     Review insertReview(Review review);
	public Review updateReview(ReviewDto reviewdto, Long reviewId);

	public void deleteReview(Long reviewId);

	RestaurantWithReviews viewReviewsWithRestaurant(Long id);
}
