package com.enfedaque.fluxAPI.excepciones;

public class vehiculoNotFoundException extends Exception {
    private static final String VEHICULO_NO_EXISTE= "Este vehiculo no existe";

    public vehiculoNotFoundException(String message) {
        super(message);
    }

    public vehiculoNotFoundException(){
        super(VEHICULO_NO_EXISTE);
    }
}
