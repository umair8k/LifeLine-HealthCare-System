package com.lhs.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringData{
	
	@Id
	@GeneratedValue(generator = "data_gen",strategy = GenerationType.AUTO)
	private int id;
	private String bloodPressure;
	private String temperature;
	private String weight;
	private long height;
	private String heartRate;
	
	@OneToOne
	@JoinColumn(name="Nurse_id_fk")
	private User userNurse;
	@OneToOne
	@JoinColumn(name="patient_id_fk")
    private User user;

}
