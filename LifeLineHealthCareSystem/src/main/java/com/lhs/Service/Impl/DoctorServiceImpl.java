package com.lhs.Service.Impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.Models.DoctorDetail;
import com.lhs.Models.User;
import com.lhs.Payload.Request.UpdateDoctorProfileRequest;
import com.lhs.Repository.DoctorDetailRepository;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.DoctorService;
@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private UserRepository userRepository;
	
	private DoctorDetailRepository doctorDetailRepository;

	@Override
	@Transactional
	public User updateProfile(UpdateDoctorProfileRequest profileUpdate, Principal principal) {
		
		String LoggedInUser=principal.getName();
		User currentUser=this.userRepository.findByUsername(LoggedInUser);
		
		currentUser.setFirstName(profileUpdate.getFirstName());
		currentUser.setLastName(profileUpdate.getLastName());
		currentUser.setEmail(profileUpdate.getEmail());
		currentUser.setPhoneNo(profileUpdate.getPhoneNo());
		DoctorDetail doctorDetail=profileUpdate.getDoctorDetail();
		
		doctorDetail.setUser(currentUser);
		currentUser.setDoctorDetail(doctorDetail);

		
		User updatedUser=userRepository.save(currentUser);
		return updatedUser;
		}
			
}
