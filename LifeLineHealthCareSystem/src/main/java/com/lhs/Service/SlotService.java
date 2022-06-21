package com.lhs.Service;

import org.springframework.stereotype.Service;

import com.lhs.Models.Slot;
import com.lhs.Models.User;
@Service
public interface SlotService {
	
	public Slot createSlot( Slot slot);

	public Slot bookSlot(Slot slot, int slotId);
}
