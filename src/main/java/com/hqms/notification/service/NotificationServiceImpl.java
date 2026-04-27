package com.hqms.notification.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    private final String senderEmail;

    public NotificationServiceImpl(JavaMailSender mailSender,
                                   @Value("${app.gmail.sender.email}") String senderEmail) {
        this.mailSender = mailSender;
        this.senderEmail = senderEmail;
    }

    @Override
    public void sendEmail(String email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(senderEmail);
        helper.setTo(email);
        helper.setSubject("Test Email");
        helper.setText("<h2>Hello from Spring Boot</h2>", true);

        mailSender.send(message);

    }

}
