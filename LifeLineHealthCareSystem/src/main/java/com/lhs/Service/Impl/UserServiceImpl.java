package com.lhs.Service.Impl;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lhs.Exceptions.BuisinessException;
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception{

		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User already exist with this username!!");
			throw new Exception("user already present with this username");
			//create user
		}
		else {

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
		try {
			
			return this.userRepository.findByUsername(username);
			
		}catch(IllegalArgumentException iae) {
			throw new BuisinessException("601","Given username is null, please send some username to be serched !!!"+iae.getMessage());
		}catch(NoSuchElementException nsee) {
			throw new BuisinessException("602","Given username does not exist in database"+nsee.getMessage());
		}catch(Exception e) {
			throw new BuisinessException("603","Something went wrong in service layer while fetching user by username"+e.getMessage());
		}
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

	@Override
	public void deleteAllUsers() {
		try {
		this.userRepository.deleteAll();
		}catch(IllegalArgumentException iae) {
			throw new BuisinessException("615","Given id is null, Pleace give id that is in database"+iae.getMessage());
		}catch(Exception e) {
			throw new BuisinessException("615","Somthing went wrong in service while deleting users by id"+e.getMessage());
		}

	}
	@Override
	public User updateUser(String username, User user, Principal principal){
		
		String LoggedInUser=principal.getName();
		User currentUser=this.userRepository.findByUsername(LoggedInUser);
		
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPhoneNo(user.getPhoneNo());
		currentUser.setUsername(user.getUsername());
		User updatedUser=userRepository.save(currentUser);
		return updatedUser;
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
