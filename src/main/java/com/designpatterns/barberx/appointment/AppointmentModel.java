package com.designpatterns.barberx.appointment;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.designpatterns.barberx.barber.BarberModel;
import com.designpatterns.barberx.customer.ClientModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_APPOINTMENTS")
@Data
public class AppointmentModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberModel barber;

    private LocalDateTime appointmentDateTime;
}
