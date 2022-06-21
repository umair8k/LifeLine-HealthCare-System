package com.lhs.Service;

import java.security.Principal;

import com.lhs.Models.MonitoringData;

public interface NurseService {
	
	public MonitoringData addMonitoringData(MonitoringData monitoringData,String username,Principal principal);
	
	public MonitoringData fillMonitringData(Principal principal, String username);

}
