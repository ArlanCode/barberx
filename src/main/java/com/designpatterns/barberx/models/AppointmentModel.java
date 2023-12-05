package com.designpatterns.barberx.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.designpatterns.barberx.state.EnumState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberModel barber;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumState enumState;

    public void setEnumState(EnumState enumState){
        this.enumState = enumState;
    }

    private LocalDateTime appointmentDateTime;
}
