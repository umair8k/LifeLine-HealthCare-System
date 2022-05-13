package com.lhs.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.lhs.Models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	

}
