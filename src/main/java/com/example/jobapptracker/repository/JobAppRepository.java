package com.example.jobapptracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jobapptracker.domain.JobApp;

public interface JobAppRepository extends CrudRepository<JobApp, Long> {
	
	public List<JobApp> findByUserId(Long userId);

}
