package com.designpatterns.barberx.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.appointment.state.AppointmentSevice;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {


   @Autowired
   private AppointmentSevice appointmentService;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody AppointmentModel appointmentModel){
        appointmentService.createAppointment(appointmentModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentModel);
    }
    
    @PostMapping("/{appointmentId}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok("Appointment canceled successfully");
    }

    @PostMapping("/{appointmentId}/accept")
    public ResponseEntity<String> acceptAppointment(@PathVariable Long appointmentId) {
        appointmentService.acceptAppointment(appointmentId);
        return ResponseEntity.ok("Appointment accepted successfully");
    }
    
}
