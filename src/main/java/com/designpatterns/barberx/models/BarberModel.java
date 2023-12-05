package com.designpatterns.barberx.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_BARBERS")
public class BarberModel extends PersonModel{

}
