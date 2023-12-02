package com.designpatterns.barberx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.facade.AppointmentFacade;
import com.designpatterns.barberx.models.AppointmentModel;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {


   @Autowired
   private AppointmentFacade appointmentService;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AppointmentModel appointmentModel){
        appointmentService.createAppointment(appointmentModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentModel);
    }
    
    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
        
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }

    
    @PutMapping("/{appointmentId}/accept")
    public ResponseEntity<String> acceptAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.acceptAppointment(appointmentId));
    }
    
}