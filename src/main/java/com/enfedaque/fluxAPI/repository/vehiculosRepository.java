package com.enfedaque.fluxAPI.repository;


import com.enfedaque.fluxAPI.domain.vehiculos;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface vehiculosRepository extends CrudRepository<vehiculos, Long> {

    List<vehiculos> findAll();

    List<vehiculos> findByMatriculaAndKilometrosAndAntiguedad(String matricula, float kilometros, int antiguedad);

    //JPQL
    @Query("SELECT veh FROM vehiculos veh WHERE unicoPropietario= :unicoPropietario")
    List<vehiculos> findByPropietario(boolean unicoPropietario);

    //SQL NATIVAS
    @Query(value = "SELECT fechaEntrada FROM vehiculos WHERE matricula= :matricula")
    List<LocalDate> busquedaVariada(String matricula);
}
