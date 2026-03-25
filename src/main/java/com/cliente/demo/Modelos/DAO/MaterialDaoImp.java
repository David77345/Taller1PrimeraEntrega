package com.cliente.demo.Modelos.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cliente.demo.Modelos.Entity.Material;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MaterialDaoImp implements IMaterialDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Material> findAll() {
        return em.createQuery("from Material").getResultList();
    }

    @Override
    @Transactional
    public void save(Material material) {
        if (material.getId() != null && material.getId() > 0) {
            em.merge(material);
        } else {
            em.persist(material);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Material findOne(Long id) {
        return em.find(Material.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Material material = em.find(Material.class, id);
        if (material != null) {
            em.remove(material);
        }
    }
}