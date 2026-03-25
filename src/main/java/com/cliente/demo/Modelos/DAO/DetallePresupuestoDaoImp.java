package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cliente.demo.Modelos.Entity.DetallePresupuesto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DetallePresupuestoDaoImp implements IDetallePresupuestoDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<DetallePresupuesto> findAll() {
        return em.createQuery("from DetallePresupuesto").getResultList();
    }

    @Override
    @Transactional
    public void save(DetallePresupuesto detallePresupuesto) {
        if (detallePresupuesto.getId() != null && detallePresupuesto.getId() > 0) {
            em.merge(detallePresupuesto);
        } else {
            em.persist(detallePresupuesto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePresupuesto findOne(Long id) {
        return em.find(DetallePresupuesto.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DetallePresupuesto detallePresupuesto = em.find(DetallePresupuesto.class, id);
        if (detallePresupuesto != null) {
            em.remove(detallePresupuesto);
        }
    }
}