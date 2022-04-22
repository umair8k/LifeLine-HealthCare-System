package com.lhs.Models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
//@Entity
public class SlotLock {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slotLockId;
	
	@OneToOne(cascade = { CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
	private User user;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	private DoctorSlots doctorSlots;
	
	@Column(name="isLocked")
	private Boolean isLocked;
	
	@Column(name="create_Time")
	private LocalDateTime createTime;

}
