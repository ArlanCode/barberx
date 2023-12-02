package com.designpatterns.barberx.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.designpatterns.barberx.models.BarberModel;

public interface IBarberRepository extends JpaRepository<BarberModel, Long>{
    Optional<BarberModel> findByUsername(String username);
    Optional<BarberModel> findById(long id);

}
