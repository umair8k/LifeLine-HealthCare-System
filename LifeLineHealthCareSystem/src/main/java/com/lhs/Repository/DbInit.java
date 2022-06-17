package com.lhs.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.lhs.Models.Appointment;
import com.lhs.Models.TimeSlot;

@Service
public class DbInit implements CommandLineRunner {
	
	private AppointmentRepository appointmentRepository;
	private TimeSlotRepository timeSlotRepository;
	
	public DbInit(AppointmentRepository appointmentRepository, TimeSlotRepository timeSlotRepository) {
		this.appointmentRepository = appointmentRepository;
		this.timeSlotRepository = timeSlotRepository;
	}
	
	@Override
	public void run(String... args)
	{	
		LocalDate date1 = LocalDate.of(2022, 05, 12); 
		Appointment apmt1 = new Appointment(1, 101,201,date1);//,  new ArrayList<>("10:00", "10:30"));
		appointmentRepository.save(apmt1);
		
		TimeSlot timeSlot1 = new TimeSlot(1, "10:00",0, apmt1);
		timeSlotRepository.save(timeSlot1);
		
		TimeSlot timeSlot11 = new TimeSlot(2, "11:00",0, apmt1);
		timeSlotRepository.save(timeSlot11);
		
		//apmt1.setTimeSlot(timeSlot1);
		//appointmentRepository.save(apmt1);
		
		
		date1 = LocalDate.of(2022, 05, 12); 
		Appointment apmt2 = new Appointment(2, 102,201,date1);
		appointmentRepository.save(apmt2);
		TimeSlot timeSlot2 = new TimeSlot(3, "10:15",0, apmt2);
		timeSlotRepository.save(timeSlot2);
		
		date1 = LocalDate.of(2022, 05, 13); 
		Appointment apmt3 = new Appointment(3, 101,201,date1);
		appointmentRepository.save(apmt3);
		TimeSlot timeSlot3 = new TimeSlot(4, "11:15", 0, apmt3);
		timeSlotRepository.save(timeSlot3);
		
	}

}
