package com.enfedaque.fluxAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class vehiculosDTO {
    /*
    El DTO es la forma que tengo de pasarle los datos en JSON, ya que no le puedo pasar un objeto entero
    como atricuto por ejemplo
     */
    private long cliente_id; //Le paso el id, y no el objeto entero
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEntrada;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;
    private String matricula;
    private float kilometros;
    private int antiguedad;
    private boolean unicoPropietario;






    public vehiculosDTO(long cliente_id, LocalDate fechaEntrada, LocalDate fechaSalida, String matricula, float kilometros, int antiguedad, boolean unicoPropietario) {
        this.cliente_id = cliente_id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.matricula = matricula;
        this.kilometros = kilometros;
        this.antiguedad = antiguedad;
        this.unicoPropietario = unicoPropietario;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getKilometros() {
        return kilometros;
    }

    public void setKilometros(float kilometros) {
        this.kilometros = kilometros;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isUnicoPropietario() {
        return unicoPropietario;
    }

    public void setUnicoPropietario(boolean unicoPropietario) {
        this.unicoPropietario = unicoPropietario;
    }

    public long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
