package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cliente.demo.Modelos.Entity.Presupuesto;

@Repository
public interface PresupuestoRepository extends MongoRepository<Presupuesto, String> {

    List<Presupuesto> findByProyectoId(String proyectoId);
}