package com.lhs.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationDto;
import com.lhs.service.RegistrationService;

@RestController
@RequestMapping("/apl")
@CrossOrigin
public class RegistrationController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	RegistrationService service;

	@RequestMapping("/")
	public String home() {
		logger.info("checking html page ");
		return "test";
	}

	@RequestMapping("/login")
	public String loginPage() {
		logger.info("login method executed");
		return "login";
	}

	@RequestMapping("/logg")
	public String logout() {
		logger.info("logout called");
		return "logout";
	}

	@PostMapping("/register")
	public ResponseEntity<String> addRegistration( @RequestBody @Valid RegistrationDto register) {

		logger.info("executed add method in controller");
		if (register == null) {
			logger.error("registration object is null");
		}

		service.addAccount(register);
		logger.info("account saved in the database sucessfull");

		return ResponseEntity.ok("added account sucessfully" + register.getUsername());

	}

//	@GetMapping("/get")
//	public Iterable<RegistrationEntity> get() {
//
//		Iterable<RegistrationEntity> entity = registrationRepo.findAll();
//
//		return entity;
//
//	}

	}
