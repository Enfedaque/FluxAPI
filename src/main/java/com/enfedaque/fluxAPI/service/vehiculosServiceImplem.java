package com.enfedaque.fluxAPI.service;



import com.enfedaque.fluxAPI.domain.clientes;
import com.enfedaque.fluxAPI.domain.dto.vehiculosDTO;
import com.enfedaque.fluxAPI.domain.vehiculos;
import com.enfedaque.fluxAPI.excepciones.clienteNotFoundException;
import com.enfedaque.fluxAPI.excepciones.facturasNotFoundException;
import com.enfedaque.fluxAPI.excepciones.vehiculoNotFoundException;
import com.enfedaque.fluxAPI.repository.clientesRepository;
import com.enfedaque.fluxAPI.repository.facturasRepository;
import com.enfedaque.fluxAPI.repository.vehiculosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class vehiculosServiceImplem implements vehiculosService {

    @Autowired
    private com.enfedaque.fluxAPI.repository.vehiculosRepository vehiculosRepository;
    @Autowired
    private com.enfedaque.fluxAPI.repository.clientesRepository clientesRepository;
    @Autowired
    private com.enfedaque.fluxAPI.repository.facturasRepository facturasRepository;


    @Override
    public vehiculos addVehiculos(vehiculosDTO vehiculoDTO) throws clienteNotFoundException, facturasNotFoundException {
        clientes miCliente=clientesRepository.findById(vehiculoDTO.getCliente_id())
                .orElseThrow(clienteNotFoundException::new);

        ModelMapper mapper=new ModelMapper();

        vehiculos vehiculoNuevo=mapper.map(vehiculoDTO, vehiculos.class);
        vehiculoNuevo.setCliente(miCliente);
        return vehiculosRepository.save(vehiculoNuevo);
    }


    @Override
    public List<vehiculos> findAll() {
        List<vehiculos> listado=vehiculosRepository.findAll();

        return listado;
    }


    @Override
    public vehiculos deleteVehiculos(long id) throws vehiculoNotFoundException {
        vehiculos miVehiculo= vehiculosRepository.findById(id)
                .orElseThrow(vehiculoNotFoundException::new);

        vehiculosRepository.deleteById(id);

        return miVehiculo;
    }

    //TODO creo casi seguro que esta mal
    @Override
    public vehiculos modifyVehiculos(vehiculosDTO vehiculoDTO, long id) throws vehiculoNotFoundException, clienteNotFoundException {
        vehiculos miVehiculo= vehiculosRepository.findById(id)
                .orElseThrow(vehiculoNotFoundException::new);

        clientes miCliente=clientesRepository.findById(vehiculoDTO.getCliente_id())
                .orElseThrow(clienteNotFoundException::new);

        ModelMapper mapper= new ModelMapper();
        vehiculos miVehiculo2=mapper.map(vehiculoDTO, miVehiculo.getClass());

        miVehiculo2.setVehiculosID(miVehiculo.getVehiculosID());
        return vehiculosRepository.save(miVehiculo2);
    }

    @Override
    public vehiculos findById(long id) throws vehiculoNotFoundException{
        vehiculos miVehiculo=vehiculosRepository.findById(id)
                .orElseThrow(vehiculoNotFoundException::new);

        return miVehiculo;
    }

    @Override
    public List<vehiculos> findByMatriculaAndKilometrosAndAntiguedad(String matricula, float kilometros, int antiguedad) {

        List<vehiculos> mivehiculo=vehiculosRepository.findByMatriculaAndKilometrosAndAntiguedad(matricula, kilometros, antiguedad);

        return mivehiculo;
    }

    @Override
    public vehiculos modifyKilometros(float kilometros, long id) throws vehiculoNotFoundException{

        vehiculos miVehiculo=vehiculosRepository.findById(id)
                .orElseThrow(vehiculoNotFoundException::new);

        miVehiculo.setKilometros(kilometros);
        return vehiculosRepository.save(miVehiculo);
    }

    //Del reposirtoio que hago la consulta con JPQL
    @Override
    public List<vehiculos> findByPropietario(boolean unicoPropietario) {
        return vehiculosRepository.findByPropietario(unicoPropietario);
    }

    @Override
    public List<LocalDate> busquedaVariada(String matricula) {
        return vehiculosRepository.busquedaVariada(matricula);
    }
}
