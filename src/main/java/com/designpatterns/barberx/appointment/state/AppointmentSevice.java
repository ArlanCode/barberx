package com.designpatterns.barberx.appointment.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.appointment.AppointmentModel;
import com.designpatterns.barberx.appointment.IAppointmentRepository;
import com.designpatterns.barberx.barber.BarberModel;
import com.designpatterns.barberx.barber.IBarberRepository;
import com.designpatterns.barberx.customer.ClientModel;
import com.designpatterns.barberx.customer.IClientRepository;
import com.designpatterns.barberx.erro.AppointmentNotFoundException;

@Service
public class AppointmentSevice {

   @Autowired
    IAppointmentRepository appointmentRepository;

    @Autowired
    IBarberRepository barberRepository;

    @Autowired
    IClientRepository clientRepository;

    
    public AppointmentModel createAppointment(AppointmentModel appointmentModel) {
        BarberModel barber = barberRepository.findByUsername(appointmentModel.getBarber().getUsername()).orElseThrow(() -> new RuntimeException("Barber não encontrado"));
        ClientModel client = clientRepository.findByUsername(appointmentModel.getClient().getUsername()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        appointmentModel.setBarber(barber);
        appointmentModel.setClient(client);
        appointmentRepository.save(appointmentModel);
        return appointmentModel;
    }
     

    private AppointmentModel getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with ID: " + appointmentId));
    }

    public void cancelAppointment(Long appointmentId) {
        AppointmentModel appointment = getAppointmentById(appointmentId);
        appointment.cancel();
        appointmentRepository.save(appointment);
    }

    public void acceptAppointment(Long appointmentId) {
        AppointmentModel appointment = getAppointmentById(appointmentId);
        appointment.accept();
        appointmentRepository.save(appointment);
    }
}
