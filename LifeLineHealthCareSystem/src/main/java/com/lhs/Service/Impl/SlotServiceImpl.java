package com.lhs.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.Models.Slot;
import com.lhs.Repository.SlotRepository;
import com.lhs.Service.SlotService;
@Service
public class SlotServiceImpl implements SlotService {

	@Autowired
	private SlotRepository slotRepository;
	
	@Override
	public Slot createSlot(Slot slot) {
		
		return this.slotRepository.save(slot);

}

	public List<Slot> getAllAvailableSlot() {
		Slot slot1=new Slot();
			return this.slotRepository.findAll();
	
	
	}

	
	@Override
	public Slot bookSlot(Slot slot, int slotId) {
		// TODO Auto-generated method stub
		return null;
	}
}