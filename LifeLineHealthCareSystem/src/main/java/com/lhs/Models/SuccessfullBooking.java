package com.lhs.Models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SuccessfullBooking {

	private Integer slotId;
	private LocalDate appointmentDate;
	private String transactionId;
}
