package com.lhs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.Models.Slot;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer>{

}
