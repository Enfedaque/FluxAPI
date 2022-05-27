package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.dto.vehiculosDTO;
import com.enfedaque.fluxAPI.domain.vehiculos;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.excepciones.facturasNotFoundException;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface vehiculosService {

    List<vehiculos> findAll();
    vehiculos deleteVehiculos(long id) throws vehiculoNotFoundException;
    vehiculos modifyVehiculos(vehiculosDTO vehiculosDTO, long id) throws vehiculoNotFoundException, clienteNotFoundException;
    vehiculos addVehiculos(vehiculosDTO vehiculosDTO) throws clienteNotFoundException, facturasNotFoundException;
    vehiculos findById(long id) throws vehiculoNotFoundException;

    List<vehiculos> findByMatriculaAndKilometrosAndAntiguedad(String matricula, float kilometros, int antiguedad);

    vehiculos modifyKilometros(float kilometros, long id) throws vehiculoNotFoundException;

    List<vehiculos> findByPropietario(boolean unicoPropietario);

    List<LocalDate> busquedaVariada(String matricula);
}
