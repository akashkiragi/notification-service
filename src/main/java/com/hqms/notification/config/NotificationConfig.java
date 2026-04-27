package com.hqms.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class NotificationConfig {

    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private final String smtpHost;

    private final Integer smtpPort;

    private final String userEmail;

    private final String emailUserPassword;

    private final String smtpAuth;

    private final String STARTTLS;



    public NotificationConfig(@Value("${app.gmail.host}") String smtpHost,
                              @Value("${app.gmail.port}") Integer smtpPort,
                              @Value("${app.gmail.username}") String userEmail,
                              @Value("${app.gmail.password}") String emailUserPassword,
                              @Value("${app.gmail.properties.mail.smtp.auth}") String smtpAuth,
                              @Value("${app.gmail.properties.mail.smtp.starttls.enable}") String STARTTLS) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.userEmail = userEmail;
        this.emailUserPassword = emailUserPassword;
        this.smtpAuth = smtpAuth;
        this.STARTTLS = STARTTLS;
    }


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setPort(smtpPort);
        mailSender.setUsername(userEmail);
        mailSender.setPassword(emailUserPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put(MAIL_SMTP_AUTH, smtpAuth);
        props.put(MAIL_SMTP_STARTTLS_ENABLE, STARTTLS);

        return mailSender;
    }
}
