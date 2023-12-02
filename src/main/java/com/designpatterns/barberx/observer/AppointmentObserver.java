package com.designpatterns.barberx.observer;

import com.designpatterns.barberx.state.EnumState;

public interface AppointmentObserver {
    void update(EnumState enumState);
    
}
