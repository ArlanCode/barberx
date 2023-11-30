package com.designpatterns.barberx.barber;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarberRepository extends JpaRepository<BarberModel, Long>{
    Optional<BarberModel> findByUsername(String username);
    Optional<BarberModel> findById(long id);

}
