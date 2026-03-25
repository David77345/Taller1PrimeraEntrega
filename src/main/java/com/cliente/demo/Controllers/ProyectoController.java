package com.cliente.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import com.cliente.demo.Modelos.DAO.IProyectoDao;
import com.cliente.demo.Modelos.Entity.Proyecto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private IProyectoDao proyectoDao;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Proyectos");
        model.addAttribute("proyectos", proyectoDao.findAll());
        return "proyecto/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Proyecto proyecto = new Proyecto();
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("titulo", "Formulario de Proyecto");
        return "proyecto/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("proyecto") Proyecto proyecto,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Proyecto");
            return "proyecto/form";
        }

        proyectoDao.save(proyecto);
        return "redirect:/proyecto/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Proyecto proyecto = proyectoDao.findOne(id);

        if (proyecto == null) {
            return "redirect:/proyecto/listar";
        }

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("titulo", "Editar Proyecto");
        return "proyecto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        if (id > 0) {
            proyectoDao.delete(id);
        }
        return "redirect:/proyecto/listar";
    }
}