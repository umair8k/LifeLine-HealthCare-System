package com.lhs.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.Slot;
import com.lhs.Repository.SlotRepository;
import com.lhs.Service.Impl.SlotServiceImpl;

@RestController
@RequestMapping("/slot")
public class SlotController {

	@Autowired
	private SlotServiceImpl slotService;
	
	@Autowired 
	private SlotRepository slotRepository;
	

	
	
	
	@PostMapping("/createSlot")
	@PreAuthorize("hasAuthority('DOCTOR')")
	public Slot createDoctorSlot(@RequestBody Slot slot) {
		return this.slotService.createSlot(slot);
	}
	
	
	@GetMapping("/getSlot")
	public List<Slot> getAllSlot(){
		return slotService.getAllAvailableSlot();
		
	}
}
