package com.enfedaque.fluxAPI.excepciones;

public class usuarioNotFoundException extends  Exception{

    private static final String USUARIO_NO_EXISTE= "Este usuario no existe";

    public usuarioNotFoundException(String message) {
        super(message);
    }

    public usuarioNotFoundException() {
        super(USUARIO_NO_EXISTE);
    }
    
}
