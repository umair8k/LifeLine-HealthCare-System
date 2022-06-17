package com.lhs.Controller;

import java.util.ArrayList;
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
import com.lhs.Models.TimeSlot;
import com.lhs.Repository.AppointmentRepository;
import com.lhs.Repository.TimeSlotRepository;

import lombok.Data;


@RestController
@RequestMapping("/appointment")
@CrossOrigin("*")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;
	
	@GetMapping("/get")    
	private List<AppointmentChart> GetAllAppointments()
	{
		List<AppointmentChart> appointmentCharts = new ArrayList<>();
		List<Appointment> appointments =  appointmentRepository.findAll();
		
		for(Appointment appointment: appointments) {
			
			AppointmentChart appointmentChart = new AppointmentChart();
			appointmentChart.setAppointment(appointment);
			appointmentChart.setTimeSlot(timeslotRepository.findAllByAppointment(appointment));
			appointmentCharts.add(appointmentChart);
			
		}
		
		return appointmentCharts;
	}
	@GetMapping("/get/{id}")
	private List<AppointmentChart> GetAppointments(@PathVariable("id") Integer Id)
	{
		List<AppointmentChart> appointmentCharts = new ArrayList<>();
		List<Appointment> appointments =  appointmentRepository.findByDoctorId(Id);
		
		for(Appointment appointment: appointments) {
			
			AppointmentChart appointmentChart = new AppointmentChart();
			appointmentChart.setAppointment(appointment);
			appointmentChart.setTimeSlot(timeslotRepository.findAllByAppointment(appointment));
			appointmentCharts.add(appointmentChart);
			
		}
		
		return appointmentCharts;
	}
	@GetMapping("/book/{apmt_id}/{slot_id}")
	private AppointmentChart BookAppointment(@PathVariable("apmt_id") Integer apmt_Id, @PathVariable("slot_id") Integer slot_id)
	{
		AppointmentChart appointmentChart = new AppointmentChart();
		
		Optional<Appointment> aptmt = appointmentRepository.findById(apmt_Id);
		Appointment appointment = aptmt.get();
		
		Optional<TimeSlot> slot = timeslotRepository.findById(slot_id);
		TimeSlot timeSlot = slot.get();
		if(timeSlot.getSlotStatus() == 0)
			timeSlot.setSlotStatus(1);
		timeslotRepository.save(timeSlot);
				
		appointmentChart.setAppointment(appointment);
		List<TimeSlot> timeSlots = new ArrayList<>();
		timeSlots.add(timeSlot);
		appointmentChart.setTimeSlot(timeSlots);
		
		return appointmentChart;
	}
	
	@GetMapping("/unbook/{apmt_id}/{slot_id}")
	private Appointment unbookAppointment(@PathVariable("apmt_id") Integer apmt_Id, @PathVariable("slot_id") Integer slot_id)
	{
		Optional<Appointment> aptmt = appointmentRepository.findById(apmt_Id);
		Appointment appointment = aptmt.get();
		
		Optional<TimeSlot> slot = timeslotRepository.findById(slot_id);
		TimeSlot timeSlot = slot.get();
		if(timeSlot.getSlotStatus() == 1)
			timeSlot.setSlotStatus(0);
		timeslotRepository.save(timeSlot);
		return appointment;
	} 

}

@Data
class AppointmentChart{
	Appointment appointment;
	List<TimeSlot> timeSlot;
}
