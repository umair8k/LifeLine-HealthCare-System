package com.lhs.Config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

    private final static Logger LOG = LoggerFactory.getLogger(TwilioInitializer.class);
    @Autowired
    private TwilioConfiguration twilioConfiguration;

    
 
	
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
		
		this.twilioConfiguration = twilioConfiguration;
		
		Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
		
		LOG.info("twilio initialized with accountSid {} " , twilioConfiguration.getAccountSid());
	}



   
    
}