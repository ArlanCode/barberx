package com.designpatterns.barberx.facade;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.erro.DuplicateUsernameException;
import com.designpatterns.barberx.erro.PersonNotFoundException;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.repositories.IClientRepository;

@Service
public class ClientFacade {
    @Autowired
    IClientRepository clientRepository;

    public ClientModel createClient(ClientRecordDto clientRecordDto){
        if(clientRepository.findByUsername(clientRecordDto.username()).isPresent()){
            throw new DuplicateUsernameException("This client username is already in use");
        }

        var client = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, client);
        
        clientRepository.save(client);
        return client;
     }

    public ClientModel getClientById(Long clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new PersonNotFoundException("Client not found with ID: " + clientId));
    }

    public List<ClientModel> getAllClients(){
        return clientRepository.findAll();
    }

    public void deleteClientById(Long clientId){
        clientRepository.delete(getClientById(clientId));
    }
}
