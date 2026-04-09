package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cliente.demo.Modelos.Entity.Corte;

@Repository
public interface CorteRepository extends MongoRepository<Corte, String> {

    List<Corte> findByPresupuestoId(String presupuestoId);

    List<Corte> findByDetallesMaterialId(String materialId);
}