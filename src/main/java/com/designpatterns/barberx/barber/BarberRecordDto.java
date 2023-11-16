package com.designpatterns.barberx.barber;

import jakarta.validation.constraints.NotBlank;

public record BarberRecordDto(
    @NotBlank(message = "Username cannot be blank") String username,
    @NotBlank(message = "name cannot be blank") String fullName,
    @NotBlank(message = "Password cannot be blank") String password
) {
}
