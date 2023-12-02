package com.designpatterns.barberx.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.repositories.IClientRepository;

@Service
public class ClienteFacade {
    @Autowired
    IClientRepository clientRepository;
}
