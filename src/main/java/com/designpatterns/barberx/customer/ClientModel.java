package com.designpatterns.barberx.customer;

import com.designpatterns.barberx.person.PersonModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CUSTOMERS")
public class ClientModel extends PersonModel {
    
}