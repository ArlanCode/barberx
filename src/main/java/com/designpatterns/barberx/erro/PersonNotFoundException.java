package com.designpatterns.barberx.erro;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String messag){
        super(messag);
    }
}
