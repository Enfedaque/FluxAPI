package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.usuarios;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface usuariosService {

    /*METODOS QUE QUIERO QUE TENGA MI SERVICIO WEB
    Es la parte logica de la aplicacion
     */

    Flux<usuarios> findAll();
    Mono<usuarios> findById(long id) throws usuarioNotFoundException;
    Mono<usuarios> deleteUsuario(long id) throws usuarioNotFoundException;
    Mono<usuarios> addUsuario(usuarios usuario);
    Mono<usuarios> modifyUsuario(usuarios usuario, long id) throws usuarioNotFoundException;

    Flux<usuarios> findByNombre(String nombre);

    Flux<String> busquedaVariada(String nombre, String telefono);
}
