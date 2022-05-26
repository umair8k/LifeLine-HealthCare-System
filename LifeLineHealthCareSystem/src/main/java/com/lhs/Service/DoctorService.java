package com.lhs.Service;

import java.security.Principal;

import org.springframework.stereotype.Service;

import com.lhs.Models.User;
import com.lhs.Payload.Request.UpdateDoctorProfileRequest;

@Service
public interface DoctorService {

	public User updateProfile(UpdateDoctorProfileRequest profileUpdate, Principal principal);
}
