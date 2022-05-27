package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.empleados;
import com.enfedaque.fluxAPI.excepciones.empleadoNotFoundException;

import java.util.List;

public interface empleadoService {

    empleados addEmpleado(empleados empleado);
    empleados deleteEmpleado(long id) throws empleadoNotFoundException;
    empleados modifyEmpleado(empleados empleado, long id) throws empleadoNotFoundException;
    List<empleados> findAll();
    empleados findById(long id) throws empleadoNotFoundException;

    List<empleados> findByPuestoAndDepartamentoAndSalario(String puesto, String departamento, float salario);

    empleados modifySalario(float salario, long id) throws empleadoNotFoundException;

    List<empleados> findByOperarios(int operariosAlCargo);

    List<String> buscarPuestoYDepart(float salario);
}
