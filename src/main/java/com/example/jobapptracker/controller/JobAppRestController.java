package com.example.jobapptracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobapptracker.domain.JobApp;
import com.example.jobapptracker.service.JobAppService;
import com.example.jobapptracker.service.UserService;

@RestController
public class JobAppRestController {

	@Autowired
	private JobAppService jobAppService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/users/{userId}/jobapps/{jobAppId}")
	public JobApp getJobApp(@PathVariable("userId") Long userId
			, @PathVariable("jobAppId") Long jobAppId
			, @RequestHeader("Authorization") String token) {
		
		Long currentUserId = userService.parseUserId(token);
		
		if (currentUserId != userId) {
			throw new RuntimeException("Not authorized!");
		}
		
		return jobAppService.findById(jobAppId);
		
	}
	
	@GetMapping("/api/users/{userId}/jobapps")
	public List<JobApp> getJobApps(@PathVariable Long userId,
			@RequestHeader("Authorization") String token){
		
		Long currentUserId = userService.parseUserId(token);
		
		if (currentUserId != userId) {
			throw new RuntimeException("Not authorized!");
		}
		
		return jobAppService.findByUserId(currentUserId);
	}
	
	@PostMapping("/api/users/{userId}/jobapps")
	public JobApp addJobApp(@PathVariable Long userId
			, @RequestBody JobApp jobApp
			, @RequestHeader("Authorization") String token) {
		
		Long currentUserId = userService.parseUserId(token);
		
		if (currentUserId != userId) {
			throw new RuntimeException("Not authorized!");
		}
		
		jobAppService.save(jobApp, currentUserId);
		return jobApp;
	}
	
	@DeleteMapping("/api/users/{userId}/jobapps/{jobAppId}")
	public String deleteJobApp(@PathVariable("userId") Long userId 
			, @PathVariable("jobAppId") Long jobAppId
			, @RequestHeader("Authorization") String token) {
		
		Long currentUserId = userService.parseUserId(token);
		
		if (currentUserId != userId) {
			throw new RuntimeException("Not authorized!");
		}
		
		jobAppService.deleteById(jobAppId);
		return "Deleted Job Application with ID: " + jobAppId;
	}
	
	@PutMapping("/api/users/{userId}/jobapps/{jobAppId}")
	public JobApp editJobApp(@PathVariable("userId") Long userId 
			, @PathVariable("jobAppId") Long jobAppId
			, @RequestHeader("Authorization") String token
			, @RequestBody JobApp jobApp) {
		
		Long currentUserId = userService.parseUserId(token);
		
		if (currentUserId != userId) {
			throw new RuntimeException("Not authorized!");
		}
		
		jobApp.setId(jobAppId);
		
		JobApp tempJobApp = jobAppService.findById(jobAppId);
		
		if (tempJobApp.getUser().getId() != currentUserId) {
			throw new RuntimeException("Not Authorized!");
		}

		jobAppService.save(jobApp, userId);
		return jobApp;
	}
}
