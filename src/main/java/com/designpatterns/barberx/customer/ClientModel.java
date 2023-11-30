package com.designpatterns.barberx.customer;

import com.designpatterns.barberx.appointment.AppointmentObserver;
import com.designpatterns.barberx.appointment.EnumState;
import com.designpatterns.barberx.person.PersonModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CUSTOMERS")
public class ClientModel extends PersonModel implements AppointmentObserver{
    @Override
    public void update(EnumState enumState) {
        System.out.println("Cliente, o agendamento agora está no estado de "+ enumState);
    }
    
}