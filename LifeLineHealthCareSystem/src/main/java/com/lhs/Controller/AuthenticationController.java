package com.lhs.Controller;

import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.User;
import com.lhs.Payload.Request.ForgotPasswordEmailRequest;
import com.lhs.Payload.Request.JwtRequest;
import com.lhs.Payload.Response.JwtResponse;
import com.lhs.Repository.UserRepository;
import com.lhs.SecurityConfig.JwtUtil;
import com.lhs.Service.EmailService;
import com.lhs.Service.UserService;
import com.lhs.Service.Impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

	private static final Logger LOG=LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;// authenticate method will use this to authenticate 

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailServcie;

	@PostMapping("/login")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		LOG.info("Enterd into generateToken method");
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		}catch(UsernameNotFoundException unfe) {
			LOG.debug("Unable to login, User Not Found{}",unfe.getMessage());
			unfe.printStackTrace();
			throw new Exception("User Not Found");
		}

		UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);// this will give token

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {// this method will auth if auth is not sucessfull then is will throegh excep.
		LOG.info("Ented Into authenticate method");
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));//takinf uname and pass wich we have set in jwtfilter class

		}catch(DisabledException de) {
			LOG.debug("User Is Disabled due to this {}",de.getMessage());
			throw new Exception("USER IS Disabled"+de.getMessage());
		}catch(BadCredentialsException be) {
			LOG.debug("Invalid Credentials due to this {}",be.getMessage());
			throw new Exception("Invalid Credentials" +be.getMessage());

		}
	}

	//it will give logged in user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {

		return (User)userDetailsService.loadUserByUsername(principal.getName());
	}
	

}
