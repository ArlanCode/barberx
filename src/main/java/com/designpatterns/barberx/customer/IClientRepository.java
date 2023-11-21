package com.designpatterns.barberx.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<ClientModel, Long>{
    Optional<ClientModel> findByUsername(String username);
    Optional<ClientModel> findById(Long id);
}
