package com.cliente.demo.Modelos.DAO;

import java.util.List;
import com.cliente.demo.Modelos.Entity.Presupuesto;

public interface IPresupuestoDao {

    public List<Presupuesto> findAll();

    public void save(Presupuesto presupuesto);

    public Presupuesto findOne(Long id);

    public void delete(Long id);
}