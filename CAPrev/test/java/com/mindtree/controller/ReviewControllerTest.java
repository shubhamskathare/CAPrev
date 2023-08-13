package com.mindtree.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Date;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.dto.ReviewDto;
import com.mindtree.model.Review;
import com.mindtree.service.ReviewService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value= ReviewController.class)
public class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewservice;

	@Autowired
	private ObjectMapper objectMapper;

	
	String jwt="";
	private static final long EXPIRATIONTIME = 900000;
	@Before
	public void setup() {
		jwt = "Bearer "+Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}




	@Test
	public void insertReviewtest() throws Exception {
		Review review = new Review();
		review.setDescription("good");
		review.setComments("ok");
        review.setScore(1);
        review.setRestaurantId(1L);
        
		when(reviewservice.insertReview(any(Review.class))).thenReturn(new Review());
		RequestBuilder request = MockMvcRequestBuilders.post(
				"/review/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString((review)))
				.header(HttpHeaders.AUTHORIZATION,
						jwt);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andExpect(content().string("Item Added successfully"))
		.andReturn();
	}
	
	@Test
	public void updateOrder() throws Exception{
	
		
		ReviewDto reviewdto = new ReviewDto("good","ok",1,1L);
		when(reviewservice.updateReview(any(ReviewDto.class),1L)).thenReturn(new Review());
		RequestBuilder request = MockMvcRequestBuilders.put(
				"/review/{id}")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(reviewdto))
		        .header(HttpHeaders.AUTHORIZATION,
						jwt);
		mockMvc.perform(request)
		.andExpect(status().is(200))
		.andReturn();

	}}