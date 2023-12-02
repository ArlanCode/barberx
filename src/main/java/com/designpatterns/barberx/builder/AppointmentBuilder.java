package com.designpatterns.barberx.builder;

import java.time.LocalDateTime;

import com.designpatterns.barberx.models.AppointmentModel;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.state.EnumState;

public class AppointmentBuilder {
    private ClientModel client;
    private BarberModel barber;
    private EnumState enumState = EnumState.PENDING;
    private LocalDateTime appointmentDateTime;

    public AppointmentBuilder withClient(ClientModel client) {
        this.client = client;
        return this;
    }

    public AppointmentBuilder withBarber(BarberModel barber) {
        this.barber = barber;
        return this;
    }

    public AppointmentBuilder withEnumState(EnumState enumState) {
        this.enumState = enumState;
        return this;
    }

    public AppointmentBuilder withAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
        return this;
    }

    public AppointmentModel build() {
        AppointmentModel appointment = new AppointmentModel();
        appointment.setClient(this.client);
        appointment.setBarber(this.barber);
        appointment.setEnumState(this.enumState);
        appointment.setAppointmentDateTime(this.appointmentDateTime);
        return appointment;
    }
}

