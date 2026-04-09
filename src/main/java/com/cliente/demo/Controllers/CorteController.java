package com.cliente.demo.Controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
import com.cliente.demo.Modelos.DAO.MaterialRepository;
import com.cliente.demo.Modelos.DAO.PresupuestoRepository;
import com.cliente.demo.Modelos.DAO.ProyectoRepository;
import com.cliente.demo.Modelos.DTO.CorteListadoDTO;
import com.cliente.demo.Modelos.DTO.PresupuestoOptionDTO;
import com.cliente.demo.Modelos.Entity.Corte;
import com.cliente.demo.Modelos.Entity.DetalleCorte;
import com.cliente.demo.Modelos.Entity.Material;
import com.cliente.demo.Modelos.Entity.Presupuesto;
import com.cliente.demo.Modelos.Entity.Proyecto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/corte")
public class CorteController {

    @Autowired
    private CorteRepository corteRepository;

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        Map<String, Presupuesto> presupuestoMap = presupuestoRepository.findAll().stream()
                .filter(p -> p.getId() != null)
                .collect(Collectors.toMap(Presupuesto::getId, p -> p, (a, b) -> a));

        Map<String, Proyecto> proyectoMap = proyectoRepository.findAll().stream()
                .filter(p -> p.getId() != null)
                .collect(Collectors.toMap(Proyecto::getId, p -> p, (a, b) -> a));

