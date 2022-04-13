package com.lhs.Service.Impl;

import com.lhs.Config.TwilioConfiguration;
import com.lhs.Payload.Request.SmsRequest;
import com.lhs.Service.SmsSender;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderImpl implements SmsSender {

    private static final Logger LOG = LoggerFactory.getLogger(SmsSenderImpl.class);

    @Autowired
    private TwilioConfiguration twilioConfiguration;


    @Override
    public boolean sendSms(String phoneNo, String message) {
        if (isPhoneNumberValid(phoneNo)) {
            PhoneNumber to = new PhoneNumber(phoneNo);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String msg = message;
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOG.info("Send sms {}", phoneNo,message);
            return true;
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + phoneNo + "] is not a valid number"
            );
        }
		

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}