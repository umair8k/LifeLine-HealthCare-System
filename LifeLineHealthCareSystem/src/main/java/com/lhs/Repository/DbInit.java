package com.lhs.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.lhs.Models.Appointment;

@Service
public class DbInit implements CommandLineRunner {
	
	private AppointmentRepository appointmentRepository;
	
	public DbInit(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	@Override
	public void run(String... args)
	{
		LocalDate date1 = LocalDate.of(2022, 05, 12); 
		Appointment apmt1 = new Appointment(1, 101,201,date1, "10:30", 0);
		date1 = LocalDate.of(2022, 05, 12); 
		Appointment apmt2 = new Appointment(2, 101,201,date1, "11:00", 0);
		date1 = LocalDate.of(2022, 05, 13); 
		Appointment apmt3 = new Appointment(3, 101,201,date1, "10:00", 0);
		
		appointmentRepository.save(apmt1);
		appointmentRepository.save(apmt2);
		appointmentRepository.save(apmt3);
		//List<Appointment> appointments = Arrays.asList(apmt1, apmt2, apmt3);
		//appointmentRepository.saveAll(appointments);
	}

}
