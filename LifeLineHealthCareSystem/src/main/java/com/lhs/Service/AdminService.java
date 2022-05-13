package com.lhs.Service;

import java.util.Set;

import com.lhs.Models.Doctors;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;

public interface AdminService {

	public Doctors addDoctor(Doctors doctor, Set<UserRole> userRole) throws Exception;
}
