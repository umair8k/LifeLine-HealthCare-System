package com.lhs.Controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.Doctors;
import com.lhs.Models.Role;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;
import com.lhs.Repository.DoctorsRepository;
import com.lhs.Service.Impl.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger LOG=LoggerFactory.getLogger(AdminController.class);

	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DoctorsRepository doctorRepository;
	
	
	@PostMapping("/addDoctor")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Doctors addDoctor(@RequestBody Doctors doctor) throws Exception {
		LOG.info("Enterd into createUser Method");

		doctor.setDoctorName("Dr."+doctor.getDoctorName());
		doctor.setPassword(this.bCryptPasswordEncoder.encode(doctor.getPassword()));
		LOG.debug("Encrypted password");
		Set<UserRole> userRoleSet=new HashSet<>();

		Role role=new Role();         //default role "User"
		role.setRoleId(33);
		role.setRoleName("DOCTOR");

		UserRole userRole=new UserRole();
		//User user=new User();
		userRole.setRole(role);
		userRole.setDoctors(doctor);
		LOG.debug("Assigned Default role to user to USER");
		userRoleSet.add(userRole);
		return this.adminService.addDoctor(doctor, userRoleSet);
	}
	

}