        List<CorteListadoDTO> cortes = corteRepository.findAll().stream()
                .map(corte -> {
                    String nombreProyecto = "Sin proyecto";
                    Presupuesto presupuesto = presupuestoMap.get(corte.getPresupuestoId());

                    if (presupuesto != null) {
                        Proyecto proyecto = proyectoMap.get(presupuesto.getProyectoId());
                        if (proyecto != null) {
                            nombreProyecto = proyecto.getNombre();
                        }
                    }

                    return new CorteListadoDTO(
                            corte.getId(),
                            corte.getPresupuestoId(),
                            nombreProyecto,
                            corte.getFecha(),
                            corte.getDetalles() != null ? corte.getDetalles().size() : 0,
                            corte.getTotal() != null ? corte.getTotal() : 0.0
                    );
                })
                .sorted(Comparator.comparing(CorteListadoDTO::getFecha, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(CorteListadoDTO::getNombreProyecto, String.CASE_INSENSITIVE_ORDER))
                .toList();

        model.addAttribute("titulo", "Listado de Cortes");
        model.addAttribute("cortes", cortes);
        return "corte/listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Corte corte = new Corte();
        corte.getDetalles().add(new DetalleCorte());

        model.addAttribute("corte", corte);
        model.addAttribute("presupuestoOpciones", construirPresupuestoOpciones());
        model.addAttribute("materiales", obtenerMaterialesOrdenados());
        model.addAttribute("titulo", "Formulario de Corte");
        model.addAttribute("modoEdicion", false);
        return "corte/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("corte") Corte corte,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {

        Corte corteExistente = null;
        String presupuestoAnteriorId = null;
        boolean editando = corte.getId() != null && !corte.getId().trim().isEmpty();

        if (editando) {
            corteExistente = corteRepository.findById(corte.getId()).orElse(null);
            if (corteExistente == null) {
                flash.addFlashAttribute("error", "El corte que intentas editar no existe.");
                return "redirect:/corte/listar";
            }
            presupuestoAnteriorId = corteExistente.getPresupuestoId();
        }

        if (corte.getPresupuestoId() == null || corte.getPresupuestoId().trim().isEmpty()) {
            result.rejectValue("presupuestoId", "error.corte", "Debe seleccionar un presupuesto.");
        } else {
            corte.setPresupuestoId(corte.getPresupuestoId().trim());
        }

        Presupuesto presupuesto = null;
        if (corte.getPresupuestoId() != null && !corte.getPresupuestoId().isBlank()) {
            presupuesto = presupuestoRepository.findById(corte.getPresupuestoId()).orElse(null);
            if (presupuesto == null) {
                result.rejectValue("presupuestoId", "error.corte", "El presupuesto seleccionado no existe.");
            }
        }

        List<DetalleCorte> detallesEntrada = corte.getDetalles() != null ? corte.getDetalles() : new ArrayList<>();
        List<DetalleCorte> detallesProcesados = new ArrayList<>();
        double totalCorte = 0.0;

        if (detallesEntrada.isEmpty()) {
            result.reject("error.corte", "Debe agregar al menos un material al corte.");
        } else {
            for (int i = 0; i < detallesEntrada.size(); i++) {
                DetalleCorte detalle = detallesEntrada.get(i);

                if (detalle == null) {
                    continue;
                }

                String materialId = detalle.getMaterialId() != null ? detalle.getMaterialId().trim() : null;
                Integer cantidad = detalle.getCantidad();

                boolean filaVacia = (materialId == null || materialId.isBlank()) && cantidad == null;
                if (filaVacia) {
                    continue;
                }

                if (materialId == null || materialId.isBlank()) {
                    result.reject("error.corte", "Todas las filas diligenciadas deben tener material seleccionado.");
                    continue;
                }

                if (cantidad == null || cantidad <= 0) {
                    result.reject("error.corte", "Todas las cantidades del corte deben ser mayores que 0.");
                    continue;
                }

                Material material = materialRepository.findById(materialId).orElse(null);
                if (material == null) {
                    result.reject("error.corte", "Uno de los materiales seleccionados no existe.");
                    continue;
                }

                Double precioAplicado = null;
                if (editando && detalle.getPrecioUnitario() != null && detalle.getPrecioUnitario() > 0) {
                    precioAplicado = detalle.getPrecioUnitario();
                }

                if (precioAplicado == null) {
                    precioAplicado = material.getValorUnitario();
                }

                if (precioAplicado == null || precioAplicado <= 0) {
                    result.reject("error.corte", "Uno de los materiales no tiene un precio unitario válido.");
                    continue;
                }

                DetalleCorte detalleNuevo = new DetalleCorte();
                detalleNuevo.setMaterialId(materialId);
                detalleNuevo.setCantidad(cantidad);
                detalleNuevo.setPrecioUnitario(precioAplicado);
                detalleNuevo.setSubtotal(cantidad * precioAplicado);

                totalCorte += detalleNuevo.getSubtotal();
                detallesProcesados.add(detalleNuevo);
            }
        }

        if (detallesProcesados.isEmpty()) {
            result.reject("error.corte", "Debe agregar al menos un material válido al corte.");
        }

        if (!result.hasErrors() && presupuesto != null) {
            double totalOtrosCortes = calcularTotalOtrosCortes(corte.getPresupuestoId(), corte.getId());
            double montoMaximo = presupuesto.getMontoMaximo() != null ? presupuesto.getMontoMaximo() : 0.0;

            if (totalOtrosCortes + totalCorte > montoMaximo) {
                NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
                formatoMoneda.setMaximumFractionDigits(0);
                formatoMoneda.setMinimumFractionDigits(0);

                result.reject(
                        "error.corte",
                        "El corte supera el saldo disponible del presupuesto. Disponible: "
                                + formatoMoneda.format(montoMaximo - totalOtrosCortes)
                                + " | Total del corte: "
                                + formatoMoneda.format(totalCorte)
                );
            }
        }

        if (result.hasErrors()) {
            if (detallesProcesados.isEmpty()) {
                if (detallesEntrada.isEmpty()) {
                    detallesEntrada.add(new DetalleCorte());
                }
                corte.setDetalles(detallesEntrada);
            } else {
                corte.setDetalles(detallesProcesados);
            }
            corte.setTotal(totalCorte);

            model.addAttribute("titulo", editando ? "Editar Corte" : "Formulario de Corte");
            model.addAttribute("presupuestoOpciones", construirPresupuestoOpciones());
            model.addAttribute("materiales", obtenerMaterialesOrdenados());
            model.addAttribute("modoEdicion", editando);
            return "corte/form";
        }

        if (corte.getId() != null && corte.getId().trim().isEmpty()) {
            corte.setId(null);
        }

        corte.setDetalles(detallesProcesados);
        corte.setTotal(totalCorte);
        corteRepository.save(corte);

        recalcularTotalGastadoPresupuesto(corte.getPresupuestoId());

        if (presupuestoAnteriorId != null && !presupuestoAnteriorId.equals(corte.getPresupuestoId())) {
            recalcularTotalGastadoPresupuesto(presupuestoAnteriorId);
        }

        flash.addFlashAttribute("success", editando ? "Corte actualizado correctamente." : "Corte guardado correctamente.");
        return "redirect:/corte/listar";
    }

