package edu.usta.entidades;

public class Revista {

    private Integer codRevista;
    private String nombreRevista;
    private String categoriaRevista;
    private String issnRevista;
    private Ciudad codCiudad;
    private Integer cantArticulos;

    public Revista() {
    }

    public Revista(Integer codRevista, String nombreRevista, String categoriaRevista, String issnRevista, Ciudad codCiudad, Integer cantArticulos) {
        this.codRevista = codRevista;
        this.nombreRevista = nombreRevista;
        this.categoriaRevista = categoriaRevista;
        this.issnRevista = issnRevista;
        this.codCiudad = codCiudad;
        this.cantArticulos = cantArticulos;
    }

    public Integer getCodRevista() {
        return codRevista;
    }

    public void setCodRevista(Integer codRevista) {
        this.codRevista = codRevista;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getCategoriaRevista() {
        return categoriaRevista;
    }

    public void setCategoriaRevista(String categoriaRevista) {
        this.categoriaRevista = categoriaRevista;
    }

    public String getIssnRevista() {
        return issnRevista;
    }

    public void setIssnRevista(String issnRevista) {
        this.issnRevista = issnRevista;
    }

    public Ciudad getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(Ciudad codCiudad) {
        this.codCiudad = codCiudad;
    }

    public Integer getCantArticulos() {
        return cantArticulos;
    }

    public void setCantArticulos(Integer cantArticulos) {
        this.cantArticulos = cantArticulos;
    }

    @Override
    public String toString() {
        return "Revista{" + "codRevista=" + codRevista + ", nombreRevista=" + nombreRevista + ", categoriaRevista=" + categoriaRevista + ", issnRevista=" + issnRevista + ", codCiudad=" + codCiudad + ", cantArticulos=" + cantArticulos + '}';
    }

}
