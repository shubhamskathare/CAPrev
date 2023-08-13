package com.mindtree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mindtree.config.JwtFilter;

@SpringBootApplication
@EnableWebMvc
@EnableEurekaClient

public class ReviewmanagementserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewmanagementserviceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/review/*");
		return registrationBean;
	}
}
