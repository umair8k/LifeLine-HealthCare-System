package com.lhs.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lhs.Models.User;
import com.lhs.Repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {//spring security will use this class services to load the user from DB
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("user not found!!");
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		return user;
	}

}
