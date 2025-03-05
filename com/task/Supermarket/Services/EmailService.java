package com.task.Supermarket.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void mailSender(String to, String subject, String body) {
        try {
            SimpleMailMessage massage=new SimpleMailMessage();
            massage.setTo(to);
            massage.setSubject(subject);
            massage.setText(body);
            javaMailSender.send(massage);

        } catch (Exception e) {
            log.error("Exception while sending mail " + e.getMessage());

        }
    }
}
