package com.lhs.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.Models.Appointment;
import com.lhs.Models.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
	
	
		List<TimeSlot> findAllByAppointment(Appointment appointmentId);
	

}
