package com.example.jobapptracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jobapptracker.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
}
