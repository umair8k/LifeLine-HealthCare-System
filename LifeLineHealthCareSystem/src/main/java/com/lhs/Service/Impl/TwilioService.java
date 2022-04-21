package com.lhs.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.Payload.Request.SmsRequest;

@Service
public class TwilioService {
	
	@Autowired
	private SmsSenderImpl smsSender;
	
	public void sendSms(SmsRequest smsRequest) {
		smsSender.sendSms(smsRequest.getMessage(),smsRequest.getPhoneNo());
	}

}
