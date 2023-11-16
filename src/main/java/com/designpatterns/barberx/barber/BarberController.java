package com.designpatterns.barberx.barber;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/barbers")
public class BarberController {

    @Autowired
    IBarberRepository barberRepository;
    
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid BarberRecordDto barberRecordDto){

        var barberModel = new BarberModel();
        BeanUtils.copyProperties(barberRecordDto, barberModel);

        var user = this.barberRepository.findByUsername(barberModel.getUsername());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", "Usuário existente", "details", "Esse 'username' já pertence a outro cliente"));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(this.barberRepository.save(barberModel));


    }

}
