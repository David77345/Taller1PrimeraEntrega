package com.cliente.demo.Modelos.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "cortes")
public class Corte {

    @Id
    private String id;

    @NotBlank(message = "Debe seleccionar un presupuesto")
    private String presupuestoId;

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private String observacion;

    private List<DetalleCorte> detalles = new ArrayList<>();

    private Double total = 0.0;

    public Corte() {
    }

    public Corte(String id, String presupuestoId, Date fecha, String observacion, List<DetalleCorte> detalles, Double total) {
        this.id = id;
        this.presupuestoId = presupuestoId;
        this.fecha = fecha;
        this.observacion = observacion;
        this.detalles = detalles;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getPresupuestoId() {
        return presupuestoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public List<DetalleCorte> getDetalles() {
        return detalles;
    }

    public Double getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPresupuestoId(String presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setDetalles(List<DetalleCorte> detalles) {
        this.detalles = detalles;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}