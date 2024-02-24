package com.ozgurokanozdal.habitTracker.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EMailService implements MailService {

    private JavaMailSender javaMailSender;


    public EMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendMail(String to) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("okansaat3@gmail.com");
        message.setTo(to);
        message.setText("Aramıza Hoşgeldin Bram");
        message.setSubject("Habit Tracker!");
        javaMailSender.send(message);
        return "Gönderildi";
    }

}
