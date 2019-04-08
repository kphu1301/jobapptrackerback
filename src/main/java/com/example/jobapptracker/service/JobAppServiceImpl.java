package com.example.jobapptracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobapptracker.domain.JobApp;
import com.example.jobapptracker.domain.User;
import com.example.jobapptracker.repository.JobAppRepository;

@Service
public class JobAppServiceImpl implements JobAppService {

	@Autowired
	private JobAppRepository jobAppRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<JobApp> findByUserId(Long userId){
		
		return jobAppRepository.findByUserId(userId);
		
	}
	
	@Override
	public void save(JobApp jobApp, Long userId) {
		User user = userService.findById(userId);
		jobApp.setUser(user);
		jobAppRepository.save(jobApp);
	}
	
	@Override
	public void deleteById(Long id) {
		JobApp jobApp = jobAppRepository.findById(id).orElse(null);
		
		if (jobApp == null) {
			throw new RuntimeException("No Job Application with ID: " + id + " exists!");
		}
		
		jobAppRepository.deleteById(id);
	}

	@Override
	public JobApp findById(Long jobAppId) {
		JobApp jobApp = jobAppRepository.findById(jobAppId).orElse(null);
		
		if (jobApp == null) {
			throw new RuntimeException("Job Application with id " + jobAppId + " doesn't exist");
		}
		
		return jobApp;
	}
	
}
