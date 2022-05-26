package com.lhs.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.Models.User;

public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);

	public void deleteByUsername(String username);

	public User getUserByEmail(String email);

	public User findByEmail(String email);

	public User findByPhoneNo(String phoneNo);
	
	//public List<User> findByRoleName(String string);
	
	public Page<User> findByRoleName(String string, Pageable pageable);

	//@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = ?1")
    //List<User> findUserByRole(int role);
}
