package com.cliente.demo.Modelos.Entity;

public class DetalleCorte {

    private String materialId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public DetalleCorte() {
    }

    public DetalleCorte(String materialId, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.materialId = materialId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}