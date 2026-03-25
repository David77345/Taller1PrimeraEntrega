package com.cliente.demo.Modelos.DAO;

import java.util.List;
import com.cliente.demo.Modelos.Entity.DetallePresupuesto;

public interface IDetallePresupuestoDao {

    public List<DetallePresupuesto> findAll();

    public void save(DetallePresupuesto detallePresupuesto);

    public DetallePresupuesto findOne(Long id);

    public void delete(Long id);
}