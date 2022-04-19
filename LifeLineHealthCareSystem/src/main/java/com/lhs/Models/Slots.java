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

import lombok.Data;

@Data
//@Entity
@Table(name="slot")
public class Slots {
	
	@Id
	@GeneratedValue(generator = "slot_gen", strategy = GenerationType.SEQUENCE)
	private Integer slotId;
	
	private Doctors doctors;
	
	private String startTime;
	
	private String endTime;
	
	@Column(name="slot_date")
	private LocalDate slotDate;
	
	@JsonIgnore
	@Column(name="is_booked")
	private Boolean isBooked;
	
	@JsonIgnore
	private Date createdDate;
	
	@JsonIgnore
	private Date modifiedDate;
	
	private ConsultingDays consultingDays;

}
