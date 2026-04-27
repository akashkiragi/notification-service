package com.hqms.notification.controller;

import com.hqms.notification.service.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nofication")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestParam String emailId) throws MessagingException {
        notificationService.sendEmail(emailId);
    }

}
