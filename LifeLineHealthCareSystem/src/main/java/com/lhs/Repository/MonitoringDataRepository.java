package com.lhs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lhs.Models.MonitoringData;

@Repository
public interface MonitoringDataRepository extends JpaRepository<MonitoringData, String>{

}
