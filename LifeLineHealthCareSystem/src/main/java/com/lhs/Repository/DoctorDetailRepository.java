package com.lhs.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.Models.DoctorDetail;
import com.lhs.Models.User;
import com.lhs.Models.UserRole;
@Repository
public interface DoctorDetailRepository extends JpaRepository<DoctorDetail, Integer> {

	public DoctorDetail findBySpecialization(String specialization);

	public Set<DoctorDetail> findByspecialization(String string);

}
