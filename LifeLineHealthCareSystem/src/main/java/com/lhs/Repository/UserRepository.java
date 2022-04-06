package com.lhs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.Models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

	public void deleteByUsername(String username);
}
