package com.designpatterns.barberx.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.designpatterns.barberx.models.AppointmentModel;

public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    Optional<AppointmentModel> findById(Long id);
}
