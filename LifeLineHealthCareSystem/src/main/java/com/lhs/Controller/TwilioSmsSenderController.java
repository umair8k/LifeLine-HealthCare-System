package com.lhs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.Payload.Request.SmsRequest;
import com.lhs.Service.Impl.SmsSenderImpl;

@RestController
@RequestMapping("/sms")
public class TwilioSmsSenderController {

	@Autowired
	private SmsSenderImpl smsSender;

	@PostMapping("/send")
	public void sendSms(@RequestBody SmsRequest smsRequest) {
		smsSender.sendSms(smsRequest.getPhoneNo(), smsRequest.getMessage());
	}

}
