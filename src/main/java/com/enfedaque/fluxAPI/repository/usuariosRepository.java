package com.enfedaque.fluxAPI.repository;


import com.enfedaque.fluxAPI.domain.usuarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Similar al DAO
@Repository
public interface usuariosRepository extends CrudRepository<usuarios, Long> {

    /*Metodos como los del DAO, todos los que necesite
    METODOS QUE ACCEDEN A LA BBDD Y RECOGEN DATOS
    * Los find... tienen su miga, ya que solo con ese nombre se encarga de buscar la info en la BBDD*/

    //Devuleve todos los usuarios
    List<usuarios> findAll();

    //JPQL
    @Query("SELECT user FROM usuarios user WHERE nombre= :nombre")
    List<usuarios> findByNombre(String nombre);

    //SQL NATIVAS
    @Query(value = "SELECT DNI, email FROM usuarios WHERE nombre= :nombre AND telefono= :telefono")
    List<String> busquedaVariada(String nombre, String telefono);
}
