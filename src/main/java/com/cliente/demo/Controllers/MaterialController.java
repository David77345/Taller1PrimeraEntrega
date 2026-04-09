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

import com.cliente.demo.Modelos.DAO.CorteRepository;
import com.cliente.demo.Modelos.DAO.MaterialRepository;
import com.cliente.demo.Modelos.Entity.Material;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CorteRepository corteRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Material> materiales = materialRepository.findAll().stream()
                .sorted(Comparator.comparing(Material::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("titulo", "Listado de Materiales");
        model.addAttribute("materiales", materiales);
        return "material/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("material", new Material());
        model.addAttribute("titulo", "Formulario de Material");
        model.addAttribute("modoEdicion", false);
        return "material/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("material") Material material,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {

        if (material.getNombre() != null) {
            material.setNombre(material.getNombre().trim());
        }
        if (material.getDescripcion() != null) {
            material.setDescripcion(material.getDescripcion().trim());
        }

        if (result.hasErrors()) {
            model.addAttribute("titulo", material.getId() == null ? "Formulario de Material" : "Editar Material");
            model.addAttribute("modoEdicion", material.getId() != null && !material.getId().isBlank());
            return "material/form";
        }

        if (material.getId() != null && material.getId().trim().isEmpty()) {
            material.setId(null);
        }

        boolean editando = material.getId() != null;
        materialRepository.save(material);
        flash.addFlashAttribute("success", editando ? "Material actualizado correctamente." : "Material guardado correctamente.");
        return "redirect:/material/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes flash) {
        Material material = materialRepository.findById(id).orElse(null);

        if (material == null) {
            flash.addFlashAttribute("error", "El material que intentas editar no existe.");
            return "redirect:/material/listar";
        }

        model.addAttribute("material", material);
        model.addAttribute("titulo", "Editar Material");
        model.addAttribute("modoEdicion", true);
        return "material/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes flash) {
        if (!corteRepository.findByDetallesMaterialId(id).isEmpty()) {
            flash.addFlashAttribute("error", "No se puede eliminar el material porque ya está usado en cortes.");
            return "redirect:/material/listar";
        }

        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            flash.addFlashAttribute("success", "Material eliminado correctamente.");
        } else {
            flash.addFlashAttribute("error", "El material que intentas eliminar no existe.");
        }

        return "redirect:/material/listar";
    }
}
