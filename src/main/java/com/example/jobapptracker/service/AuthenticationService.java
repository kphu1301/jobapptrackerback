package com.example.jobapptracker.service;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.jobapptracker.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {
	
	// 1 day in ms
	static final long EXPIRATIONTIME = 864_000_00;
	
	static final String SIGNINGKEY = "SecretKey";
	static final String PREFIX = "Bearer";

	//Add token to authorization header
	static public void addToken(HttpServletResponse res, Authentication auth) {
		User currentUser = (User)auth.getPrincipal();
		String currentUserId = currentUser.getId().toString();
		
		String JwtToken = Jwts.builder().setSubject(currentUserId)
				.setExpiration(new Date(System.currentTimeMillis()
						+ EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
				.compact();
		
		res.addHeader("Authorization",  PREFIX + " " + JwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
	}
		
	//get token from authorization header
	static public Authentication getAuthentication(HttpServletRequest req) {
		
		String token = req.getHeader("Authorization");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(SIGNINGKEY)
					.parseClaimsJws(token.replaceAll(PREFIX, ""))
					.getBody()
					.getSubject();
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null,
						emptyList());
			}
		}
		
		return null;
	}
	
}
