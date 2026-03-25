package com.cliente.demo.Modelos.DAO;

import java.util.List;
import com.cliente.demo.Modelos.Entity.Proyecto;

public interface IProyectoDao {

    public List<Proyecto> findAll();

    public void save(Proyecto proyecto);

    public Proyecto findOne(Long id);

    public void delete(Long id);
}