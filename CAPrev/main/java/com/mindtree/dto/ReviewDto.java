package com.mindtree.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class ReviewDto {

	private String comments;
	private String description;
	private Integer score;
	private Long restaurantId;
	
		
	
}

