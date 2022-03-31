package com.lhs.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.User;
import com.lhs.Payload.Request.JwtRequest;
import com.lhs.Payload.Response.JwtResponse;
import com.lhs.SecurityConfig.JwtUtil;
import com.lhs.Service.Impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;// authenticate method will use this to authenticate 

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		}catch(UsernameNotFoundException unfe) {
			unfe.printStackTrace();
			throw new Exception("User Not Found");
		}

		UserDetails userDetails=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userDetails);// this will give token

		return ResponseEntity.ok(new JwtResponse(token));
	}



	private void authenticate(String username, String password) throws Exception {// this method will auth if auth is not sucessfull then is will throegh excep.
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));//takinf uname and pass wich we have set in jwtfilter class

		}catch(DisabledException de) {
			throw new Exception("USER IS Disabled"+de.getMessage());
		}catch(BadCredentialsException be) {
			throw new Exception("Invalid Credentials" +be.getMessage());

		}
	}
	
	//it will give logged in user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
		return (User) this.userDetailsService.loadUserByUsername(principal.getName());
		
	}

}
