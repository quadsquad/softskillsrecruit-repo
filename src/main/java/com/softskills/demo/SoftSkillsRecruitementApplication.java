package com.softskills.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin(origins = "http://localhost:4200")
public class SoftSkillsRecruitementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftSkillsRecruitementApplication.class, args);
	}
	
	@Bean
	@LoadBalanced

	public RestTemplate restTemplate() {
		return new RestTemplate();
		
	}

}
