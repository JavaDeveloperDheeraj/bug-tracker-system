package org.nic.bug_tracker_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BugTrackerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerSystemApplication.class, args);
		System.err.println("Application started");
	}

}
