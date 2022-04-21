package com.lhs.Controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.User;
import com.lhs.Payload.Request.ForgotPasswordSmsRequest;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.SmsSender;
import com.lhs.Service.UserService;

@RestController
@RequestMapping("/smsForgot")
public class ForgotPasswordSmsController {
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SmsSender smsSender;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/forgot-password")
	public String forgetPassword(@RequestBody ForgotPasswordSmsRequest forgotPwdReq, HttpSession session){
		
		Random rondom=new Random();
		
		int otp=rondom.nextInt(9999);
		System.out.println("OTP "+otp);
		
		String message="Please enter below OTP to reset your LHS account password. <h1> OTP "+otp+"<h1>";
		String to=forgotPwdReq.getPhoneNo();
		//User user=userRepository.findByEmail(to);

		boolean flag=smsSender.sendSms(to, message);
		if(flag) {
			
			session.setAttribute("otp", otp);
			session.setAttribute("phoneNo", forgotPwdReq.getPhoneNo());
			System.out.println("otpppp "+otp);
			
			
			return "Otp Sent";
		}
	     else
			return"Please check Email Id !!!";
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam(value="otp",required = false)Integer otp, HttpSession session) {
		
		Integer sessionOtp=(Integer) session.getAttribute("otp");
		String phoneNo=(String) session.getAttribute("phoneNo");
		System.out.println("sessionOtp  "+sessionOtp);
		System.out.println("phoneNo  "+phoneNo);
		if(sessionOtp==otp) {

			User user=userRepository.findByPhoneNo(phoneNo);
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
	public ResponseEntity<User> changeForgotPassword(@RequestBody ForgotPasswordSmsRequest forgotPwdReq,HttpSession session) {
		String phoneNo=(String) session.getAttribute("phoneNo");
		System.out.println("phoneNo  "+phoneNo);
		User user=userService.findByPhoneNo(phoneNo);
		System.out.println(user);
		System.out.println(phoneNo);
		user.setPassword(bCryptPasswordEncoder.encode(forgotPwdReq.getNewPassword()));
		userRepository.save(user);
		if(user==null)
			return new ResponseEntity("User not found",HttpStatus.BAD_REQUEST);
			else {
		System.out.println(user);
		return  new ResponseEntity(user,HttpStatus.OK);	
			}
	}

}
