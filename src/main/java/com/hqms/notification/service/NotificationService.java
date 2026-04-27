package com.hqms.notification.service;

import jakarta.mail.MessagingException;

public interface NotificationService {

    void sendEmail(String email) throws MessagingException;
}
