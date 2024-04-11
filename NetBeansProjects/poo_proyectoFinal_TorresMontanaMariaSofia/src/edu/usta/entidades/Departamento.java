package edu.usta.entidades;

public class Departamento {

    private Integer codDepartamento;
    private String nombreDepartamento;
    private String daneDepartamento;
    private Integer cantCiudades;

    public Departamento() {
    }

    public Departamento(Integer codDepartamento, String nombreDepartamento, String daneDepartamento, Integer cantCiudades) {
        this.codDepartamento = codDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.daneDepartamento = daneDepartamento;
        this.cantCiudades = cantCiudades;
    }

    public Integer getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(Integer codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDaneDepartamento() {
        return daneDepartamento;
    }

    public void setDaneDepartamento(String daneDepartamento) {
        this.daneDepartamento = daneDepartamento;
    }

    public Integer getCantCiudades() {
        return cantCiudades;
    }

    public void setCantCiudades(Integer cantCiudades) {
        this.cantCiudades = cantCiudades;
    }

    @Override
    public String toString() {
        return "Departamento{" + "codDepartamento=" + codDepartamento + ", nombreDepartamento=" + nombreDepartamento + ", daneDepartamento=" + daneDepartamento + ", cantCiudades=" + cantCiudades + '}';
    }

}
