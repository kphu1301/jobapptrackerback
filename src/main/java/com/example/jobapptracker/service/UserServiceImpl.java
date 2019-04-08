package com.example.jobapptracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jobapptracker.domain.User;
import com.example.jobapptracker.repository.UserRepository;

import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		if (user == null) {
			throw new RuntimeException("User with id " + id + " doesn't exist");
		}
		return user;
	}

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public Long parseUserId(String token) {
		String currentUserId = Jwts.parser()
				.setSigningKey("SecretKey")
				.parseClaimsJws(token.replaceAll("Bearer", ""))
				.getBody().getSubject();
		
		return Long.valueOf(currentUserId);
	}
	
}
