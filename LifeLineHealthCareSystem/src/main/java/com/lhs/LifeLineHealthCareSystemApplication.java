package com.lhs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lhs.Models.Role;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;
import com.lhs.Service.UserService;

@SpringBootApplication
public class LifeLineHealthCareSystemApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LifeLineHealthCareSystemApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception{
	/*	System.out.println("execution start");
		
		User user=new User();
		
		user.setEmail("user1@gmail.com");
		user.setFirstName("test");
		user.setLastName("user");
		user.setDOB("01-06-1997");
		user.setPhoneNo("7350957167");
		user.setPassword(bCryptPasswordEncoder.encode("user1234"));
		user.setUsername("tuser1");
		
		Role role=new Role();
		role.setRoleId(11);
		role.setRoleName("USER");
		
		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1=this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());
		
		*/
		
		
	}
}
