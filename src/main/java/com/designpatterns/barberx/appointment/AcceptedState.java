package com.designpatterns.barberx.appointment;

public class AcceptedState implements State {
    AppointmentModel appointment;

    
    public AcceptedState() {
    }

    public AcceptedState(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    @Override
    public String accept() {
        return "appointment has already been accepted";
    }

    @Override
    public String cancel() {
        appointment.setEnumState(EnumState.CANCELED);
        return "Appointment canceled successfully";
    }
    
}
