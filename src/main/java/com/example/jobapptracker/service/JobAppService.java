package com.example.jobapptracker.service;

import java.util.List;

import com.example.jobapptracker.domain.JobApp;

public interface JobAppService {

	public List<JobApp> findByUserId(Long userId);
	
	public void save(JobApp jobApp, Long userId);
	
	public void deleteById(Long id);
	
	public JobApp findById(Long jobAppId);
	
}
