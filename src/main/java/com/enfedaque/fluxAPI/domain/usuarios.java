package com.enfedaque.fluxAPI.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data //Con esta anotacion de lombock hago que todos los atributos tengasn get y set
@AllArgsConstructor //Creo constructor completo
@NoArgsConstructor //Creo constructor vacio
//Le indico a la bbdd que es una tabla
@Document(value = "users")
public class usuarios {

    //Le indico las columnas y el ID autogenerado
    @Id
    private String id;
    @Field
    @NotNull
    private String nombre;
    @Field
    @NotNull
    private String apellido;
    @Field
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String DNI;
    @Field
    @Email
    private String email;
    @Field
    @NotNull
    private String telefono;
    @Field
    private LocalDate fechaNac;

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }
}
