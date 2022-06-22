package com.lhs.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@Entity
public class Slot {
	@Id
	@GeneratedValue(generator = "slot_gen",strategy = GenerationType.AUTO)
	private int slotId;
	
	private Date slotDate;
	
	private String startTime;
	
	private String endTime;
	
	private boolean Booked;

}
