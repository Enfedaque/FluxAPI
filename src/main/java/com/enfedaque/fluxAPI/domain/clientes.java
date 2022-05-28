package com.enfedaque.fluxAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "Clientes")
public class clientes extends usuarios {

    @Column
    @NotNull
    private boolean particular;
    @Column(name = "fecha_alta")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    @Column(name="presupuesto")
    @Positive
    private int presupuesto; //No se que dato puedo guardar sino como int en un cliente...
    @Column
    @Positive
    private float estatura; //No se que dato poder coger, no hay nada... para float
    @Column
    @NotNull
    private boolean empresa;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    //1 cliente tiene 1 o "n" vehiculos, que los voy a guardar en una lista
    @OneToMany (mappedBy = "cliente")
    @JsonBackReference
    private List<vehiculos> vehiculos;







    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public boolean isParticular() {
        return particular;
    }

    public void setParticular(boolean particular) {
        this.particular = particular;
    }

    public boolean isEmpresa() {
        return empresa;
    }

    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public List<vehiculos> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<vehiculos> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
