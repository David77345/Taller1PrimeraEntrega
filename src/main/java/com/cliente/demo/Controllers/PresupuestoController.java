package com.cliente.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import com.cliente.demo.Modelos.DAO.IPresupuestoDao;
import com.cliente.demo.Modelos.DAO.IProyectoDao;
import com.cliente.demo.Modelos.Entity.Presupuesto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/presupuesto")
public class PresupuestoController {

    @Autowired
    private IPresupuestoDao presupuestoDao;

    @Autowired
    private IProyectoDao proyectoDao;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Presupuestos");
        model.addAttribute("presupuestos", presupuestoDao.findAll());
        return "presupuesto/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Presupuesto presupuesto = new Presupuesto();
        model.addAttribute("presupuesto", presupuesto);
        model.addAttribute("proyectos", proyectoDao.findAll());
        model.addAttribute("titulo", "Formulario de Presupuesto");
        return "presupuesto/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute Presupuesto presupuesto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Presupuesto");
            model.addAttribute("proyectos", proyectoDao.findAll());
            return "presupuesto/form";
        }

        presupuestoDao.save(presupuesto);
        return "redirect:/presupuesto/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Presupuesto presupuesto = presupuestoDao.findOne(id);

        if (presupuesto == null) {
            return "redirect:/presupuesto/listar";
        }

        model.addAttribute("presupuesto", presupuesto);
        model.addAttribute("proyectos", proyectoDao.findAll());
        model.addAttribute("titulo", "Editar Presupuesto");
        return "presupuesto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        if (id > 0) {
            presupuestoDao.delete(id);
        }
        return "redirect:/presupuesto/listar";
    }
}