package com.lhs.Service.Impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.lhs.Service.EmailService;

@Service
public  class EmailServiceImpl implements EmailService{
	
    @Override
    public boolean sendEmail(String subject, String message, String to)
    {
        boolean flag = false; 
       String senderEmail="lifeline.healthcaresys@gmail.com";
       String senderPassword = "LifeLineHealthCareSystem"; 
       
     
     

        
        Properties properties = new Properties();

       

        properties.put("mail.smtp.auth", "true"); 
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 

        // get the session object and pass username and password
        Session session = Session.getDefaultInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            MimeMessage msg = new MimeMessage(session); 

            msg.setFrom(new InternetAddress(senderEmail)); 

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 

            msg.setSubject(subject); 
            msg.setContent(message,"text/html"); 

            Transport.send(msg); 

            flag = true; 

        }catch(Exception e){

            System.out.println("EmailService File Error"+ e);
        }

        return flag;
    }

}
