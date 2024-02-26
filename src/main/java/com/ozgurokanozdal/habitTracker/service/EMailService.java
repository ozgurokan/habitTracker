package com.ozgurokanozdal.habitTracker.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class EMailService implements MailService {

    private JavaMailSender javaMailSender;


    public EMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendMail(String to,String token) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("okansaat3@gmail.com");
        message.setTo(to);
        message.setText(token);
        message.setSubject("Habit Tracker!");
        javaMailSender.send(message);
        return "Gönderildi";
    }

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("okansaat3@gmail.com");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }

}
