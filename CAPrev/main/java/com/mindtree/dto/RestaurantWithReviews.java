package com.mindtree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantWithReviews {

    List<ReviewDto> reviewDtoList;
    Restaurant restaurant;
	
    
    
    
}
