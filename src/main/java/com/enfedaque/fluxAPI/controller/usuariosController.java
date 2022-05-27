package com.enfedaque.fluxAPI.controller;


import com.enfedaque.fluxAPI.domain.usuarios;
import com.enfedaque.fluxAPI.excepciones.respuestaErrores;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;
import com.enfedaque.fluxAPI.service.usuariosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class usuariosController {

    private final Logger logger = LoggerFactory.getLogger(usuariosController.class);

    //Parte con la que interactue el usuario
    //Metodos invocables desde cualquier navegador

    @Autowired //Spring lo inicializa y hace todas las cosas sin tener que hacer el new
    private usuariosService miUsuariosService;

    //Mostrar todos los usuarios
    @GetMapping("/Usuarios") //Forma de buscarlo en el navegador
    public Flux<usuarios> getUsuarios(){
        logger.info("Inicio getUsuarios");
        Flux<usuarios> usuarios=miUsuariosService.findAll();
        logger.info("Fin operacion de mostrado de usuarios");
        return usuarios;
    }

    //Buscar un usuario segun ID
    @GetMapping("/Usuarios/{id}")
    public Mono<usuarios> getUsuario(@PathVariable long id) throws usuarioNotFoundException {
        logger.info("Inicio busqueda de usuario con id: " + id);
        Mono<usuarios> miUsuarios=miUsuariosService.findById(id);
        logger.info("Fin de la operacion de busqueda");
        return miUsuarios;
    }

    //BORRAR un usuario por el id
    @DeleteMapping("/Usuarios/{id}")
    public Mono<usuarios> deleteUsuario(@PathVariable long id) throws usuarioNotFoundException {
        logger.info("Inicio deleteUsuario");
        Mono<usuarios> miUsuario=miUsuariosService.deleteUsuario(id);
        return miUsuario;
    }

    //TODO, No creo el metodo de registrar usuarios ni el de modificar porque no tiene sentido, lo que se
    // registran son Clientesy Empleados, que juntos componen los usurios. Por eso los
    // usuarios si se pueden mostrar, buscar y eliminar, pero no registrar y modificar,
    // ya que estas funciones se realizan en la clase pertinente*/

    //Buscar usuarios por el nombre con JPQL
    @GetMapping("/BusquedaUsuarios/{nombre}")
    public Flux<usuarios> findByNombre(@PathVariable String nombre){
        logger.info("Inicio findByNombre");
        Flux<usuarios> usuarios=miUsuariosService.findByNombre(nombre);
        logger.info("Fin operacion de mostrado de usuarios");
        return usuarios;
    }

    //Buscar email y DNI por el nombre y telefono con SQL NATIVA
    @GetMapping("/BusquedaUsuarios/{nombre}/{telefono}")
    public Flux<String> busquedaVariada(@PathVariable String nombre, @PathVariable String telefono){
        logger.info("Inicio busquedaVariada");
        Flux<String> usuarios=miUsuariosService.busquedaVariada(nombre, telefono);
        logger.info("Fin operacion de mostrado de usuarios");
        return usuarios;
    }

    /*
    AQUI GESTIONO LAS EXCEPCIONES Y LAS CAPTURO
     */
    @ExceptionHandler(usuarioNotFoundException.class)
    public ResponseEntity<respuestaErrores> HandlerUsuarioNoEncontrado(usuarioNotFoundException unfe){
        respuestaErrores miRespuestaErrores=new respuestaErrores("404", unfe.getMessage());
        logger.error(unfe.getMessage(), unfe);
        return new ResponseEntity<>(miRespuestaErrores, HttpStatus.NOT_FOUND);
    }

    //Gestor de excepciones generico para fallos que no tenga pensados
    @ExceptionHandler
    public ResponseEntity<respuestaErrores> excepcionGenerica(Exception exception){
        respuestaErrores miRespuestaErrores=new respuestaErrores("x", "Error en el lado servidor");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(miRespuestaErrores, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
