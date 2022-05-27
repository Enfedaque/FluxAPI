package com.enfedaque.fluxAPI.service;


import com.enfedaque.fluxAPI.domain.clientes;
import com.enfedaque.fluxAPI.domain.dto.clientesDTO;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.repository.clientesRepository;
import com.enfedaque.fluxAPI.repository.vehiculosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class clientesServiceImplem implements clientesService{

    @Autowired
    private clientesRepository miClienteRepository;
    @Autowired
    private vehiculosRepository miVehiculosRepository;


    @Override
    public List<clientes> findAll() {
        List<clientes> listado=miClienteRepository.findAll();
        return listado;
    }


    @Override
    public clientesDTO findById(long id) throws clienteNotFoundException {
        clientes miCliente=miClienteRepository.findById(id)
                .orElseThrow(clienteNotFoundException::new);

        ModelMapper mapper=new ModelMapper();
        clientesDTO miClienteFinal=mapper.map(miCliente, (Type) clientesDTO.class);
        return miClienteFinal;
    }


    @Override
    public clientes deleteCliente(long id) throws clienteNotFoundException {
        clientes miCliente= miClienteRepository.findById(id)
                .orElseThrow(clienteNotFoundException::new);
        miClienteRepository.deleteById(id);

        return miCliente;
    }

    @Override
    public clientes addCliente(clientesDTO clienteDTO) {

        ModelMapper mapper=new ModelMapper();

        clientes miCliente=mapper.map(clienteDTO, clientes.class);

        return miClienteRepository.save(miCliente);
    }

    @Override
    public clientes modifyCliente(clientesDTO clienteDTO, long id) throws clienteNotFoundException {

        clientes miCliente= miClienteRepository.findById(id)
                .orElseThrow(clienteNotFoundException::new);

        ModelMapper mapper= new ModelMapper();
        clientes miCliente2=mapper.map(clienteDTO, miCliente.getClass());
        miCliente2.setId(miCliente.getId());
        return miClienteRepository.save(miCliente2);
    }

    @Override
    public List<clientes> findByParticularAndPresupuestoAndNombreEmpresa(boolean particular, int presupuestoEnReparaciones,
                                    String nombreEmpresa) {

        List<clientes> miCliente=miClienteRepository.findByParticularAndPresupuestoAndNombreEmpresa(particular,
                presupuestoEnReparaciones, nombreEmpresa);
        return miCliente;
    }

    @Override
    public clientes modifyPresupuesto(int presupuesto, long id) throws clienteNotFoundException{
        clientes miCliente=miClienteRepository.findById(id)
                .orElseThrow(clienteNotFoundException::new);

        miCliente.setPresupuesto(presupuesto);

        return miClienteRepository.save(miCliente);
    }



}
