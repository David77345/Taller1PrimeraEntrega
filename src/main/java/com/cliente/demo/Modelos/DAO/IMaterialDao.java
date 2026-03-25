package com.cliente.demo.Modelos.DAO;

import java.util.List;
import com.cliente.demo.Modelos.Entity.Material;

public interface IMaterialDao {

    public List<Material> findAll();

    public void save(Material material);

    public Material findOne(Long id);

    public void delete(Long id);
}