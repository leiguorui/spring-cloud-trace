package com.springboot.zipkin.demo.springbootzipkin.greet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.springboot.zipkin.demo.springbootzipkin.greet")
public class UserGreetingService {

	private static final Logger log = Logger.getLogger(UserGreetingService.class);

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping("/api/user/greet")
	public String greet(HttpServletRequest request) {
		
		String greetingMsg = "Hello";

		log.info("hello");

		String userAddress = restTemplate.getForObject("http://user-name-service/api/user/name", String.class);
		
		return greetingMsg + " "  + "!\n\n" + userAddress;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserGreetingService.class,
				"--spring.application.name=user-greeting-service",
				"--server.port=3000"
		);
	}
}
