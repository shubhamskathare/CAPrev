package com.mindtree.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor



public class Restaurant {

	private Long id;
	private String name;
	private String location;
	private String cuisine;
	private int budget;
	private double rating;

	public Restaurant(String name, String location, String cuisine, int budget, double rating) {
		super();
		this.name = name;
		this.location = location;
		this.cuisine = cuisine;
		this.budget = budget;
		this.rating = rating;
	}
	
}
