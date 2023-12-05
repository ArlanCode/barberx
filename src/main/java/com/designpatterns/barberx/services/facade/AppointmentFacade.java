package com.designpatterns.barberx.services.facade;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.builder.AppointmentBuilder;
import com.designpatterns.barberx.dtos.AppointmentRecordDto;
import com.designpatterns.barberx.erro.AppointmentNotFoundException;
import com.designpatterns.barberx.models.AppointmentModel;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.repositories.IAppointmentRepository;
import com.designpatterns.barberx.repositories.IBarberRepository;
import com.designpatterns.barberx.repositories.IClientRepository;
import com.designpatterns.barberx.state.AcceptedState;
import com.designpatterns.barberx.state.CanceledState;
import com.designpatterns.barberx.state.EnumState;
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

    @Autowired
    ClientFacade clientFacade;

    
    public AppointmentModel createAppointment(AppointmentRecordDto appointmentRecordDto) {
        BarberModel barber = barberRepository.findByUsername(appointmentRecordDto.barberUsername()).orElseThrow(() -> new AppointmentNotFoundException("Barber not found"));
        ClientModel client = clientRepository.findByUsername(appointmentRecordDto.clientUsername()).orElseThrow(() -> new AppointmentNotFoundException("Customer not found"));
        
        AppointmentModel appointment = new AppointmentBuilder()
        .withClient(client)
        .withBarber(barber)
        .withEnumState(EnumState.PENDING)
        .withAppointmentDateTime(appointmentRecordDto.appointmentDateTime())
        .build();
        
        appointmentRepository.save(appointment);
        return appointment;
    }

    public List<AppointmentModel> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public AppointmentModel getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
    }

    public AppointmentModel updateAppointmentTime(Long appointmentId, LocalDateTime newDateTime) {
        AppointmentModel appointment = getAppointmentById(appointmentId);

        appointment.setAppointmentDateTime(newDateTime);
        appointmentRepository.save(appointment);

        return appointment;
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.delete(getAppointmentById(appointmentId));
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
        notifyClient(appointment);
        return status;
    }

    public String acceptAppointment(Long appointmentId) {
        AppointmentModel appointment = getAppointmentById(appointmentId);
        State state = getState(appointment);
        String status = state.accept();
        appointmentRepository.save(appointment);
        notifyClient(appointment);
        return status;
    }

    public void notifyClient(AppointmentModel appointment){
        clientFacade.notifyByEmail(appointment.getClient().getEmail(), "Appointment Update", "Your appointment state has been updated to: " + appointment.getEnumState());
    }

}
