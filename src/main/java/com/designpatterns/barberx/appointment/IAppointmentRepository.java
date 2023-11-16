package com.designpatterns.barberx.appointment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<AppointmentModel, UUID> {
    
    
}
