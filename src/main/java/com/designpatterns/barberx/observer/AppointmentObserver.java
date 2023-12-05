package com.designpatterns.barberx.observer;

public interface AppointmentObserver {
    void notifyByEmail(String to, String subject, String body);
    
}
