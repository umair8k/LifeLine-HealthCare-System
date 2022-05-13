package com.lhs.Models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	
	@Id
	private Integer appointmentId;
	
	private Integer doctorId;
	private Integer patientId;
	//private String startTime;
	
	//private String endTime;
	
	@Column(name="slot_date")
	private LocalDate slotDate;
	
	@Column(name="slot_time")
	private String slotTime;
	
	@Column(name="is_booked")
	private Integer isBooked;
	
	//@JsonIgnore
	//private Date createdDate;
	
	//@JsonIgnore
	//private Date modifiedDate;
	
	//private ConsultingDays consultingDays;

}
