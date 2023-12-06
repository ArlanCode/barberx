package com.designpatterns.barberx.services.facade;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.dtos.LoginRecordDto;
import com.designpatterns.barberx.erro.DuplicateUsernameException;
import com.designpatterns.barberx.erro.IncorrectPasswordException;
import com.designpatterns.barberx.erro.PersonNotFoundException;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.observer.AppointmentObserver;
import com.designpatterns.barberx.repositories.IClientRepository;
import com.designpatterns.barberx.services.EmailService;

@Service
public class ClientFacade implements AppointmentObserver{
    @Autowired
    IClientRepository clientRepository;
    @Autowired
    EmailService emailService;

    public ClientModel createClient(ClientRecordDto clientRecordDto){
        if(clientRepository.findByUsername(clientRecordDto.username()).isPresent()){
            throw new DuplicateUsernameException("This client username is already in use");
        }

        var client = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, client);
        
        clientRepository.save(client);
        return client;
    }

    public ClientModel verificarLogin(LoginRecordDto loginRecordDto){
        
        ClientModel client = getClientByUsername(loginRecordDto.username());
        if (loginRecordDto.password().compareTo(client.getPassword()) != 0) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return client;
    }

    public ClientModel getClientById(Long clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new PersonNotFoundException("Client not found with ID: " + clientId));
    }

    public ClientModel getClientByUsername(String clientUsername) {
        return clientRepository.findByUsername(clientUsername)
            .orElseThrow(() -> new PersonNotFoundException("Client not found with Username: " + clientUsername));
    }

    public List<ClientModel> getAllClients(){
        return clientRepository.findAll();
    }

    public void deleteClientById(Long clientId){
        clientRepository.delete(getClientById(clientId));
    }

    @Override
    public void notifyByEmail(String to, String subject, String body) {
        emailService.sendEmail(to, subject, body);
    }
}
