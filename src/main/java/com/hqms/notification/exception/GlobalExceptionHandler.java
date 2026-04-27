package com.hqms.notification.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MessagingException.class)
    public void handleGeneral(MessagingException  ex) {
        log.error("Exception occurred while sending the email with cause {}", ex.getCause(),ex);

    }

    @ExceptionHandler(Exception.class)
    public void handleGeneral(Exception ex, HttpServletRequest req) {

    }
}
