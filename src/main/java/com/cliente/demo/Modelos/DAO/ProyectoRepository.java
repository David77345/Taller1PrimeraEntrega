package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cliente.demo.Modelos.Entity.Proyecto;

@Repository
public interface ProyectoRepository extends MongoRepository<Proyecto, String> {

    List<Proyecto> findByIdCliente(String idCliente);

    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
}