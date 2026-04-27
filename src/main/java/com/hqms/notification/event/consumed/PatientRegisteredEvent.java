package com.hqms.notification.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Consumed from patient.registered topic.
 * Used to validate patient existence before booking.
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PatientRegisteredEvent {
    private String eventId;
    private String eventType;
    private Long patientId;
    private String patientRef;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDateTime occurredAt;
}
