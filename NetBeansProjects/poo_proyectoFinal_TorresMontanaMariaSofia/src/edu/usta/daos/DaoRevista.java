package edu.usta.daos;

import edu.usta.configuracion.MiConexion;
import edu.usta.entidades.Ciudad;
import edu.usta.entidades.Departamento;
import edu.usta.entidades.Revista;
import edu.usta.interfaces.Funcionalidad;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoRevista extends MiConexion implements Funcionalidad<Revista> {

    @Override
    public Boolean registrar(Revista elObjeto) {
        try {
            miCadenaSQL = "INSERT INTO revistas (nombre_revista, categoria_revista, issn_revista, cod_ciudad) VALUES (?, ?, ?, ?)";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreRevista());
            miConsulta.setString(2, elObjeto.getCategoriaRevista());
            miConsulta.setString(3, elObjeto.getIssnRevista());
            miConsulta.setInt(4, elObjeto.getCodCiudad().getCodCiudad());

            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public List<Revista> consultar(String orden) {

        try {

            if (orden.isEmpty()) {
                orden = "cod_revista";
            }
            miCadenaSQL = "SELECT r.cod_revista, "
                    + "r.nombre_revista, "
                    + "r.categoria_revista, "
                    + "r.issn_revista, "
                    + "(SELECT COUNT(cod_revista) FROM articulos WHERE cod_revista = r.cod_revista) AS cantArticulos, "
                    + "c.cod_ciudad, "
                    + "c.nombre_ciudad, "
                    + "d.cod_departamento, "
                    + "d.nombre_departamento "
                    + "FROM revistas r "
                    + "INNER JOIN ciudades c ON r.cod_ciudad = c.cod_ciudad "
                    + "INNER JOIN departamentos d ON c.cod_departamento = d.cod_departamento "
                    + "ORDER BY " + orden;

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            List<Revista> arrRevistas = new ArrayList<>();
            while (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String cat = misRegistros.getString(3);
                String issn = misRegistros.getString(4);
                Integer cant = misRegistros.getInt(5);
                Integer codCiudad = misRegistros.getInt(6);
                String nomCiudad = misRegistros.getString(7);
                Integer codDep = misRegistros.getInt(8);
                String nombreDep = misRegistros.getString(9);

                Departamento objDepartamento = new Departamento(codDep, nombreDep, "", 0);
                Ciudad objCiudad = new Ciudad(codCiudad, nomCiudad, "", objDepartamento, 0);
                Revista objRevista = new Revista(cod, nom, cat, issn, objCiudad, cant);

                arrRevistas.add(objRevista);
            }
            miObjConexion.close();
            return arrRevistas;

        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Revista buscar(Integer llavePrimaria) {
        try {

            miCadenaSQL = "SELECT r.cod_revista, "
                    + "r.nombre_revista, r.categoria_revista, "
                    + "r.issn_revista, "
                    + "(SELECT COUNT(cod_revista) FROM articulos WHERE cod_revista = r.cod_revista) AS cantArticulos, c.cod_ciudad, c.nombre_ciudad, d.cod_departamento, d.nombre_departamento "
                    + "FROM revistas r "
                    + "INNER JOIN ciudades c ON r.cod_ciudad = c.cod_ciudad "
                    + "INNER JOIN departamentos d ON c.cod_departamento = d.cod_departamento "
                    + "WHERE r.cod_revista = ?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);

            misRegistros = miConsulta.executeQuery();

            Revista objEncontrada = null;
            if (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String cat = misRegistros.getString(3);
                String issn = misRegistros.getString(4);
                Integer cant = misRegistros.getInt(5);
                Integer codCiudad = misRegistros.getInt(6);
                String nomCiudad = misRegistros.getString(7);
                Integer codDep = misRegistros.getInt(8);
                String nombreDep = misRegistros.getString(9);

                Departamento objDepartamento = new Departamento(codDep, nombreDep, "", 0);

                Ciudad objCiudad = new Ciudad(codCiudad, nomCiudad, "", objDepartamento, 0);
                objEncontrada = new Revista(cod, nom, cat, issn, objCiudad, cant);

            }

            miObjConexion.close();
            return objEncontrada;

        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        try {
            miCadenaSQL = "DELETE FROM revistas WHERE cod_revista=? ";
            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);

            miCantidad = miConsulta.executeUpdate(); // se utiliza porque se va a manipular los datos

            miObjConexion.close();
            return miCantidad > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean actualizar(Revista elObjeto) {

        try {
            miCadenaSQL = "UPDATE revistas SET nombre_revista=?, "
                    + "categoria_revista=?, issn_revista=?, cod_ciudad=? "
                    + "WHERE cod_revista=?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreRevista());
            miConsulta.setString(2, elObjeto.getCategoriaRevista());
            miConsulta.setString(3, elObjeto.getIssnRevista());
            miConsulta.setInt(4, elObjeto.getCodCiudad().getCodCiudad());
            miConsulta.setInt(5, elObjeto.getCodRevista());

            miCantidad = miConsulta.executeUpdate();

            miObjConexion.close();

            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Integer totalResgistros() {
        try {
            miCadenaSQL = "SELECT COUNT(cod_revista) FROM revistas";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            while (misRegistros.next()) {
                miCantidad = misRegistros.getInt(1);
            }

            miObjConexion.close();
            return miCantidad;
        } catch (SQLException ex) {
            Logger.getLogger(DaoRevista.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
