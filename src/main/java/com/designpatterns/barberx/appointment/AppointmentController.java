package com.designpatterns.barberx.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.barber.BarberModel;
import com.designpatterns.barberx.barber.IBarberRepository;
import com.designpatterns.barberx.customer.ClientModel;
import com.designpatterns.barberx.customer.IClientRepository;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    IAppointmentRepository appointmentRepository;

    @Autowired
    IBarberRepository barberRepository;

    @Autowired
    IClientRepository clientRepository;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AppointmentModel appointmentModel){

       BarberModel barber = barberRepository.findByUsername(appointmentModel.getBarber().getUsername()).orElseThrow(() -> new RuntimeException("Barber não encontrado"));
        ClientModel client = clientRepository.findByUsername(appointmentModel.getClient().getUsername()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        appointmentModel.setBarber(barber);
        appointmentModel.setClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentRepository.save(appointmentModel));
    }
    
}
