package com.mindtree.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.dto.Restaurant;
import com.mindtree.dto.RestaurantWithReviews;
import com.mindtree.dto.ReviewDto;
import com.mindtree.exception.ReviewNotFoundException;
import com.mindtree.model.Review;
import com.mindtree.repository.ReviewRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewrepository;

	@Autowired
	private KafkaTemplate<String, Object> kafkatemplate;

	private   String topic = "restaurant-review";
	@Autowired
	private RestTemplate restTemplate;
	
	
	ObjectMapper om = new ObjectMapper();
	@Override
	public Review insertReview(Review review){
		 
		review=reviewrepository.save(review);
	   String reviewStr=null;
	   try {
		reviewStr=om.writeValueAsString(review);
	} catch (JsonProcessingException e) {
	
		e.printStackTrace();
	}
	   kafkatemplate.send(topic,reviewStr);
		return review;
	}
 

	@Override
	public Review updateReview(ReviewDto reviewDto, Long reviewId) {
 		Review review = reviewrepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundException("Review Not Found with id : "+reviewId));
 		review.setComments(reviewDto.getComments());
 		review.setDescription(reviewDto.getDescription());
 		review.setRestaurantId(reviewDto.getRestaurantId());
 		review.setScore(reviewDto.getScore());
 		return reviewrepository.saveAndFlush(review);
	
	}
	@Override
	public void deleteReview(Long reviewId) {
		@SuppressWarnings("unused")
		Review review=reviewrepository.findById(reviewId).orElseThrow(()->new ReviewNotFoundException("Review Not Found with id : "+reviewId));
	 	reviewrepository.deleteById(reviewId);
	}

	@Override
	public RestaurantWithReviews viewReviewsWithRestaurant(Long id) {
		String endPoint = "http://localhost:8084/restaurantservice/"+id;
        Restaurant restaurant = restTemplate.getForObject(endPoint, Restaurant.class);
		List<Review> reviewList = reviewrepository.findAllByRestaurantId(id);
		List<ReviewDto> reviewDtoList = new ArrayList<>();
		for(Review review : reviewList){
			ReviewDto reviewDto = ReviewDto.builder()
					.comments(review.getComments())
					.score(review.getScore())
					.description(review.getDescription())
					.build();
			reviewDtoList.add(reviewDto);
		}
		RestaurantWithReviews restaurantWithReviews = RestaurantWithReviews.builder()
				.reviewDtoList(reviewDtoList)
				.restaurant(restaurant)
				.build();

		return restaurantWithReviews;

	}
	 
	
}
