package com.enfedaque.fluxAPI.controller;


import com.enfedaque.fluxAPI.domain.dto.facturasDTO;
import com.enfedaque.fluxAPI.domain.facturas;
import com.enfedaque.fluxAPI.excepciones.facturasNotFoundException;
import com.enfedaque.fluxAPI.excepciones.respuestaErrores;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;
import com.enfedaque.fluxAPI.service.facturasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class facturasController {

    private final Logger logger = LoggerFactory.getLogger(facturasController.class);

    @Autowired
    private com.enfedaque.fluxAPI.service.facturasService facturasService;

    @GetMapping("/Facturas") //Forma de buscarlo en el navegador
    public List<facturasDTO> getFacturas(){
        logger.info("Inicio getFacturas");
        List<facturasDTO> factura=facturasService.findAll();
        logger.info("Fin operacion de mostrado de facturas");
        return factura;
    }

    @PostMapping("/Facturas")
    public facturas addFactura(@RequestBody facturasDTO facturasDTO) throws vehiculoNotFoundException {
        logger.info("Inicio AddFactura");
        facturas miFactura= facturasService.addFactura(facturasDTO);
        logger.info("Factura con id: " + miFactura.getNumFactura() + " añadida. FIN de la operación");
        return miFactura;
    }

    @DeleteMapping("/Facturas/{id}")
    public facturas deleteFactura(@PathVariable long id) throws facturasNotFoundException {
        logger.info("Inicio deleteFactura");
        facturas miFactura= facturasService.deleteFactura(id);
        logger.info("Factura con id: " + miFactura.getNumFactura() + " eliminada. FIN de la operación");
        return miFactura;
    }

    @PutMapping("/Facturas/{id}")
    public facturas modifyFactura(@RequestBody facturasDTO facturaDTO, @PathVariable long id)
            throws usuarioNotFoundException, facturasNotFoundException, vehiculoNotFoundException {
        logger.info("Inicio modificar factura con id: " + id);
        facturas miFactura= facturasService.modifyFactura(facturaDTO, id);
        logger.info("Factura con id: " + miFactura.getNumFactura() + " modificada. FIN de la operación");
        return miFactura;
    }

    @GetMapping("/Facturas/{id}")
    public facturas getFactura(@PathVariable long id) throws facturasNotFoundException {
        logger.info("Inicio busqueda de factura con id: " + id);
        facturas miFactura=facturasService.findById(id);
        logger.info("Fin de la operacion de busqueda");
        return miFactura;
    }

    //Metodo que me deja buscar indicandole 3 campos
    @GetMapping("/Facturas/{nombrePropietario}/{isEmpresa}/{precio}")
    public List<facturas> getFacturass(@PathVariable String nombrePropietario, @PathVariable boolean isEmpresa,
                                         @PathVariable float precio){

        logger.info("Inicio busqueda de facturas con parametros -nombrePropietario- : " + nombrePropietario + " , " +
                "-isEmpresa- : " + isEmpresa + " , -precio- : " + precio);
        List<facturas> miFactura=facturasService.findByNombrePropietarioAndIsEmpresaAndPrecio(nombrePropietario, isEmpresa, precio);
        logger.info("Fin de la operacion de busqueda");
        return miFactura;
    }

    //Metodo que modifica solo 1 parametro
    @PatchMapping("/Facturas/{id}/{precio}")
    public facturas modifyPrecio(@PathVariable long id, @PathVariable float precio) throws facturasNotFoundException {
        logger.info("Inicio modificacion de precio de factura con id: " + id);
        facturas miFactura=facturasService.modifyPrecio(precio, id);
        logger.info("Factura con id: " + id + " modificada. FIN de la operación");
        return miFactura;
    }

    //Consultar el precio de las facturas de 1 propietario con JPQL
    @GetMapping("/BusquedaFacturas/{nombrePropietario}")
    public List<String> findByNombrePro(@PathVariable String nombrePropietario){
        logger.info("Inicio findByNombrePro");
        List<String> factura=facturasService.findByNomprePro(nombrePropietario);
        logger.info("Fin operacion de mostrado de facturas");
        return factura;
    }

    //Consultar el dueño de una factura con SQL NATIVA
    @GetMapping("/BuscarFacturas/{numFactura}")
    public String buscarDuenoFactura(@PathVariable long numFactura){
        logger.info("Inicio buscarDuenoFactura");
        String factura=facturasService.buscarDuenoFactura(numFactura);
        logger.info("Fin operacion de mostrado de facturas");
        return factura;
    }
    /*

    AQUI GESTIONO LAS EXCEPCIONES Y LAS CAPTURO
     */
    @ExceptionHandler(facturasNotFoundException.class)
    public ResponseEntity<respuestaErrores> HandlerFacturaNoEncontrado(facturasNotFoundException fnfe){
        respuestaErrores miRespuestaErrores=new respuestaErrores("404", fnfe.getMessage());
        logger.error(fnfe.getMessage(), fnfe);
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
