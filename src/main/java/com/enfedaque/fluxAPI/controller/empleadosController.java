package com.enfedaque.fluxAPI.controller;


import com.enfedaque.fluxAPI.domain.empleados;
import com.enfedaque.fluxAPI.excepciones.empleadoNotFoundException;
import com.enfedaque.fluxAPI.excepciones.respuestaErrores;
import com.enfedaque.fluxAPI.service.empleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class empleadosController {

    private final Logger logger = LoggerFactory.getLogger(empleadosController.class);

    @Autowired
    private empleadoService miEmpleadoService;

    //AÑADIR nuevo empleado
    @PostMapping("/Empleados")
    public empleados addEmpleado(@RequestBody empleados empleado){
        logger.info("Inicio AddEmpleado");
        empleados miEmpleado= miEmpleadoService.addEmpleado(empleado);
        logger.info("Empleado con id: " + miEmpleado.getId() + " añadido. FIN de la operación");
        return miEmpleado;
    }

    //BORRAR cliente
    @DeleteMapping("/Empleados/{id}")
    public empleados deleteEmpleado(@PathVariable long id) throws empleadoNotFoundException {
        logger.info("Inicio deleteEmpleado");
        empleados miEmpleado= miEmpleadoService.deleteEmpleado(id);
        logger.info("Empleado con id: " + miEmpleado.getId() + " eliminado. FIN de la operación");
        return miEmpleado;
    }

    //MODIFICAR un cliente por el id
    @PutMapping("/Empleados/{id}")
    public empleados modifyEmpleado(@RequestBody empleados empleado, @PathVariable long id)
            throws empleadoNotFoundException {
        logger.info("Inicio modificar empleado con id: " + id);
        empleados empleados=miEmpleadoService.modifyEmpleado(empleado, id);
        logger.info("Empleado con id: " + empleados.getId() + " modificada. FIN de la operación");
        return empleados;
    }

    //Metodo que me devuelva el TOTAL DE EMPLEADOS
    @GetMapping("/Empleados") //Forma de buscarlo en el navegador
    public List<empleados> getempleados(){
        logger.info("Inicio getEmpleados");
        List<empleados> empleados=miEmpleadoService.findAll();
        logger.info("Fin operacion de mostrado de empleados");
        return empleados;
    }

    //Metodo que me devuelve un EMPLEADO SEGUN ID
    @GetMapping("/Empleados/{id}")
    public empleados getEmpleado(@PathVariable long id) throws empleadoNotFoundException {
        logger.info("Inicio busqueda de empleado con id: " + id);
        empleados miEmpleado=miEmpleadoService.findById(id);
        logger.info("Fin de la operacion de busqueda");
        return miEmpleado;
    }

    //Metodo que me deja buscar indicandole 3 campos
    @GetMapping("/Empleados/{puesto}/{departamento}/{salario}")
    public List<empleados> getEmpleadoss(@PathVariable String puesto, @PathVariable String departamento,
                                       @PathVariable float salario){

        logger.info("Inicio busqueda de empleado con parametros -puesto- : " + puesto + " , " +
                "-departamento- : " + departamento + " , -salario- : " + salario);
        List<empleados> miEmpleado=miEmpleadoService.findByPuestoAndDepartamentoAndSalario(puesto, departamento, salario);
        logger.info("Fin de la operacion de busqueda");
        return miEmpleado;
    }

    //Metodo que modifica solo 1 parametro
    @PatchMapping("/Empleados/{id}/{salario}")
    public empleados modifySalario(@PathVariable long id, @PathVariable float salario) throws empleadoNotFoundException {
        logger.info("Inicio modificacion de salario de empleado con id: " + id);
        empleados miEmpleado=miEmpleadoService.modifySalario(salario, id);
        logger.info("Empleado con id: " + id + " modificado. FIN de la operación");
        return miEmpleado;
    }

    //Metodo que me devuelve los empleados con x operariosAlCargo
    @GetMapping("/BusquedaEmpleados/{operariosAlCargo}") //Forma de buscarlo en el navegador
    public List<empleados> findByOperarios(@PathVariable int operariosAlCargo){
        logger.info("Inicio findByOperarios");
        List<empleados> empleados=miEmpleadoService.findByOperarios(operariosAlCargo);
        logger.info("Fin operacion de mostrado de empleados segun operarios al cargo");
        return empleados;
    }

    //Buscar el puesto y el departamento a traves del salario con SQL
    @GetMapping("/BuscarEmpleados/{salario}")
    public List<String> buscarPuestoYDepart(@PathVariable float salario){
        logger.info("Inicio buscarPuestoYDepart");
        List<String> empleados=miEmpleadoService.buscarPuestoYDepart(salario);
        logger.info("Fin operacion de mostrado de empleados");
        return empleados;
    }

    /*
    AQUI GESTIONO LAS EXCEPCIONES Y LAS CAPTURO
     */
    @ExceptionHandler(empleadoNotFoundException.class)
    public ResponseEntity<respuestaErrores> HandlerEmpleadoNoEncontrado(empleadoNotFoundException enfe){
        respuestaErrores miRespuestaErrores=new respuestaErrores("404", enfe.getMessage());
        logger.error(enfe.getMessage(), enfe);
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
