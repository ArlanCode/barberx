package com.designpatterns.barberx.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppointmentRecordDto(
    @NotNull(message = "Appointment date and time cannot be null") LocalDateTime appointmentDateTime,
    @NotBlank(message = "Barber username cannot be blank") String barberUsername,
    @NotBlank(message = "Client username cannot be blank") String clientUsername
) {
}


