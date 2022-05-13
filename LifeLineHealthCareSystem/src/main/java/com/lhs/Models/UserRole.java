package com.lhs.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
	
	@Id
	@GeneratedValue(generator = "UserRole_gen",strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_sql")
	private Integer userRoleId;
	
	//users
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Doctors doctors;
	
	@ManyToOne
	private Role role;

}
