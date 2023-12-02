package com.designpatterns.barberx.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto(
    @NotBlank(message = "Username cannot be blank") String username,
    @NotBlank(message = "name cannot be blank") String fullName,
    @NotBlank(message = "Password cannot be blank") String password
) {
}
