package com.enfedaque.fluxAPI.excepciones;

public class empleadoNotFoundException extends Exception {
    private static final String EMPLEADO_NO_EXISTE= "Este empleado no existe";

    public empleadoNotFoundException(String message) {
        super(message);
    }

    public empleadoNotFoundException(){
        super(EMPLEADO_NO_EXISTE);
    }
}
