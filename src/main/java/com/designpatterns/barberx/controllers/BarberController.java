package com.designpatterns.barberx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.dtos.BarberRecordDto;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.services.facade.BarberFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/barbers")
public class BarberController {

    @Autowired
    BarberFacade barberFacade;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid BarberRecordDto barberRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(barberFacade.createBarber(barberRecordDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BarberModel>> getAllBarbers(){
        return ResponseEntity.ok(barberFacade.getAllBarbers());
    }

    @GetMapping("/{barberId}")
    public ResponseEntity<?> getBarberById(@PathVariable Long barberId){
        return ResponseEntity.ok(barberFacade.getBarberById(barberId));
    }

    @DeleteMapping("/{barberId}")
    public ResponseEntity<String> deleteBarber(@PathVariable Long barberId){
        barberFacade.deleteBarber(barberId);
        return ResponseEntity.ok("Barber deleted successfully");
    } 
}
