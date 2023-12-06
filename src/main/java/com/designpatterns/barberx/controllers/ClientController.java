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

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.dtos.LoginRecordDto;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.services.facade.ClientFacade;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientFacade clienteFacade;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid ClientRecordDto clientRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteFacade.createClient(clientRecordDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRecordDto loginRecordDto){
        return ResponseEntity.ok(clienteFacade.verificarLogin(loginRecordDto));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClientById(@PathVariable Long clientId){
        return ResponseEntity.ok(clienteFacade.getClientById(clientId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.ok(clienteFacade.getAllClients());
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
        clienteFacade.deleteClientById(clientId);
        return ResponseEntity.ok("Client deleted successfully");
    }

}
