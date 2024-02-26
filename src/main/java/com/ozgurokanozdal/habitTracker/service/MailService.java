package com.ozgurokanozdal.habitTracker.service;

public interface MailService {
    String sendMail(String to,String token);

    void send(String to,String mail);

}
