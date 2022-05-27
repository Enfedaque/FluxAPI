package com.enfedaque.fluxAPI.service;


import com.enfedaque.fluxAPI.domain.empleados;
import com.enfedaque.fluxAPI.excepciones.empleadoNotFoundException;
import com.enfedaque.fluxAPI.repository.empleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class empleadoServiceImplem implements empleadoService {

    @Autowired
    private empleadosRepository miEmpleadoRepository;


    //Añadir empleado
    @Override
    public empleados addEmpleado(empleados empleado) {
        return miEmpleadoRepository.save(empleado);
    }

    //Modificar empleado
    @Override
    public empleados modifyEmpleado(empleados empleado, long id) throws empleadoNotFoundException {
        empleados miEmpleado= miEmpleadoRepository.findById( id)
                .orElseThrow(empleadoNotFoundException::new);
        miEmpleado.setId(miEmpleado.getId());
        miEmpleado.setNombre(empleado.getNombre());
        miEmpleado.setApellido(empleado.getApellido());
        miEmpleado.setEmail(empleado.getEmail());
        miEmpleado.setTelefono(empleado.getTelefono());
        miEmpleado.setFechaNac(empleado.getFechaNac());
        miEmpleado.setPuesto(empleado.getPuesto());
        miEmpleado.setDepartamento(empleado.getDepartamento());
        miEmpleado.setSalario(empleado.getSalario());
        miEmpleado.setOperariosAlCargo(empleado.getOperariosAlCargo());
        miEmpleado.isJefeDeTaller(empleado.isJefeDeTaller());
        miEmpleado.setFechaComienzo(empleado.getFechaComienzo());
        return miEmpleadoRepository.save(miEmpleado);
    }

    //Mostrar todos los empleados
    @Override
    public List<empleados> findAll() {
        return miEmpleadoRepository.findAll();
    }

    //Buscar un empleado por ID
    @Override
    public empleados findById(long id) throws empleadoNotFoundException {

        return miEmpleadoRepository.findById(id)
                .orElseThrow(empleadoNotFoundException::new);
    }


    //Eliminar empleado por ID
    @Override
    public empleados deleteEmpleado(long id) throws empleadoNotFoundException {
        empleados empleado=miEmpleadoRepository.findById(id)
                .orElseThrow(empleadoNotFoundException::new);
        miEmpleadoRepository.deleteById(id);
        return empleado;
    }

    //Busqueda por 3 campos
    @Override
    public List<empleados> findByPuestoAndDepartamentoAndSalario(String puesto, String departamento, float salario) {

        List<empleados> miEmpleado=miEmpleadoRepository.findByPuestoAndDepartamentoAndSalario(puesto, departamento, salario);

        return miEmpleado;
    }

    @Override
    public empleados modifySalario(float salario, long id) throws empleadoNotFoundException{

        empleados miEmpleado= miEmpleadoRepository.findById(id)
                .orElseThrow(empleadoNotFoundException::new);

        miEmpleado.setSalario(salario);

        return miEmpleadoRepository.save(miEmpleado);
    }

    //Operacion que viene del metodo del repository en JPQL
    @Override
    public List<empleados> findByOperarios(int operariosAlCargo) {

        return miEmpleadoRepository.findByOperarios(operariosAlCargo);
    }

    //Viene del repository con SQL NATIVAS
    @Override
    public List<String> buscarPuestoYDepart(float salario) {
        return miEmpleadoRepository.buscarPuestoYDepart(salario);
    }
}
