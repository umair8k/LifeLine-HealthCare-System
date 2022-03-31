package com.lhs.Models;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
@JsonPropertyOrder({"id","username","firstName","lastName","email","phoneNo","DOB","password","gender","status"})
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="first_name")
	@NotEmpty(message = "Please provide your first name")
	private String firstName;

	@Column(name="last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;


	@Column(name="username", nullable=false, unique=true)
	@Length( max = 15)
	private String username;


	private String password;

	@Column(name="email", nullable=false, unique=true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;

	private String phoneNo;

	private Boolean status=true;

	private String DOB;


	@Length( max = 15)
	private String gender;


	//user can have many 
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="user")
	@JsonIgnore
	private Set<UserRole> userRole=new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Authority> set = new HashSet<>();
		this.userRole.forEach(userRole -> {
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}




}
