package com.lhs.Service;

import java.util.List;
import java.util.Set;

import com.lhs.Models.User;
import com.lhs.Models.UserRole;

public interface UserService {
	
	//creating a new user with roles
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	//finding By username
	public User getUser(String username);
	
	//deleting user by id
	public void deleteUser(Integer id);
	
	//get all users
	public List<User> gellAllUsers();
	
	public void deleteAllUsers();
}
