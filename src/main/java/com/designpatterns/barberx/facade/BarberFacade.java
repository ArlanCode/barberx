package com.designpatterns.barberx.facade;

import org.springframework.beans.factory.annotation.Autowired;

import com.designpatterns.barberx.repositories.IBarberRepository;

public class BarberFacade {
    @Autowired
    IBarberRepository barberRepository;
}
