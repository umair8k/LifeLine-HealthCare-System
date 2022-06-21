package com.lhs.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lhs.Models.DoctorDetail;
import com.lhs.Models.User;
import com.lhs.Repository.DoctorDetailRepository;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private DoctorDetailRepository doctorDetailRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<DoctorDetail> getDoctorBySpecilazation(String specialization) {

		return (List<DoctorDetail>) this.doctorDetailRepository.findBySpecialization(specialization);
	}

	@Override
	public Page<User> getAllUser(PageRequest pageRequest, String string) {
		
		return this.userRepository.findByRoleName(string, pageRequest);
	}

	@Override
	public List<User> getAllDoctors(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}
}