package com.designpatterns.barberx.barber;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarberRepository extends JpaRepository<BarberModel, UUID>{
    Optional<BarberModel> findByUsername(String username);
    Optional<BarberModel> findById(UUID id);

}
