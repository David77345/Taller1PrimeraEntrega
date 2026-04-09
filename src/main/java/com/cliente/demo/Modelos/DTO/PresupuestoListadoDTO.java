package com.cliente.demo.Modelos.DTO;

public class PresupuestoListadoDTO {

    private String id;
    private String nombreProyecto;
    private Double montoMaximo;
    private Double totalGastado;
    private Double saldoDisponible;

    public PresupuestoListadoDTO() {
    }

    public PresupuestoListadoDTO(String id, String nombreProyecto, Double montoMaximo, Double totalGastado, Double saldoDisponible) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.montoMaximo = montoMaximo;
        this.totalGastado = totalGastado;
        this.saldoDisponible = saldoDisponible;
    }

    public String getId() {
        return id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public Double getMontoMaximo() {
        return montoMaximo;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setMontoMaximo(Double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}