package com.lhs.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import com.lhs.Models.User;
import com.lhs.Models.UserRole;

public interface UserService {
	
	//creating a new user with roles
	public User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	//finding By username
	public User getUser(String username);
	
	//finding By email
	public User getUserByEmail(String email);
	
	//deleting user by id
	public void deleteUser(String id);
	
	//get all users
	public List<User> gellAllUsers();
	
	public void deleteAllUsers();
	
	public User updateUser(String username, User user, Principal principal);
	 
	public User getUserByPhoneNO(String phoneNo);

	public User findByPhoneNo(String phoneNo);

	List<User> gellAllUsers(String roleName);
	

}
