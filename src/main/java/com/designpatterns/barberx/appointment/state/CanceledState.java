package com.designpatterns.barberx.appointment.state;

import com.designpatterns.barberx.appointment.AppointmentModel;

public class CanceledState implements AppointmentState{

    private final AppointmentModel context;

    public CanceledState(AppointmentModel context) {
        this.context = context;
    }

    @Override
    public void cancel() {
        System.out.println("Appointment is already canceled.");
    }

    @Override
    public void accept() {
        System.out.println("Cannot accept a canceled appointment.");
    }
    
}
