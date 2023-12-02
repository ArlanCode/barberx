package com.designpatterns.barberx.models;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class ClientBuilder {
    private String username;
    private String fullName;
    private String password;
    

    public ClientBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public ClientBuilder withName(String fullName ) {
        this.fullName = fullName ;
        return this;
    }

    public ClientBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientModel buildClientModel() {
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(this, clientModel);
        return clientModel;
    }
}
