package com.enfedaque.fluxAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class facturasDTO {

    @NotNull
    private String nombrePropietario;
    @NotNull
    private boolean isEmpresa;
    @PositiveOrZero
    private float precio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFactura;
    private long vehiculo_id;



    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public boolean isEmpresa() {
        return isEmpresa;
    }

    public void setEmpresa(boolean empresa) {
        isEmpresa = empresa;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public long getVehiculo() {
        return vehiculo_id;
    }

    public void setVehiculo(long vehiculo) {
        this.vehiculo_id = vehiculo;
    }
}
