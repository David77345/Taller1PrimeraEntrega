package com.cliente.demo.Controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cliente.demo.Modelos.DAO.PresupuestoRepository;
import com.cliente.demo.Modelos.DAO.ProyectoRepository;
import com.cliente.demo.Modelos.Entity.Proyecto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Proyecto> proyectos = proyectoRepository.findAll().stream()
                .sorted(Comparator.comparing(Proyecto::getFecha, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(Proyecto::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("titulo", "Listado de Proyectos");
        model.addAttribute("proyectos", proyectos);
        return "proyecto/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("titulo", "Formulario de Proyecto");
        model.addAttribute("modoEdicion", false);
        return "proyecto/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("proyecto") Proyecto proyecto,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {

        if (proyecto.getNombre() != null) {
            proyecto.setNombre(proyecto.getNombre().trim());
        }
        if (proyecto.getIdCliente() != null) {
            proyecto.setIdCliente(proyecto.getIdCliente().trim());
        }

        if (result.hasErrors()) {
            model.addAttribute("titulo", proyecto.getId() == null ? "Formulario de Proyecto" : "Editar Proyecto");
            model.addAttribute("modoEdicion", proyecto.getId() != null && !proyecto.getId().isBlank());
            return "proyecto/form";
        }

        if (proyecto.getId() != null && proyecto.getId().trim().isEmpty()) {
            proyecto.setId(null);
        }

        boolean editando = proyecto.getId() != null;
        proyectoRepository.save(proyecto);
        flash.addFlashAttribute("success", editando ? "Proyecto actualizado correctamente." : "Proyecto guardado correctamente.");
        return "redirect:/proyecto/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes flash) {
        Proyecto proyecto = proyectoRepository.findById(id).orElse(null);

        if (proyecto == null) {
            flash.addFlashAttribute("error", "El proyecto que intentas editar no existe.");
            return "redirect:/proyecto/listar";
        }

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("titulo", "Editar Proyecto");
        model.addAttribute("modoEdicion", true);
        return "proyecto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes flash) {
        if (!presupuestoRepository.findByProyectoId(id).isEmpty()) {
            flash.addFlashAttribute("error", "No se puede eliminar el proyecto porque tiene presupuestos asociados.");
            return "redirect:/proyecto/listar";
        }

        if (proyectoRepository.existsById(id)) {
            proyectoRepository.deleteById(id);
            flash.addFlashAttribute("success", "Proyecto eliminado correctamente.");
        } else {
            flash.addFlashAttribute("error", "El proyecto que intentas eliminar no existe.");
        }

        return "redirect:/proyecto/listar";
    }
}
