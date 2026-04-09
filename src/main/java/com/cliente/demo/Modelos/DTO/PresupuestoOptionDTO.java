package com.cliente.demo.Modelos.DTO;

public class PresupuestoOptionDTO {

    private String id;
    private String nombreProyecto;
    private Double montoMaximo;
    private Double totalGastado;
    private Double saldoDisponible;

    public PresupuestoOptionDTO() {
    }

    public PresupuestoOptionDTO(String id, String nombreProyecto, Double montoMaximo, Double totalGastado, Double saldoDisponible) {
        this.id = id;
        this.nombreProyecto = nombreProyecto;
        this.montoMaximo = montoMaximo;
        this.totalGastado = totalGastado;
        this.saldoDisponible = saldoDisponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(Double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public Double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public String getIdCorto() {
        if (id == null || id.length() <= 14) {
            return id;
        }
        return id.substring(0, 8) + "..." + id.substring(id.length() - 4);
    }
}
