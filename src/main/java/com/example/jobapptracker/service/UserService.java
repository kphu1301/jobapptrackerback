package com.example.jobapptracker.service;

import com.example.jobapptracker.domain.User;

public interface UserService {

	public User findById(Long id);

	public void save(User user);

	public void deleteById(Long id);
	
	public Long parseUserId(String token);
	
}
