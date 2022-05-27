package com.enfedaque.fluxAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class clientesDTO {
    /*
    El DTO es la forma que tengo de pasarle los datos en JSON, ya que no le puedo pasar un objeto entero
    como atricuto por ejemplo
     */
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String DNI;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNac;
    private boolean particular;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaAlta;
    private int presupuestoEnReparaciones;
    private float estatura;
    private boolean empresa;
    private String nombreEmpresa;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getPresupuestoEnReparaciones() {
        return presupuestoEnReparaciones;
    }

    public void setPresupuestoEnReparaciones(int presupuestoEnReparaciones) {
        this.presupuestoEnReparaciones = presupuestoEnReparaciones;
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

    public String NombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
