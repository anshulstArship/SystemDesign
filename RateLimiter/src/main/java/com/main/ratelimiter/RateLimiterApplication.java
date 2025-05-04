package com.main.ratelimiter;

import com.main.service.FixedWindow;
import com.main.service.LeakingBucket;
import com.main.service.TokenBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


@SpringBootApplication
public class RateLimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateLimiterApplication.class, args);
		HashMap<String, TokenBucket> userMap = new HashMap<>();
		userMap.putIfAbsent("UserA",new TokenBucket(5,1));
		userMap.putIfAbsent("UserB",new TokenBucket(5,1));
		//System.out.println(userMap.get("UserA").allowedRequests("UserA"));

		LeakingBucket user1 = new LeakingBucket(3,1);
		//System.out.println(user1.allowedRequests("A"));

		FixedWindow fixedWindow = new FixedWindow(2,1);
		System.out.println(fixedWindow.allowedRequest("A"));
		System.out.println(fixedWindow.allowedRequest("A"));
		System.out.println(fixedWindow.allowedRequest("B"));
		System.out.println(fixedWindow.allowedRequest("B"));
		System.out.println(fixedWindow.allowedRequest("B"));


	}

}
