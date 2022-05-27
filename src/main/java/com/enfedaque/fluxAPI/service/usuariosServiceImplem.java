package com.enfedaque.fluxAPI.service;


import com.enfedaque.fluxAPI.domain.usuarios;
import com.enfedaque.fluxAPI.excepciones.usuarioNotFoundException;
import com.enfedaque.fluxAPI.repository.usuariosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class usuariosServiceImplem implements usuariosService {

    @Autowired
    private usuariosRepository miUsuariosRepository;

    @Override
    public Flux<usuarios> findAll() {
        return miUsuariosRepository.findAll();
    }

    @Override
    public Mono<usuarios> findById(long id) throws usuarioNotFoundException {
        return miUsuariosRepository.findById(id)
                .onErrorReturn(new usuarios());
    }

    @Override
    public Mono<usuarios> deleteUsuario(long id) throws usuarioNotFoundException {
        Mono<usuarios> miUsuario= miUsuariosRepository.findById(id)
                .onErrorReturn(new usuarios());
        miUsuariosRepository.deleteById(id);
        return miUsuario;
    }

    @Override
    public Mono<usuarios> addUsuario(usuarios usuario) {
        //Spring ya tiene el metodo "save" que da de alta nuevos campos
       return miUsuariosRepository.save(usuario);
    }

    //TODO Aqui esta mal
    @Override
    public Mono<usuarios> modifyUsuario(usuarios nuevoUsuario, long id) throws usuarioNotFoundException {
        Mono<usuarios> miUsuario= miUsuariosRepository.findById(id)
                .onErrorReturn(new usuarios());

        ModelMapper mapper=new ModelMapper();
        usuarios usuarioModificado=mapper.map(miUsuario, usuarios.class);
        return miUsuariosRepository.save(usuarioModificado);
    }

    @Override
    public Flux<usuarios> findByNombre(String nombre) {
        return miUsuariosRepository.findByNombre(nombre);
    }

    @Override
    public Flux<String> busquedaVariada(String nombre, String telefono) {
        return miUsuariosRepository.busquedaVariada(nombre, telefono);
    }
}
