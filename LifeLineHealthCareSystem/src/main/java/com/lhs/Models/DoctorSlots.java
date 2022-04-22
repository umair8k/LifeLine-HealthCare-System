package com.lhs.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DoctorSlots {
	
	@Id
	@GeneratedValue(generator = "slot_gen", strategy = GenerationType.SEQUENCE)
	private Integer slotId;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "doctorId")
	private Doctors doctors;
	
	@Column(name="start_time")
	private String startTime;

	@Column(name="end_time")
	private String endTime;
	
	@JsonIgnore
	@Column(name="isLocked")
	private boolean isLocked;
	
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "c_id")
	private ConsultingDays consultingDays;


}
