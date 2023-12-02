package com.designpatterns.barberx.models;

import com.designpatterns.barberx.observer.AppointmentObserver;
import com.designpatterns.barberx.state.EnumState;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_BARBERS")
public class BarberModel extends PersonModel implements AppointmentObserver{

    @Override
    public void update(EnumState enumState) {
        System.out.println("Barbeiro, o agendamento agora está no estado de "+ enumState);
    }

}
