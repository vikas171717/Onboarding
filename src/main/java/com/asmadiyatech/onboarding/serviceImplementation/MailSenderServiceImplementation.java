<<<<<<< HEAD
package com.asmadiyatech.onboarding.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.service.MailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailSenderServiceImplementation implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Verification");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

}
=======
package com.asmadiyatech.onboarding.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmadiyatech.onboarding.service.MailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailSenderServiceImplementation implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Verification");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

}
>>>>>>> 8a8786ed1032cfcfec9908411367fd4fcb8deeb4
