package com.lhs.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lhs.Models.Appointment;
import com.lhs.Repository.AppointmentRepository;


@RestController
@RequestMapping("/appointment")
@CrossOrigin("*")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@GetMapping("/get")
	private List<Appointment> getAppointment()
	{
		return appointmentRepository.findAll();
		
	}
	@GetMapping("/book/{id}")
	private Appointment bookAppointment(@PathVariable("id") Integer Id)
	{
		Optional<Appointment> aptmt = appointmentRepository.findById(Id);
		Appointment appointment = aptmt.get();
		if(appointment.getIsBooked() == 0)
			appointment.setIsBooked(1);
		appointmentRepository.save(appointment);
		return appointment;
	}
	@GetMapping("/unbook/{id}")
	private Appointment unbookAppointment(@PathVariable("id") Integer Id)
	{
		Optional<Appointment> aptmt = appointmentRepository.findById(Id);
		Appointment appointment = aptmt.get();
		if(appointment.getIsBooked() == 1)
			appointment.setIsBooked(0);
		appointmentRepository.save(appointment);
		return appointment;
	}

}
