package com.designpatterns.barberx.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.dtos.AppointmentRecordDto;
import com.designpatterns.barberx.models.AppointmentModel;
import com.designpatterns.barberx.services.facade.AppointmentFacade;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {


   @Autowired
   private AppointmentFacade appointmentService;


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid AppointmentRecordDto appointmentRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointmentRecordDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentModel>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(appointmentId));
    }


    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) { 
        return ResponseEntity.ok(appointmentService.cancelAppointment(appointmentId));
    }

    @PutMapping("/{appointmentId}/accept")
    public ResponseEntity<?> acceptAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.acceptAppointment(appointmentId));
    }

    @PutMapping("/{appointmentId}/update-time")
    public ResponseEntity<?> updateAppointmentTime(
            @PathVariable Long appointmentId,
            @RequestBody String newDateTime
    ) {
        LocalDateTime dateTime = LocalDateTime.parse(newDateTime);
        return ResponseEntity.ok(appointmentService.updateAppointmentTime(appointmentId, dateTime));
    }
    

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("Appointment deleted successfully");
    }
}
