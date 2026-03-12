package com.cliente.demo.Modelos.DAO;

import java.util.List;

import com.cliente.demo.Modelos.Entity.Cliente;



public interface IClienteDao {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);

}