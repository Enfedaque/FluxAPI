package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.dto.facturasDTO;
import com.enfedaque.fluxAPI.domain.facturas;
import com.enfedaque.fluxAPI.excepciones.facturasNotFoundException;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;

import java.util.List;

public interface facturasService {

    List<facturasDTO> findAll();
    facturas findById(long id) throws facturasNotFoundException;
    facturas deleteFactura(long id) throws facturasNotFoundException;
    facturas addFactura(facturasDTO facturasDTO) throws vehiculoNotFoundException;
    facturas modifyFactura(facturasDTO facturasDTO, long id) throws facturasNotFoundException, vehiculoNotFoundException;

    List<facturas> findByNombrePropietarioAndIsEmpresaAndPrecio(String nombrePropietario, boolean isEmpresa, float precio);

    facturas modifyPrecio(float precio, long id) throws facturasNotFoundException;

    List<String> findByNomprePro(String nombrePropietario);

    String buscarDuenoFactura(long numFactura);
}
