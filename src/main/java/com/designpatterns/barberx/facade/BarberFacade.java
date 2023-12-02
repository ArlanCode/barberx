package com.designpatterns.barberx.facade;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.barberx.dtos.BarberRecordDto;
import com.designpatterns.barberx.erro.DuplicateUsernameException;
import com.designpatterns.barberx.erro.PersonNotFoundException;
import com.designpatterns.barberx.models.BarberModel;
import com.designpatterns.barberx.repositories.IBarberRepository;

@Service
public class BarberFacade {
    @Autowired
    IBarberRepository barberRepository;


    public BarberModel createBarber(BarberRecordDto barberRecordDto){
            if (this.barberRepository.findByUsername(barberRecordDto.username()).isPresent()) {
                throw new DuplicateUsernameException("This barber username is already in use");
            }

            var barber = new BarberModel();
            BeanUtils.copyProperties(barberRecordDto, barber);
            
            barberRepository.save(barber);
            return barber;
    }

    public List<BarberModel> getAllBarbers() {
        return barberRepository.findAll();
    }

    public BarberModel getBarberById(Long barberId) {
        return barberRepository.findById(barberId)
            .orElseThrow(() -> new PersonNotFoundException("Barber not found with ID: " + barberId));
    }

    public void deleteBarber(Long barberId){
        barberRepository.delete(getBarberById(barberId));
    }
}