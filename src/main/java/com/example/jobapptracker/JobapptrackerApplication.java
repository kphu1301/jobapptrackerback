package com.example.jobapptracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.jobapptracker.domain.JobApp;
import com.example.jobapptracker.domain.User;
import com.example.jobapptracker.repository.UserRepository;

@SpringBootApplication
public class JobapptrackerApplication {
	
	@Autowired UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(JobapptrackerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			User tempUser = new User("user1"
					, "$2a$04$Pzi.Dm77LeFMrsMtjxej8OOSz8ibNnTZZ25RWUuSMyqljPd1trRFu"
					, "user1@gmail.com");
			JobApp tempJobApp = new JobApp("Google", "Software Engineer", "http://www.google.com/jobPostingLink", "Greg Jones", "greg@google.com", "In Progress");
			tempUser.addJobApp(tempJobApp);
			
			tempJobApp = new JobApp("Amazon", "Software Engineer", "http://www.amazon.com/jobPostingLink", "James Carter", "james@amazon.com", "Phone Interview");
			tempUser.addJobApp(tempJobApp);
			
			tempJobApp = new JobApp("Microsoft", "Software Engineer", "http://www.microsoft.com/jobPostingLink", "Sammy Smith", "sammy@microsoft.com", "Declined");
			tempUser.addJobApp(tempJobApp);
			
			tempJobApp = new JobApp("Valve", "Software Engineer", "http://www.valve.com/jobPostingLink", "Tiffany Smalls", "tiffany@valve.com", "Offer Received");
			tempUser.addJobApp(tempJobApp);
			userRepository.save(tempUser);
			
			User tempUser2 = new User("user2",
					"$2a$04$1EBCTfcv9jQo6dUbRJ.HZufJx9FpI4WRgVZo80si1nZwDqrIkG9gu"
					, "user2@gmail.com");
			JobApp tempJobApp2 = new JobApp("Google", "Software Engineer", "http://www.google.com/jobPostingLink", "Greg Jones", "greg@google.com", "In Progress");
			tempUser2.addJobApp(tempJobApp2);
			
			tempJobApp2 = new JobApp("Amazon", "Software Engineer", "http://www.amazon.com/jobPostingLink", "James Jones", "james@google.com", "In Progress");
			tempUser2.addJobApp(tempJobApp2);
			
			userRepository.save(tempUser2);
			
		};
	}
}
