package com.enfedaque.fluxAPI.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "facturas")
public class facturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numFactura;
    @Column
    private String nombrePropietario;
    @Column(name = "is_empresa")
    private boolean isEmpresa;
    @Column
    private float precio;
    @Column(name = "fecha_factura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFactura;
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private vehiculos vehiculo;

    public long getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(long numFactura) {
        this.numFactura = numFactura;
    }

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

    public vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }
}
