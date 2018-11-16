package com.springboot.zipkin.demo.springbootzipkin.v2;

import com.springboot.zipkin.demo.springbootzipkin.greet.UserGreetingService;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@ServletComponentScan(basePackages = "com.springboot.zipkin.demo.springbootzipkin.v2")
public class UserNameV2Service {
	private static final Logger log = Logger.getLogger(UserGreetingService.class);

	@RequestMapping("/api/user/name")
	public String getUserName(HttpServletRequest request) {

		for (String param : Collections.list(request.getHeaderNames())){
			log.info(param + "-----" + request.getHeader(param));
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "John Smith222222222";
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserNameV2Service.class,
				"--eureka.instance.metadata-map.HDF-test-version=v2",
				"--spring.application.name=user-name-service",
				"--server.port=3004"
		);
	}

}
