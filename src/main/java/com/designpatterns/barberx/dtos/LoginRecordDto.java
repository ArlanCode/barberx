package com.designpatterns.barberx.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRecordDto (    
    @NotBlank(message = "Username cannot be blank") String username,
    @NotBlank(message = "Password cannot be blank") String password
    ){
}
