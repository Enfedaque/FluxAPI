package com.enfedaque.fluxAPI.repository;


import com.enfedaque.fluxAPI.domain.facturas;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface facturasRepository extends CrudRepository<facturas, Long> {

    List<facturas> findAll();

    List<facturas> findByNombrePropietarioAndIsEmpresaAndPrecio(String nombrePropietario, boolean isEmpresa, float precio);

    //JPQL
    @Query("SELECT precio FROM facturas f WHERE nombrePropietario= :nombrePropietario")
    List<String> findByNomprePro(String nombrePropietario);

    //SQL NATIVAS
    @Query(value = "SELECT nombrePropietario FROM facturas WHERE numFactura= :numFactura")
    String buscarDuenoFactura(long numFactura);
}
