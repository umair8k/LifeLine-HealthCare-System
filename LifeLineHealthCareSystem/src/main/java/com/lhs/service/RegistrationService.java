package com.lhs.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lhs.customexception.GlobalExceptionHandler;
import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationDto;
import com.lhs.entity.RegistrationEntity;

@Service
public class RegistrationService implements RegistrationServiceInterface {

	@Autowired
	RegistrationRepo repo;

	public void addAccount(RegistrationDto register) {
		RegistrationEntity entity = new RegistrationEntity();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(register, entity);

		PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (repo.existsByUsername(entity.getUsername()))

			throw new GlobalExceptionHandler("501", "already exist username found");

		else

			entity.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));

		repo.save(entity);

	}

}
