package edu.usta.entidades;

import java.util.Date;

public class Articulo {

    private Integer codArticulo;
    private String nombreArticulo;
    private Date fechaPublicacionArticulo;
    private Short tipoArticulo;
    private Short cantidadPaginasArticulo;
    private Revista codRevista;

    public Articulo() {
    }

    public Articulo(Integer codArticulo, String nombreArticulo, Date fechaPublicacionArticulo, Short tipoArticulo, Short cantidadPaginasArticulo, Revista codRevista) {
        this.codArticulo = codArticulo;
        this.nombreArticulo = nombreArticulo;
        this.fechaPublicacionArticulo = fechaPublicacionArticulo;
        this.tipoArticulo = tipoArticulo;
        this.cantidadPaginasArticulo = cantidadPaginasArticulo;
        this.codRevista = codRevista;
    }

    public Integer getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Date getFechaPublicacionArticulo() {
        return fechaPublicacionArticulo;
    }

    public void setFechaPublicacionArticulo(Date fechaPublicacionArticulo) {
        this.fechaPublicacionArticulo = fechaPublicacionArticulo;
    }

    public Short getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(Short tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public Short getCantidadPaginasArticulo() {
        return cantidadPaginasArticulo;
    }

    public void setCantidadPaginasArticulo(Short cantidadPaginasArticulo) {
        this.cantidadPaginasArticulo = cantidadPaginasArticulo;
    }

    public Revista getCodRevista() {
        return codRevista;
    }

    public void setCodRevista(Revista codRevista) {
        this.codRevista = codRevista;
    }

    @Override
    public String toString() {
        return "Articulo{" + "codArticulo=" + codArticulo + ", nombreArticulo=" + nombreArticulo + ", fechaPublicacionArticulo=" + fechaPublicacionArticulo + ", tipoArticulo=" + tipoArticulo + ", cantidadPaginasArticulo=" + cantidadPaginasArticulo + ", codRevista=" + codRevista + '}';
    }

}
