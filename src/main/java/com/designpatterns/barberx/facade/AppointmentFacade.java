package com.designpatterns.barberx.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.erro.AppointmentNotFoundException;
import com.designpatterns.barberx.models.AppointmentModel;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.repositories.IAppointmentRepository;
import com.designpatterns.barberx.repositories.IBarberRepository;
import com.designpatterns.barberx.repositories.IClientRepository;
import com.designpatterns.barberx.state.AcceptedState;
import com.designpatterns.barberx.state.CanceledState;
import com.designpatterns.barberx.state.PendingState;
import com.designpatterns.barberx.state.State;

@Service
public class AppointmentFacade {

   @Autowired
    IAppointmentRepository appointmentRepository;

    @Autowired
    IBarberRepository barberRepository;

    @Autowired
    IClientRepository clientRepository;

    
    public AppointmentModel createAppointment(AppointmentModel appointmentModel) {
        BarberModel barber = barberRepository.findByUsername(appointmentModel.getBarber().getUsername()).orElseThrow(() -> new AppointmentNotFoundException("Barbeiro não encontrado"));
        ClientModel client = clientRepository.findByUsername(appointmentModel.getClient().getUsername()).orElseThrow(() -> new AppointmentNotFoundException("Cliente não encontrado"));
        appointmentModel.setBarber(barber);
        appointmentModel.setClient(client);
        appointmentRepository.save(appointmentModel);
        return appointmentModel;
    }
     

    private AppointmentModel getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
    }

    private State getState(AppointmentModel appointment) {
        State state;
        switch (appointment.getEnumState()) {

            case CANCELED:
                state = new CanceledState();
                break;

            case ACCEPTED:
                state = new AcceptedState(appointment);
                break;
        
            default:
                state =  new PendingState(appointment);
                break;
        }

        return state;
    }

    public String cancelAppointment(Long appointmentId) {
        AppointmentModel appointment = getAppointmentById(appointmentId);
        State state = getState(appointment);
        String status = state.cancel();
        appointmentRepository.save(appointment);
        return status;
    }

    public String acceptAppointment(Long appointmentId) {
        AppointmentModel appointment = getAppointmentById(appointmentId);
        State state = getState(appointment);
        String status = state.accept();
        appointmentRepository.save(appointment);
        return status;
    }
    
}
