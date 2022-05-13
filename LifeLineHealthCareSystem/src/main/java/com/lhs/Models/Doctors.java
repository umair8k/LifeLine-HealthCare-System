package com.lhs.Models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter 
@Getter 
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctors implements UserDetails {
	@Id
	@GeneratedValue(generator = "doctor_gen",strategy = GenerationType.AUTO)
	private int doctorId;
	
	private String username;
	
	private String phoneNo;
	
	private String email;
	
	private String speciality;
	
	private String doctorName;
	
	private int practiceYears;
	
	private String DOB;
	
	private String gender;
	
	private String password;
	
	//user can have many 
		@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="doctors")
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
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}


		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

	
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}




		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}



}
