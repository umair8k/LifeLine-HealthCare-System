package com.lhs.Service.Impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.Models.User;
import com.lhs.Models.UserRole;
import com.lhs.Repository.RoleRepository;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception{
		
	User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exist!!");
			throw new Exception("user already present");
			//create user
		}else {

			for(UserRole ur: userRole) {
				roleRepository.save(ur.getRole());//role save
			}
			user.getUserRole().addAll(userRole);//associating roles to user
			local=this.userRepository.save(user);
		}
		return local;
	}

	
	//getting user by username
	@Override
	public User getUser(String username) {
		
		return this.userRepository.findByUsername(username);
	}
	
	
    //deleting user by id
	@Override
	public void deleteUser(Integer id) {
		this.userRepository.deleteById(id);		
	}


	@Override
	public List<User> gellAllUsers() {
		
		return this.userRepository.findAll();
	}

}
