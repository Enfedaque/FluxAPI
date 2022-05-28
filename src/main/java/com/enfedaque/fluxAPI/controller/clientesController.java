package com.enfedaque.fluxAPI.controller;


import com.enfedaque.fluxAPI.domain.clientes;
import com.enfedaque.fluxAPI.domain.dto.clientesDTO;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.excepciones.respuestaErrores;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;
import com.enfedaque.fluxAPI.service.clientesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class clientesController {

    private final Logger logger = LoggerFactory.getLogger(clientesController.class);

    @Autowired //Spring lo inicializa y hace todas las cosas sin tener que hacer el new
    private clientesService miClienteService;
    @Autowired
    private com.enfedaque.fluxAPI.service.vehiculosService vehiculosService;

    //AÑADIR nuevo cliente
    @PostMapping("/Clientes")
    public clientes addCliente(@Valid @RequestBody clientesDTO clienteDTO) throws vehiculoNotFoundException {
        logger.info("Inicio AddCliente");
        clientes miCliente= miClienteService.addCliente(clienteDTO);
        logger.info("Fin AddCliente. Cliente añadido");
        return miCliente;
    }

    //BORRAR cliente

    @DeleteMapping("/Clientes/{id}")
    public clientes deleteCliente(@PathVariable long id) throws clienteNotFoundException {
        logger.info("Inicio deleteCliente");
        clientes miCliente= miClienteService.deleteCliente(id);
        logger.info("Cliente con id: " + miCliente.getId() + " eliminado. FIN de la operación");
        return miCliente;
    }

    //MODIFICAR un cliente por el id

    @PutMapping("/Clientes/{id}")
    public clientes modifyCliente(@Valid @RequestBody clientesDTO clientesDTO, @PathVariable long id)
            throws clienteNotFoundException {
        logger.info("Inicio modificar cliente con id: " + id);
        clientes miCliente= miClienteService.modifyCliente(clientesDTO, id);
        logger.info("Cliente con id: " + miCliente.getId() + " modificado. FIN de la operación");
        return miCliente;
    }

    //Metodo que me devuelva el TOTAL DE CLIENTES

    @GetMapping("/Clientes") //Forma de buscarlo en el navegador
    public List<clientes> getClientes(){
        logger.info("Inicio getClientes");
        List<clientes> clientes=miClienteService.findAll();
        logger.info("Fin operacion de mostrado de clientes");
        return clientes;
    }

    //Metodo que me devuelve un CLIENTE SEGUN ID

    @GetMapping("/Clientes/{id}")
    public clientesDTO getCliente(@PathVariable long id) throws clienteNotFoundException {
        logger.info("Inicio busqueda de cliente con id: " + id);
        clientesDTO miCliente=miClienteService.findById(id);
        logger.info("Fin de la operacion de busqueda");
        return miCliente;
    }

    //Metodo que modifica solo 1 parametro
    @PatchMapping("/Clientes/{id}/{presupuesto}")
    public clientes modifyPresupuesto(@PathVariable long id, @PathVariable int presupuesto) throws clienteNotFoundException {
        logger.info("Inicio modificacion de presupuesto de cliente con id: " + id);
        clientes miCliente=miClienteService.modifyPresupuesto(presupuesto, id);
        logger.info("Cliente con id: " + id + " modificado. FIN de la operación");
        return miCliente;
    }

    //Metodo que me deja buscar indicandole 3 campos
    @GetMapping("/Clientes/{particular}/{presupuesto}/{nombreEmpresa}")
    public List<clientes> getClientess(@PathVariable boolean particular, @PathVariable int presupuesto,
                                  @PathVariable String nombreEmpresa){

        logger.info("Inicio busqueda de cliente con parametros -particular- : " + particular +" , " +
                "-presupuesto- : " + presupuesto + " , -nombreEmpresa- : " + nombreEmpresa);
        List<clientes> miCliente=miClienteService.findByParticularAndPresupuestoAndNombreEmpresa(particular, presupuesto, nombreEmpresa);
        logger.info("Fin de la operacion de busqueda");
        return miCliente;
    }


    /*
    AQUI GESTIONO LAS EXCEPCIONES Y LAS CAPTURO
     */
    @ExceptionHandler(clienteNotFoundException.class)
    public ResponseEntity<respuestaErrores> HandlerClienteNoEncontrado(clienteNotFoundException cnfe){
        respuestaErrores miRespuestaErrores=new respuestaErrores("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        return new ResponseEntity<>(miRespuestaErrores, HttpStatus.NOT_FOUND);
    }

    //Gestor de excepciones generico para fallos que no tenga pensados
    @ExceptionHandler
    public ResponseEntity<respuestaErrores> excepcionGenerica(Exception exception){
        respuestaErrores miRespuestaErrores=new respuestaErrores("x", "Error en el lado servidor");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(miRespuestaErrores, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException manve){
        Map<String, String> error=new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(errores -> {
            error.put(((FieldError) errores).getField(), errores.getDefaultMessage());
        });
        return error;
    }
}
