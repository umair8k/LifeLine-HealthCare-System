package com.lhs.Models;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Doctorslot {

		@Column(name="start_time")
		private String startTime;

		@Column(name="end_time")
		private String endTime;

}
