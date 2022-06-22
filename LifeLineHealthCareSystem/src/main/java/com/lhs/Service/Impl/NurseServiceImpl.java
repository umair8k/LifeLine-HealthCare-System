package com.lhs.Service.Impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.Models.MonitoringData;
import com.lhs.Models.User;
import com.lhs.Repository.MonitoringDataRepository;
import com.lhs.Repository.UserRepository;
import com.lhs.Service.NurseService;

@Service
public class NurseServiceImpl implements NurseService {

	@Autowired
	private MonitoringDataRepository monitoringDataRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*@Override
	public MonitoringData addMonitoringData(MonitoringData monitoringData, String username,Principal principal) {
		
		String LoggedInUser=principal.getName();
		User currentUser=this.userRepository.findByUsername(LoggedInUser);
		
		MonitoringData data= monitoringDataRepository.findById(id);
		System.out.println(data);
		data.setBloodPressure(monitoringData.getBloodPressure());
		data.setHeartRate(monitoringData.getHeartRate());
		data.setHeight(monitoringData.getHeight());
		data.setWeight(monitoringData.getWeight());
		data.setTemperature(monitoringData.getTemperature());
		 MonitoringData updatedUser=monitoringDataRepo.save(data);
		 return updatedUser;
	}*/

	@Override
	public MonitoringData fillMonitringData(Principal principal, String username) {
		String LoggedInUser=principal.getName();
		User currentUser=this.userRepository.findByUserId(LoggedInUser);
		
		MonitoringData data=new MonitoringData();
		data.setUserNurse(data.getUserNurse());
		data .setBloodPressure(data.getBloodPressure());
		data.setHeartRate(data.getHeartRate()+" BPM");
		data.setHeight(data.getHeight());
		data.setTemperature(data.getTemperature()+" °F");
		data.setWeight(data.getWeight()+" kgs");
		
		data.setUser(currentUser);
		//currentUser.setNurseDetail(nursedetail);
		return data;
	}

	@Override
	public MonitoringData addMonitoringData(MonitoringData monitoringData, String username, Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

}
