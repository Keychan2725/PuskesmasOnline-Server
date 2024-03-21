package com.java.PuskesmasOnline.PuskesmasOnline.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailUtil {

    private static JavaMailSender javaMailSender;

    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("puskesmasonline273@gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("Pusline2024");
        mailSender.setPassword("puskesmas123");
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }

    public static void sendSetPassword(String email) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("puskesmasonline273@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText(
                "<div>" +
                "   <a href=\"<http://localhost:8080/api/user/set-password?email=%s>\" target=\"_blank\"> Click Lnk To Set Password </a>" +
                "</div>".formatted(email), true);

        javaMailSender.send(mimeMessage);

    }



}
