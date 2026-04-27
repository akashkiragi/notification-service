package com.hqms.notification.service;

import com.hqms.notification.event.consumed.PatientRegisteredEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Consumes patient.registered events from the Patient Service.
 *
 * Used to:
 * 1. Validate patients exist before allowing bookings (cache warming)
 * 2. Log and audit patient registrations observed by this service
 *
 * This demonstrates Kafka consumer with explicit offset acknowledgement.
 */
@Component
@Slf4j @RequiredArgsConstructor
public class PatientEventConsumer {

    private final NotificationService notificationService;


    @KafkaListener(
        topics = "${app.kafka.topics.patient-registered}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void onPatientRegistered(
            @Payload PatientRegisteredEvent event,
            Acknowledgment ack) throws MessagingException {

        log.info("Received PatientRegisteredEvent: patientRef={}",
                event.getPatientRef());
        notificationService.sendEmail(event.getEmail());

        log.debug("Patient available for booking: id={}, name={} {}",
                event.getPatientId(), event.getFirstName(), event.getLastName());
        ack.acknowledge();
    }
}