    @GetMapping("/form/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes flash) {
        Corte corte = corteRepository.findById(id).orElse(null);

        if (corte == null) {
            flash.addFlashAttribute("error", "El corte que intentas editar no existe.");
            return "redirect:/corte/listar";
        }

        if (corte.getDetalles() == null || corte.getDetalles().isEmpty()) {
            corte.setDetalles(new ArrayList<>());
            corte.getDetalles().add(new DetalleCorte());
        }

        model.addAttribute("corte", corte);
        model.addAttribute("presupuestoOpciones", construirPresupuestoOpciones());
        model.addAttribute("materiales", obtenerMaterialesOrdenados());
        model.addAttribute("titulo", "Editar Corte");
        model.addAttribute("modoEdicion", true);
        return "corte/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes flash) {
        Corte corte = corteRepository.findById(id).orElse(null);

        if (corte != null) {
            String presupuestoId = corte.getPresupuestoId();
            corteRepository.deleteById(id);
            recalcularTotalGastadoPresupuesto(presupuestoId);
            flash.addFlashAttribute("success", "Corte eliminado correctamente.");
        } else {
            flash.addFlashAttribute("error", "El corte que intentas eliminar no existe.");
        }

        return "redirect:/corte/listar";
    }

    private List<Material> obtenerMaterialesOrdenados() {
        return materialRepository.findAll().stream()
                .sorted(Comparator.comparing(Material::getNombre, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    private List<PresupuestoOptionDTO> construirPresupuestoOpciones() {
        Map<String, String> proyectosMap = proyectoRepository.findAll().stream()
                .filter(proyecto -> proyecto.getId() != null)
                .collect(Collectors.toMap(Proyecto::getId, Proyecto::getNombre, (a, b) -> a));

        return presupuestoRepository.findAll().stream()
                .map(presupuesto -> {
                    double montoMaximo = presupuesto.getMontoMaximo() != null ? presupuesto.getMontoMaximo() : 0.0;
                    double totalGastado = presupuesto.getTotalGastado() != null ? presupuesto.getTotalGastado() : 0.0;
                    double saldoDisponible = montoMaximo - totalGastado;
                    String nombreProyecto = proyectosMap.getOrDefault(presupuesto.getProyectoId(), "Sin proyecto");

                    return new PresupuestoOptionDTO(
                            presupuesto.getId(),
                            nombreProyecto,
                            montoMaximo,
                            totalGastado,
                            saldoDisponible
                    );
                })
                .sorted(Comparator.comparing(PresupuestoOptionDTO::getNombreProyecto, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    private double calcularTotalOtrosCortes(String presupuestoId, String corteIdExcluir) {
        return corteRepository.findByPresupuestoId(presupuestoId).stream()
                .filter(c -> corteIdExcluir == null || !corteIdExcluir.equals(c.getId()))
                .map(Corte::getTotal)
                .filter(total -> total != null)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private void recalcularTotalGastadoPresupuesto(String presupuestoId) {
        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId).orElse(null);
        if (presupuesto == null) {
            return;
        }

        double totalGastado = corteRepository.findByPresupuestoId(presupuestoId).stream()
                .map(Corte::getTotal)
                .filter(total -> total != null)
                .mapToDouble(Double::doubleValue)
                .sum();

        presupuesto.setTotalGastado(totalGastado);
        presupuestoRepository.save(presupuesto);
    }
}
