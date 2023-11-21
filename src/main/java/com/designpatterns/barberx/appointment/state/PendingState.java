package com.designpatterns.barberx.appointment.state;

import com.designpatterns.barberx.appointment.AppointmentModel;

public class PendingState implements AppointmentState{
    private final AppointmentModel context;

    public PendingState(AppointmentModel context) {
        this.context = context;
    }

    @Override
    public void cancel() {
        // Implementação para cancelamento no estado Pending
        context.setState(new CanceledState(context));
        System.out.println("Appointment is canceled.");
    }

    @Override
    public void accept() {
        // Implementação para aceitação no estado Pending
        context.setState(new AcceptedState(context));
        System.out.println("Appointment is accepted.");
    }
    
}