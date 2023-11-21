package com.designpatterns.barberx.appointment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    Optional<AppointmentModel> findById(Long id);
}
