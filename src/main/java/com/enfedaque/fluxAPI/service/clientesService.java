package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.clientes;
import com.enfedaque.fluxAPI.domain.dto.clientesDTO;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;

import java.util.List;

public interface clientesService {

    List<clientes> findAll();
    clientesDTO findById(long id) throws clienteNotFoundException;
    clientes deleteCliente(long id) throws clienteNotFoundException;
    clientes addCliente(clientesDTO clienteDTO) throws vehiculoNotFoundException;
    clientes modifyCliente(clientesDTO clienteDTO, long id) throws clienteNotFoundException;
    List<clientes> findByParticularAndPresupuestoAndNombreEmpresa(boolean particular, int presupuestoEnReparaciones, String nombreEmpresa);

    clientes modifyPresupuesto(int presupuesto, long id) throws clienteNotFoundException;


}
