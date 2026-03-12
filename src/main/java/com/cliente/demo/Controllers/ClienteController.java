package com.cliente.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cliente.demo.Modelos.DAO.*;
import com.cliente.demo.Modelos.Entity.Cliente;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @GetMapping("/listar")
    public String Listar(Model model) {
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("cliente", clienteDao.findAll());

        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente",cliente);
        model.addAttribute("titulo","Formulario de Cliente");

        return "form";
    }
    @PostMapping("path")
    public String guardar(Cliente cliente) {
        clienteDao.save(cliente);
        return "redirect:/listar";
    }


    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value="id") Long id, Model model)
    {
        Cliente cliente = clienteDao.findOne(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar Cliente");
        return"form";
    }


   
 
    
     @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id)
    {
        clienteDao.delete(id);
        return "redirect:/listar";
    };
            

}