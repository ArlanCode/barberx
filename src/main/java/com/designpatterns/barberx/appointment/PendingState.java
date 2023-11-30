package com.designpatterns.barberx.appointment;
public class PendingState implements State {
    AppointmentModel appointment;
    
    public PendingState() {
    }

    public PendingState(AppointmentModel appointment) {
        this.appointment = appointment;
    }


    public String accept() {
        appointment.setEnumState(EnumState.ACCEPTED);
        return "Appointment accepted successfully";
    }

    
    public String cancel() {
        appointment.setEnumState(EnumState.CANCELED);
        return "Appointment canceled successfully";
    }
    
}
