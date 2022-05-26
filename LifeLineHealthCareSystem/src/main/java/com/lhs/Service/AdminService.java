package com.lhs.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.lhs.Models.DoctorDetail;
import com.lhs.Models.User;


public interface AdminService {

	public List<DoctorDetail> getDoctorBySpecilazation(String specialization);

	List<User> getAllDoctors(String roleName);
	
	//public List<User> getAllUser(Integer pageNumber, Integer pageSize, String string);

	Page<User> getAllUser(PageRequest pageRequest, String string);
}
