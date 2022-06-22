package com.lhs.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.User;
import com.lhs.Payload.Request.UpdateDoctorProfileRequest;
import com.lhs.Service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorServie;

	@PutMapping("/updateDoctorProfile")
	@PreAuthorize("hasAuthority('DOCTOR')or hasAuthority('ADMIN')")
	public User updatePofile(@RequestBody UpdateDoctorProfileRequest profileUpdate, Principal principal) {

		User updatedUser=this.doctorServie.updateProfile(profileUpdate,principal);

		return updatedUser; 

	}

}
