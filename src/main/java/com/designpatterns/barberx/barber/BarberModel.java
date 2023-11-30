package com.designpatterns.barberx.barber;

import com.designpatterns.barberx.appointment.AppointmentObserver;
import com.designpatterns.barberx.appointment.EnumState;
import com.designpatterns.barberx.person.PersonModel;

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
