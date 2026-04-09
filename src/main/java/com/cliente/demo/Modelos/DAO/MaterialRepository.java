package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cliente.demo.Modelos.Entity.Material;

@Repository
public interface MaterialRepository extends MongoRepository<Material, String> {

    List<Material> findByNombreContainingIgnoreCase(String nombre);
}