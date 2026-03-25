package com.cliente.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import com.cliente.demo.Modelos.DAO.IDetallePresupuestoDao;
import com.cliente.demo.Modelos.DAO.IPresupuestoDao;
import com.cliente.demo.Modelos.DAO.IMaterialDao;
import com.cliente.demo.Modelos.Entity.DetallePresupuesto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/detallepresupuesto")
public class DetallePresupuestoController {

    @Autowired
    private IDetallePresupuestoDao detallePresupuestoDao;

    @Autowired
    private IPresupuestoDao presupuestoDao;

    @Autowired
    private IMaterialDao materialDao;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Detalles de Presupuesto");
        model.addAttribute("detalles", detallePresupuestoDao.findAll());
        return "detallepresupuesto/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        DetallePresupuesto detalle = new DetallePresupuesto();
        model.addAttribute("detallePresupuesto", detalle);
        model.addAttribute("presupuestos", presupuestoDao.findAll());
        model.addAttribute("materiales", materialDao.findAll());
        model.addAttribute("titulo", "Formulario de Detalle de Presupuesto");
        return "detallepresupuesto/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("detallePresupuesto") DetallePresupuesto detallePresupuesto,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Detalle de Presupuesto");
            model.addAttribute("presupuestos", presupuestoDao.findAll());
            model.addAttribute("materiales", materialDao.findAll());
            return "detallepresupuesto/form";
        }

        detallePresupuestoDao.save(detallePresupuesto);
        return "redirect:/detallepresupuesto/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        DetallePresupuesto detalle = detallePresupuestoDao.findOne(id);

        if (detalle == null) {
            return "redirect:/detallepresupuesto/listar";
        }

        model.addAttribute("detallePresupuesto", detalle);
        model.addAttribute("presupuestos", presupuestoDao.findAll());
        model.addAttribute("materiales", materialDao.findAll());
        model.addAttribute("titulo", "Editar Detalle de Presupuesto");
        return "detallepresupuesto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        if (id > 0) {
            detallePresupuestoDao.delete(id);
        }
        return "redirect:/detallepresupuesto/listar";
    }
}