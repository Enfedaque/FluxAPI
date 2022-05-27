package com.enfedaque.fluxAPI.excepciones;

public class clienteNotFoundException extends Exception {

    private static final String CLIENTE_NO_EXISTE= "Este cliente no existe";

    public clienteNotFoundException(String message) {
        super(message);
    }

    public clienteNotFoundException(){
        super(CLIENTE_NO_EXISTE);
    }
}
