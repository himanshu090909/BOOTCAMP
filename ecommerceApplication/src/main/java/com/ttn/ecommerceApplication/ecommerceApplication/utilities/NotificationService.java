package com.ttn.ecommerceApplication.ecommerceApplication.utilities;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Lazy
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Lazy
    @Autowired
    NotificationService notificationService;

    @Async
    public void sendNotificaitoin(User user) throws MailException {
        System.out.println("Sending email...");
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("hs631443@gmail.com");
        mail.setSubject("Spring Boot is awesome nahi hai!");
        String uu = tokenDao.getToken(user);
        mail.setText(uu);
        javaMailSender.send(mail);
        System.out.println("Email Sent!");
    }



}
