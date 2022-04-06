package com.lhs.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Exceptions.BuisinessException;
import com.lhs.Exceptions.ControllerException;
import com.lhs.Models.Role;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;
import com.lhs.Service.UserService;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOG=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//create User
	@PostMapping("/signUp")
	public User createUser(@RequestBody User user) throws Exception {
		LOG.info("Enterd into createUser Method");
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();
		
		Role role=new Role();         //default role "User"
		role.setRoleId(11);
		role.setRoleName("USER");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		LOG.debug("Assigned Default role to user to USER");
		userRoleSet.add(userRole);
		return this.userService.createUser(user, userRoleSet);
	}
	
	//get All users
	@GetMapping("/getAllUsers")
	public List<User> showAllUsers(){
		return this.userService.gellAllUsers();
	}
	
	//get user by id
	@GetMapping("/getUser/{username}")
	public ResponseEntity<?> getUser(@PathVariable("username")String username) {
		try {
			User user=userService.getUser(username);
			return new ResponseEntity<User>(user,HttpStatus.OK);	
		}catch(BuisinessException be) {
			ControllerException ce=new ControllerException(be.getErrorCode(),be.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException ce =new ControllerException("610","Somthing went wrong in controller");
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id")Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/deleteAll")
	public void deleteAllUsers() {
		this.userService.deleteAllUsers();
	}
	
}
