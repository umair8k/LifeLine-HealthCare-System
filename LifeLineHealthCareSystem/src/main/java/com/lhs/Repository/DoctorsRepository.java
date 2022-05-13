package com.lhs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.Models.Doctors;
@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Integer>{

	Doctors findByUsername(String username);

}
