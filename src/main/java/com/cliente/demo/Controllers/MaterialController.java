package com.cliente.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import com.cliente.demo.Modelos.DAO.IMaterialDao;
import com.cliente.demo.Modelos.Entity.Material;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private IMaterialDao materialDao;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Materiales");
        model.addAttribute("materiales", materialDao.findAll());
        return "material/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Material material = new Material();
        model.addAttribute("material", material);
        model.addAttribute("titulo", "Formulario de Material");
        return "material/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute Material material, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Material");
            return "material/form";
        }

        materialDao.save(material);
        return "redirect:/material/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Material material = materialDao.findOne(id);

        if (material == null) {
            return "redirect:/material/listar";
        }

        model.addAttribute("material", material);
        model.addAttribute("titulo", "Editar Material");
        return "material/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        if (id > 0) {
            materialDao.delete(id);
        }
        return "redirect:/material/listar";
    }
}