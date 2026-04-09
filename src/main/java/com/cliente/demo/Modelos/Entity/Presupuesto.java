package com.cliente.demo.Modelos.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "presupuestos")
public class Presupuesto {

    @Id
    private String id;

    @NotBlank(message = "Debe seleccionar un proyecto")
    @Field("proyecto_id")
    private String proyectoId;

    @NotNull(message = "El monto máximo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto máximo debe ser mayor que 0")
    private Double montoMaximo;

    @Field("total_gastado")
    private Double totalGastado = 0.0;

    public Presupuesto() {
    }

    public Presupuesto(String id, String proyectoId, Double montoMaximo, Double totalGastado) {
        this.id = id;
        this.proyectoId = proyectoId;
        this.montoMaximo = montoMaximo;
        this.totalGastado = totalGastado;
    }

    public String getId() {
        return id;
    }

    public String getProyectoId() {
        return proyectoId;
    }

    public Double getMontoMaximo() {
        return montoMaximo;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProyectoId(String proyectoId) {
        this.proyectoId = proyectoId;
    }

    public void setMontoMaximo(Double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Double getSaldoDisponible() {
        double max = montoMaximo != null ? montoMaximo : 0.0;
        double gastado = totalGastado != null ? totalGastado : 0.0;
        return max - gastado;
    }
}