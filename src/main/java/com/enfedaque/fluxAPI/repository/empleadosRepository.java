package com.enfedaque.fluxAPI.repository;


import com.enfedaque.fluxAPI.domain.empleados;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface empleadosRepository extends CrudRepository<empleados, Long> {

    //empleados findById(long id);
    List<empleados> findAll();

    List<empleados> findByPuestoAndDepartamentoAndSalario(String puesto, String departamento, float salario);

    //JPQL
    @Query("SELECT emple FROM empleados emple WHERE operariosAlCargo= :operariosAlCargo")
    List<empleados> findByOperarios(int operariosAlCargo);

    //SQL NATIVAS
    @Query(value = "SELECT puesto, departamento FROM empleados WHERE salario= :salario")
    List<String> buscarPuestoYDepart(float salario);
}
