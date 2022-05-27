package com.enfedaque.fluxAPI.controller;


import com.enfedaque.fluxAPI.domain.dto.vehiculosDTO;
import com.enfedaque.fluxAPI.domain.vehiculos;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.excepciones.facturasNotFoundException;
import com.enfedaque.fluxAPI.excepciones.respuestaErrores;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class vehiculosController {

    private final Logger logger = LoggerFactory.getLogger(vehiculosController.class);

    @Autowired
    private com.enfedaque.fluxAPI.service.clientesService clientesService;
    @Autowired
    private com.enfedaque.fluxAPI.service.vehiculosService vehiculosService;

    //AÑADIR nuevo vehiculo
    @PostMapping("/Vehiculos")
    public vehiculos addVehiculo(@RequestBody vehiculosDTO vehiculosDTO) throws clienteNotFoundException, facturasNotFoundException {
        logger.info("Inicio AddVehiculo");
        vehiculos miVehiculo= vehiculosService.addVehiculos(vehiculosDTO);
        logger.info("Vehiculo con id: " + miVehiculo.getVehiculosID() + " añadido. FIN de la operación");
        return miVehiculo;
    }

    //BORRAR vehiculo
    @DeleteMapping("/Vehiculos/{id}")
    public vehiculos deleteVehiculo(@PathVariable long id) throws vehiculoNotFoundException {
        logger.info("Inicio deleteVehiculo");
        vehiculos miVehiculo= vehiculosService.deleteVehiculos(id);
        logger.info("Vehiculo con id: " + miVehiculo.getVehiculosID() + " eliminado. FIN de la operación");
        return miVehiculo;
    }

    //MODIFICAR un vehiculo por el id
    //TODO Terminado, sin probar
    @PutMapping("/Vehiculos/{id}")
    public vehiculos modifyVehiculo(@RequestBody vehiculosDTO vehiculosDTO, @PathVariable long id)
            throws vehiculoNotFoundException, clienteNotFoundException {
        logger.info("Inicio modificar vehiculo con id: " + id);
        vehiculos miVehiculo= vehiculosService.modifyVehiculos(vehiculosDTO, id);
        logger.info("Vehiculo con id: " + miVehiculo.getVehiculosID() + " modificado. FIN de la operación");
        return miVehiculo;
    }

    //Metodo que me devuelva el TOTAL DE vehiculos
    @GetMapping("/Vehiculos") //Forma de buscarlo en el navegador
    public List<vehiculos> getVehiculos(){
        logger.info("Inicio getVehiculos");
        List<vehiculos> vehiculos=vehiculosService.findAll();
        logger.info("Fin operacion de mostrado de vehiculos");
        return vehiculos;
    }

    //Metodo que me devuelve un VEHICULO SEGUN ID
    @GetMapping("/Vehiculos/{id}")
    public vehiculos getVehiculo(@PathVariable long id) throws vehiculoNotFoundException {
        logger.info("Inicio busqueda de vehiculo con id: " + id);
        vehiculos miVehiculo=vehiculosService.findById(id);
        logger.info("Fin de la operacion de busqueda");
        return miVehiculo;
    }

    //Metodo que me deja buscar indicandole 3 campos
    @GetMapping("/Vehiculos/{matricula}/{kilometros}/{antiguedad}")
    public List<vehiculos> getVehiculoss(@PathVariable String matricula, @PathVariable float kilometros,
                                         @PathVariable int antiguedad){

        logger.info("Inicio busqueda de vehiculos con parametros -matricula- : " + matricula + " , " +
                "-kilometros- : " + kilometros + " , -antiguedad- : " + antiguedad);
        List<vehiculos> mivehiculo=vehiculosService.findByMatriculaAndKilometrosAndAntiguedad(matricula, kilometros, antiguedad);
        logger.info("Fin de la operacion de busqueda");
        return mivehiculo;
    }

    //Metodo que modifica solo 1 parametro
    @PatchMapping("/Vehiculos/{id}/{kilometros}")
    public vehiculos modifyKilometros(@PathVariable long id, @PathVariable float kilometros) throws vehiculoNotFoundException {
        logger.info("Inicio modificacion de kilometros de vehiculo con id: " + id);
        vehiculos miVehiculo=vehiculosService.modifyKilometros(kilometros, id);
        logger.info("Vehiculo con id: " + id + " modificado. FIN de la operación");
        return miVehiculo;
    }

    //Me devuelve los vehiculos con un solo propietario pidiendolo con JPQL
    @GetMapping("/BusquedaVehiculos/{unicoPropietario}")
    public List<vehiculos> findByPropietario(@PathVariable boolean unicoPropietario){
        logger.info("Inicio findByPropieatrio");
        List<vehiculos> vehiculos=vehiculosService.findByPropietario(unicoPropietario);
        logger.info("Fin operacion de mostrado de vehiculos");
        return vehiculos;
    }

    //Me devuelve las fechas de entrada a traves de la matricula con SQL NATIVA
    @GetMapping("/BuscarVehiculos/{matricula}")
    public List<LocalDate> busquedaVariada(@PathVariable String matricula){
        logger.info("Inicio busquedaVariada");
        List<LocalDate> vehiculos=vehiculosService.busquedaVariada(matricula);
        logger.info("Fin operacion de mostrado de vehiculos");
        return vehiculos;
    }

    /*
    AQUI GESTIONO LAS EXCEPCIONES Y LAS CAPTURO
     */
    @ExceptionHandler(vehiculoNotFoundException.class)
    public ResponseEntity<respuestaErrores> HandlerVehiculoNoEncontrado(vehiculoNotFoundException vnfe){
        respuestaErrores miRespuestaErrores=new respuestaErrores("404", vnfe.getMessage());
        logger.error(vnfe.getMessage(), vnfe);
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
