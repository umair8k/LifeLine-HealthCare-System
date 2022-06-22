package com.lhs.Service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lhs.Models.User;
import com.lhs.Repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {//spring security will use this class services to load the user from DB
	
	private static final Logger LOG=LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.info("Enterd into loadUserByUsername method");
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("user not found!!");
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		return user;
	}
	
	

}
