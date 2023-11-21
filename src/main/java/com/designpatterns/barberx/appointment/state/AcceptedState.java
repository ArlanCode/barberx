package com.designpatterns.barberx.appointment.state;

import com.designpatterns.barberx.appointment.AppointmentModel;

public class AcceptedState implements AppointmentState {
    private final AppointmentModel context;

    public AcceptedState(AppointmentModel context) {
        this.context = context;
    }

    @Override
    public void cancel() {
        context.setState(new CanceledState(context));
        System.out.println("Appointment is canceled.");
    }

    @Override
    public void accept() {
        System.out.println("Appointment is already accepted.");
    }
    
}