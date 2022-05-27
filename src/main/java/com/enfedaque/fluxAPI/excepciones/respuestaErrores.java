package com.enfedaque.fluxAPI.excepciones;

import lombok.Data;

@Data

public class respuestaErrores {

    private String error;
    private String mensaje;

    public respuestaErrores(String error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    //Casi nunca me funciona el @Data
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



}
