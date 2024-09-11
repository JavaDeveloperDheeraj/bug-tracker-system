package com.bts.nic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugTrackerSystemApplication {
	// Hello This is my app
	public static void main(String[] args) {
		SpringApplication.run(BugTrackerSystemApplication.class, args);
		System.err.println("hello world");
		
	}

}
