package com.enfedaque.fluxAPI.service;

import com.enfedaque.fluxAPI.domain.usuarios;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;
import com.enfedaque.fluxAPI.repository.usuariosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuariosServiceImplem implements usuariosService {

    @Autowired
    private usuariosRepository miUsuariosRepository;

    @Override
    public List<usuarios> findAll() {
        return miUsuariosRepository.findAll();
    }

    @Override
    public usuarios findById(long id) throws usuarioNotFoundException {
        return miUsuariosRepository.findById(id)
                .orElseThrow(() -> new usuarioNotFoundException());
    }

    @Override
    public usuarios deleteUsuario(long id) throws usuarioNotFoundException {
        usuarios miUsuario= miUsuariosRepository.findById(id)
                .orElseThrow(() -> new usuarioNotFoundException());;
        miUsuariosRepository.deleteById(id);
        return miUsuario;
    }

    @Override
    public usuarios addUsuario(usuarios usuario) {
        //Spring ya tiene el metodo "save" que da de alta nuevos campos
       return miUsuariosRepository.save(usuario);
    }

    //TODO Aqui esta mal
    @Override
    public usuarios modifyUsuario(usuarios nuevoUsuario, long id) throws usuarioNotFoundException {
        usuarios miUsuario= miUsuariosRepository.findById(id)
                .orElseThrow(() -> new usuarioNotFoundException());

        ModelMapper mapper=new ModelMapper();
        usuarios usuarioModificado=mapper.map(miUsuario, usuarios.class);
        return miUsuariosRepository.save(usuarioModificado);
    }

    @Override
    public List<usuarios> findByNombre(String nombre) {
        return miUsuariosRepository.findByNombre(nombre);
    }

    @Override
    public List<String> busquedaVariada(String nombre, String telefono) {
        return miUsuariosRepository.busquedaVariada(nombre, telefono);
    }
}
