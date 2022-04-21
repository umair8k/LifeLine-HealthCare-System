package com.lhs.Service;

import org.springframework.stereotype.Service;

import com.lhs.Payload.Request.SmsRequest;
@Service
public interface SmsSender {

	boolean sendSms(String phoneNo, String message);

}
