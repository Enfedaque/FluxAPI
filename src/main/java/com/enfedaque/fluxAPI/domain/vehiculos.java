package com.enfedaque.fluxAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "vehiculos")
public class vehiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehiculosID;

    //1 cliente tiene 1 o varios vehiculos
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private clientes cliente;

    @OneToOne(mappedBy = "vehiculo")
    @JsonBackReference
    private facturas factura;

    @Column(name = "fecha_entrada")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaEntrada;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;
    @Column
    @NotEmpty
    private String matricula;
    @Column
    @Min(value = 1)
    private float kilometros;
    @Column
    @Min(value = 1)
    private int antiguedad;
    @Column
    private boolean unicoPropietario;

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

    public long getVehiculosID() {
        return vehiculosID;
    }

    public void setVehiculosID(long vehiculosID) {
        this.vehiculosID = vehiculosID;
    }

    public clientes getCliente() {
        return cliente;
    }

    public void setCliente(clientes cliente) {
        this.cliente = cliente;
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

    public facturas getFactura() {
        return factura;
    }

    public void setFactura(facturas factura) {
        this.factura = factura;
    }
}
