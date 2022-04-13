package com.lhs.Controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.User;
import com.lhs.Payload.Request.ForgotPasswordEmailRequest;
import com.lhs.Repository.UserRepository;
import com.lhs.SecurityConfig.JwtUtil;
import com.lhs.Service.EmailService;
import com.lhs.Service.UserService;
import com.lhs.Service.Impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/emailForgot")
public class ForgotPasswordEmailController {
	
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
	
	
	@PostMapping("/forgot-password")
	public String forgetPassword(@RequestBody ForgotPasswordEmailRequest  forgotPwdReq, HttpSession session){
		
		Random rondom=new Random();
		
		int otp=rondom.nextInt(9999);
		System.out.println("OTP "+otp);
		
		String subject="Your OTP for resetting LHS Password";
		String message="Please enter below OTP to reset your LHS account password. <h1> OTP "+otp+"<h1>";
		String to=forgotPwdReq.getEmail();
		//User user=userRepository.findByEmail(to);

		boolean flag=emailServcie.sendEmail(subject, message, to);
	
		if(flag) {
			
			session.setAttribute("otp", otp);
			session.setAttribute("email", forgotPwdReq.getEmail());
			System.out.println("otpppp "+otp);
			
			
			return "Otp Sent";
		}
	     else
			return"Please check Email Id !!!";
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam(value="otp",required = false)Integer otp, HttpSession session) {
		
		Integer sessionOtp=(Integer) session.getAttribute("otp");
		String email=(String) session.getAttribute("email");
		System.out.println("sessionOTP  "+sessionOtp);
		System.out.println("email  "+email);
		if(sessionOtp==otp) {

			User user=userRepository.findByEmail(email);
			if(user==null)
				session.setAttribute("message","User does not exist with this this email");
			else
				return "change pwd";
					}else {
						
			
		}
		session.setAttribute("message","Wrong OTP !!!");
		return "";
	}
	
	@PostMapping("/change-forgot-password")
	public User changeForgotPassword(@RequestBody ForgotPasswordEmailRequest forgotPasswordRequest,HttpSession session) {
		String email=(String) session.getAttribute("email");
		System.out.println("email  "+email);
		User user=userRepository.findByEmail(email);
		System.out.println(email);
		user.setPassword(bCryptPasswordEncoder.encode(forgotPasswordRequest.getNewPassword()));
		userRepository.save(user);
		return user;
	}

}
