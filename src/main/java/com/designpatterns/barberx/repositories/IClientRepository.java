package com.designpatterns.barberx.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.designpatterns.barberx.models.ClientModel;

public interface IClientRepository extends JpaRepository<ClientModel, Long>{
    Optional<ClientModel> findByUsername(String username);
    Optional<ClientModel> findById(long id);
}
