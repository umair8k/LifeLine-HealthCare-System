package com.lhs.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Models.MonitoringData;
import com.lhs.Models.User;
import com.lhs.Service.Impl.NurseServiceImpl;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseServiceImpl nurseService;
	
	@PostMapping("/fillPdata")
	public MonitoringData fillPreCData(MonitoringData monitoringData, String username, Principal principal){
		User nurse1=new User();
		
	
		 this.nurseService.addMonitoringData(monitoringData, username, principal);		
		 return monitoringData;
	}

}
