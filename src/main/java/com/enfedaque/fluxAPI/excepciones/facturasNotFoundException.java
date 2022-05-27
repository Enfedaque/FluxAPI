package com.enfedaque.fluxAPI.excepciones;

public class facturasNotFoundException extends Exception {
    private static final String FACTURA_NO_EXISTE = "Esta factura no existe";

    public facturasNotFoundException(String message) {
        super(message);
    }

    public facturasNotFoundException(){
        super(FACTURA_NO_EXISTE);
    }
}
