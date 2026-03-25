package com.cliente.demo.Modelos.DAO;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cliente.demo.Modelos.Entity.Proyecto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProyectoDaoImp implements IProyectoDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> findAll() {
        return em.createQuery("from Proyecto").getResultList();
    }

    @Override
    @Transactional
    public void save(Proyecto proyecto) {
        if (proyecto.getId() != null && proyecto.getId() > 0) {
            em.merge(proyecto);
        } else {
            em.persist(proyecto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto findOne(Long id) {
        return em.find(Proyecto.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Proyecto proyecto = em.find(Proyecto.class, id);
        if (proyecto != null) {
            em.remove(proyecto);
        }
    }
}