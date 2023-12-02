package com.designpatterns.barberx.controllers;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.repositories.IClientRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/customers")
public class ClientController {

    @Autowired
    IClientRepository clientRepository;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid ClientRecordDto clientRecordDto){

        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);

        var user = this.clientRepository.findByUsername(clientModel.getUsername());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", "Usuário existente", "details", "Esse 'username' já pertence a outro cliente"));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientRepository.save(clientModel));


    }

}
