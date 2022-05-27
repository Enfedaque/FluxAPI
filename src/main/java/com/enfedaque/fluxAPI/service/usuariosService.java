package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.usuarios;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;

import java.util.List;

public interface usuariosService {

    /*METODOS QUE QUIERO QUE TENGA MI SERVICIO WEB
    Es la parte logica de la aplicacion
     */

    List<usuarios> findAll();
    usuarios findById(long id) throws usuarioNotFoundException;
    usuarios deleteUsuario(long id) throws usuarioNotFoundException;
    usuarios addUsuario(usuarios usuario);
    usuarios modifyUsuario(usuarios usuario, long id) throws usuarioNotFoundException;

    List<usuarios> findByNombre(String nombre);

    List<String> busquedaVariada(String nombre, String telefono);
}
