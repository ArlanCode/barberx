package com.designpatterns.barberx.cliente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<ClientModel, UUID>{
    Optional<ClientModel> findByUsername(String username);
    Optional<ClientModel> findById(UUID id);
}
