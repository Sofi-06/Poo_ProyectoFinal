package edu.usta.entidades;

public class Ciudad {

    private Integer codCiudad;
    private String nombreCiudad;
    private String daneCiudad;
    private Departamento codDepartamento;
    private Integer cantRevistas;

    public Ciudad() {
    }

    public Ciudad(Integer codCiudad, String nombreCiudad, String daneCiudad, Departamento codDepartamento, Integer cantRevistas) {
        this.codCiudad = codCiudad;
        this.nombreCiudad = nombreCiudad;
        this.daneCiudad = daneCiudad;
        this.codDepartamento = codDepartamento;
        this.cantRevistas = cantRevistas;
    }

    public Integer getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(Integer codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getDaneCiudad() {
        return daneCiudad;
    }

    public void setDaneCiudad(String daneCiudad) {
        this.daneCiudad = daneCiudad;
    }

    public Departamento getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(Departamento codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public Integer getCantRevistas() {
        return cantRevistas;
    }

    public void setCantRevistas(Integer cantRevistas) {
        this.cantRevistas = cantRevistas;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "codCiudad=" + codCiudad + ", nombreCiudad=" + nombreCiudad + ", daneCiudad=" + daneCiudad + ", codDepartamento=" + codDepartamento + ", cantRevistas=" + cantRevistas + '}';
    }

}
