package com.cliente.demo.Controllers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.cliente.demo.Modelos.DAO.PresupuestoRepository;
import com.cliente.demo.Modelos.DAO.ProyectoRepository;
import com.cliente.demo.Modelos.DTO.PresupuestoListadoDTO;
import com.cliente.demo.Modelos.Entity.Presupuesto;
import com.cliente.demo.Modelos.Entity.Proyecto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/presupuesto")
public class PresupuestoController {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private CorteRepository corteRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Proyecto> proyectos = proyectoRepository.findAll();

        Map<String, String> proyectoMap = proyectos.stream()
                .filter(p -> p.getId() != null)
                .collect(Collectors.toMap(Proyecto::getId, Proyecto::getNombre, (a, b) -> a));

        List<PresupuestoListadoDTO> presupuestos = presupuestoRepository.findAll().stream()
                .map(p -> {
                    double montoMaximo = p.getMontoMaximo() != null ? p.getMontoMaximo() : 0.0;
                    double totalGastado = p.getTotalGastado() != null ? p.getTotalGastado() : 0.0;
                    double saldoDisponible = montoMaximo - totalGastado;

                    return new PresupuestoListadoDTO(
                            p.getId(),
                            proyectoMap.getOrDefault(p.getProyectoId(), "Sin proyecto"),
                            montoMaximo,
                            totalGastado,
                            saldoDisponible
                    );
                })
                .sorted(Comparator.comparing(PresupuestoListadoDTO::getNombreProyecto, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("titulo", "Listado de Presupuestos");
        model.addAttribute("presupuestos", presupuestos);
        return "presupuesto/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        List<Proyecto> proyectos = proyectoRepository.findAll().stream()
                .sorted(Comparator.comparing(Proyecto::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("presupuesto", new Presupuesto());
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("titulo", "Formulario de Presupuesto");
        model.addAttribute("modoEdicion", false);
        return "presupuesto/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("presupuesto") Presupuesto presupuesto,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {

        if (presupuesto.getProyectoId() != null) {
            presupuesto.setProyectoId(presupuesto.getProyectoId().trim());
        }

        Presupuesto presupuestoExistente = null;
        boolean editando = presupuesto.getId() != null && !presupuesto.getId().isBlank();

        if (editando) {
            presupuestoExistente = presupuestoRepository.findById(presupuesto.getId()).orElse(null);
            if (presupuestoExistente == null) {
                flash.addFlashAttribute("error", "El presupuesto que intentas editar no existe.");
                return "redirect:/presupuesto/listar";
            }
        }

        if (presupuesto.getProyectoId() == null || presupuesto.getProyectoId().isEmpty()) {
            result.rejectValue("proyectoId", "error.presupuesto", "Debe seleccionar un proyecto.");
        } else if (!proyectoRepository.existsById(presupuesto.getProyectoId())) {
            result.rejectValue("proyectoId", "error.presupuesto", "El proyecto seleccionado no existe.");
        }

        double totalGastadoActual = presupuestoExistente != null && presupuestoExistente.getTotalGastado() != null
                ? presupuestoExistente.getTotalGastado()
                : (presupuesto.getTotalGastado() != null ? presupuesto.getTotalGastado() : 0.0);

        if (presupuesto.getMontoMaximo() != null && presupuesto.getMontoMaximo() < totalGastadoActual) {
            result.rejectValue(
                    "montoMaximo",
                    "error.presupuesto",
                    "El monto máximo no puede ser menor al total gastado actual del presupuesto."
            );
        }

        if (result.hasErrors()) {
            List<Proyecto> proyectos = proyectoRepository.findAll().stream()
                    .sorted(Comparator.comparing(Proyecto::getNombre, String.CASE_INSENSITIVE_ORDER))
                    .toList();

            model.addAttribute("titulo", editando ? "Editar Presupuesto" : "Formulario de Presupuesto");
            model.addAttribute("proyectos", proyectos);
            model.addAttribute("modoEdicion", editando);
            return "presupuesto/form";
        }

        if (presupuesto.getId() != null && presupuesto.getId().trim().isEmpty()) {
            presupuesto.setId(null);
        }

        presupuesto.setTotalGastado(totalGastadoActual);
        presupuestoRepository.save(presupuesto);
        flash.addFlashAttribute("success", editando ? "Presupuesto actualizado correctamente." : "Presupuesto guardado correctamente.");
        return "redirect:/presupuesto/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes flash) {
        Presupuesto presupuesto = presupuestoRepository.findById(id).orElse(null);

        if (presupuesto == null) {
            flash.addFlashAttribute("error", "El presupuesto que intentas editar no existe.");
            return "redirect:/presupuesto/listar";
        }

        List<Proyecto> proyectos = proyectoRepository.findAll().stream()
                .sorted(Comparator.comparing(Proyecto::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("presupuesto", presupuesto);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("titulo", "Editar Presupuesto");
        model.addAttribute("modoEdicion", true);
        return "presupuesto/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes flash) {
        if (!corteRepository.findByPresupuestoId(id).isEmpty()) {
            flash.addFlashAttribute("error", "No se puede eliminar el presupuesto porque tiene cortes asociados.");
            return "redirect:/presupuesto/listar";
        }

        if (presupuestoRepository.existsById(id)) {
            presupuestoRepository.deleteById(id);
            flash.addFlashAttribute("success", "Presupuesto eliminado correctamente.");
        } else {
            flash.addFlashAttribute("error", "El presupuesto que intentas eliminar no existe.");
        }

        return "redirect:/presupuesto/listar";
    }
}
