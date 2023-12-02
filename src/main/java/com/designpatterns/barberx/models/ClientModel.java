package com.designpatterns.barberx.models;

import com.designpatterns.barberx.observer.AppointmentObserver;
import com.designpatterns.barberx.state.EnumState;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CLIENTS")
public class ClientModel extends PersonModel implements AppointmentObserver{
    @Override
    public void update(EnumState enumState) {
        System.out.println("Cliente, o agendamento agora está no estado de "+ enumState);
    }
    
}