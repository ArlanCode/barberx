package com.designpatterns.barberx.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.designpatterns.barberx.models.AppointmentModel;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.models.ClientModel;

public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Long> {
    Optional<AppointmentModel> findById(Long id);
    List<AppointmentModel> findByBarber(BarberModel barber);
    List<AppointmentModel> findByClient(ClientModel client);
}
