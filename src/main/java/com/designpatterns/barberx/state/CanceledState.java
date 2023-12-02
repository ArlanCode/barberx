package com.designpatterns.barberx.state;

public class CanceledState implements State{

    @Override
    public String accept() {
        return "It is not possible to accept the appointment as it has already been canceled";
    }

    @Override
    public String cancel() {
        return "appointment has already been canceled";
    }
    
}