package com.designpatterns.barberx.barber;

import com.designpatterns.barberx.person.PersonModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_BARBERS")
public class BarberModel extends PersonModel {

}
