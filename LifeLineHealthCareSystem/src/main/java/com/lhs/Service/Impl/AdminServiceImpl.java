package com.lhs.Service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.Models.Doctors;
import com.lhs.Models.UserRole;
import com.lhs.Repository.DoctorsRepository;
import com.lhs.Repository.RoleRepository;
import com.lhs.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private DoctorsRepository doctorRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Doctors addDoctor(Doctors doctor, Set<UserRole> userRole) throws Exception {
		Doctors local=this.doctorRepository.findByUsername(doctor.getUsername());
		if(local!=null) {
			System.out.println("User already exist with this username!!");
			throw new Exception("user already present with this username");
			//create user
		}
		else {

			for(UserRole ur: userRole) {
				roleRepository.save(ur.getRole());//role save
			}

			doctor.getUserRole().addAll(userRole);//associating roles to user
			local=this.doctorRepository.save(doctor);
		}
		return local;
	}

}
