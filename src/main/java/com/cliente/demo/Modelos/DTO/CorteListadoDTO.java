package com.cliente.demo.Modelos.DTO;

import java.util.Date;

public class CorteListadoDTO {

    private String id;
    private String presupuestoId;
    private String nombreProyecto;
    private Date fecha;
    private Integer cantidadItems;
    private Double total;

    public CorteListadoDTO() {
    }

    public CorteListadoDTO(String id, String presupuestoId, String nombreProyecto, Date fecha, Integer cantidadItems, Double total) {
        this.id = id;
        this.presupuestoId = presupuestoId;
        this.nombreProyecto = nombreProyecto;
        this.fecha = fecha;
        this.cantidadItems = cantidadItems;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public String getPresupuestoId() {
        return presupuestoId;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
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

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}