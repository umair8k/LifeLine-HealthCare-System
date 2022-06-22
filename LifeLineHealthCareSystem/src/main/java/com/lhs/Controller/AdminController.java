package com.lhs.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Exceptions.UserNotRegisterd;
import com.lhs.Models.Role;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;
import com.lhs.Repository.DoctorDetailRepository;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.Impl.AdminServiceImpl;
import com.lhs.Service.Impl.EmailServiceImpl;
import com.lhs.Service.Impl.UserServiceImpl;
@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOG=LoggerFactory.getLogger(AdminController.class);


	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;


	private EmailServiceImpl emailService;

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping("/addDoctor")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User addDoctor(@RequestBody User user) throws Exception {
		LOG.info("Enterd into addDoctor Method");

		user.setFirstName("Dr."+user.getFirstName());
		user.setPassword(this.bCryptPasswordEncoder.encode("LHS1234"));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();

		Role role=new Role();         //default role "User"
		role.setRoleId(33);
		role.setRoleName("DOCTOR");

		user.setRoleName(role.getRoleName());

		UserRole userRole=new UserRole();
		//User user=new User();
		userRole.setRole(role);
		userRole.setUser(user);
		LOG.debug("Assigned role DOCTOR");
		userRoleSet.add(userRole);

		String subject="JOINING CREDENTIALS";
		String message="Hi "+user.getFirstName()+" "+user.getLastName()+", <br><br> Welcome to Life Line Health Care Family."
				+ "<br><br> As you begin your journey with us, we are pleased to share few information & credentials that will go along a long way in your stint with us."
				+ " In case of any query, please reach out to us.<br>"
				+"<br>Username:- "+user.getUsername()+"<br> Password:- LHS1234 <br>"+"Email Id:- "+user.getEmail();
		String to=user.getEmail();

	//	if(userService.createUser(user, userRoleSet)==null) {
			System.out.println("user not registerd");
		//}
		//else {
		emailService.sendEmail(subject, message, to);
	//	}
		return this.userService.createUser(user, userRoleSet);
	}

	@PostMapping("/addNurse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public User addNurse(@RequestBody User user) throws Exception{
		LOG.info("Enterd into addNurse Method");

		user.setPassword(this.bCryptPasswordEncoder.encode("LHS1234"));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();
		Role role=new Role();
		role.setRoleId(44);
		role.setRoleName("NURSE");
		user.setRoleName(role.getRoleName());
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		LOG.debug("Assigned role NURSE");
		userRoleSet.add(userRole);

		String subject="JOINING CREDENTIALS";
		String message="Hi "+user.getFirstName()+" "+user.getLastName()+", <br><br> Welcome to Life Line Health Care Family."
				+ "<br><br> As you begin your journey with us, we are pleased to share few information & credentials that will go along a long way in your stint with us. "
				+ "In case of any query, please reach out to us.<br>"
				+"<br>Username:- "+user.getUsername()+"<br> Password:- LHS1234 <br>"+"Email Id:- "+user.getEmail();
		String to=user.getEmail();
		//if(userService.createUser(user, userRoleSet)==null) {
        	//System.out.println("user not registerd");
        	//throw new UserNotRegisterd("User is not registerd");
        //}
		//else {
			emailService.sendEmail(subject, message, to);
			
	//	}
		return this.userService.createUser(user, userRoleSet);

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/getAllUsers")
	public List<User> getAllUsers(){

		return userRepository.findAll();
	}


	@GetMapping("/getAllDoctors")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, Object>> findAllDoctors(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="5", required=false)Integer pageSize) {
		try {
			Page<User> users = adminService.getAllUser(PageRequest.of(pageNumber, pageSize),"DOCTOR");

			Map<String, Object> response = new HashMap<>();
			List<User> allDoctor=users.getContent();
			response.put("User", allDoctor);
			response.put("currentPage", users.getNumber());
			response.put("totalItems", users.getTotalElements());
			response.put("totalPages", users.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllNurse")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, Object>> findAllNurse(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="5", required=false)Integer pageSize) {
		try {
			Page<User> users = adminService.getAllUser(PageRequest.of(pageNumber, pageSize),"NURSE");

			Map<String, Object> response = new HashMap<>();
			List<User> allNurase=users.getContent();
			response.put("User", allNurase);
			response.put("currentPage", users.getNumber());
			response.put("totalItems", users.getTotalElements());
			response.put("totalPages", users.getTotalPages());


			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllNormalUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, Object>> findAllNormalUsers(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="5", required=false)Integer pageSize) {
		try {
			Page<User> users = adminService.getAllUser(PageRequest.of(pageNumber, pageSize),"USER");
			List<User> allUsers=users.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("User", allUsers);
			response.put("currentPage", users.getNumber());
			response.put("totalItems", users.getTotalElements());
			response.put("totalPages", users.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
