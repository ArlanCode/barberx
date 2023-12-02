package com.designpatterns.barberx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.facade.ClienteFacade;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/customers")
public class ClientController {

    @Autowired
    ClienteFacade clienteFacade;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid ClientRecordDto clientRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteFacade.createClient(clientRecordDto));
    }

}
