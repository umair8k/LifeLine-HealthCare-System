package com.lhs.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lhs.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

	public void deleteByUsername(String username);

	public User getUserByEmail(String email);

	public User findByEmail(String email);

	public User findByPhoneNo(String phoneNo);
}
