package com.designpatterns.barberx.facade;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.dtos.ClientRecordDto;
import com.designpatterns.barberx.erro.DuplicateUsernameException;
import com.designpatterns.barberx.models.ClientModel;
import com.designpatterns.barberx.repositories.IClientRepository;

@Service
public class ClienteFacade {
    @Autowired
    IClientRepository clientRepository;

    public ClientModel createClient(ClientRecordDto clientRecordDto){
        if(clientRepository.findByUsername(clientRecordDto.username()).isPresent()){
            throw new DuplicateUsernameException("This customer username is already in use");
        }

        var client = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, client);
        
        clientRepository.save(client);
        return client;
     }
}
