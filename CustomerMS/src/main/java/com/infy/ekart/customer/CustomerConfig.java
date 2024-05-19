package com.infy.ekart.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    
	private RestTemplate template = new RestTemplate();
	
	@Bean
	@LoadBalanced
	//Make this as load-balanced rest template
	public RestTemplate restTemplate() {
		//return template object created above
		return template;
	}
	
}
