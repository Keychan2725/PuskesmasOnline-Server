package com.java.PuskesmasOnline.PuskesmasOnline.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.MessagingException;

import javax.mail.Message;
import java.io.UnsupportedEncodingException;

public class EmailUtil {
    @Autowired
    private static JavaMailSender javaMailSender;

    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public static void sendSetPassword(String email) throws MessagingException, UnsupportedEncodingException, jakarta.mail.MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Forgot Password");
        mimeMessageHelper.setText("<html><body>" +
                "<div>" +
                "   <a href=\"http://localhost:8080/api/user/forgot-password?email=%s \" target=\"_blank\"> Click Lnk To Forgot Password </a>" +
                "</div>" +
                "</body></html>", true);

        javaMailSender.send(mimeMessage);
    }


}
