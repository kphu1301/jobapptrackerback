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
	

}
