package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cliente.demo.Modelos.Entity.Presupuesto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PresupuestoDaoImp implements IPresupuestoDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Presupuesto> findAll() {
        return em.createQuery("from Presupuesto").getResultList();
    }

    @Override
    @Transactional
    public void save(Presupuesto presupuesto) {
        if (presupuesto.getId() != null && presupuesto.getId() > 0) {
            em.merge(presupuesto);
        } else {
            em.persist(presupuesto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Presupuesto findOne(Long id) {
        return em.find(Presupuesto.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Presupuesto presupuesto = em.find(Presupuesto.class, id);
        if (presupuesto != null) {
            em.remove(presupuesto);
        }
    }
}