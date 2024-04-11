package edu.usta.daos;

import edu.usta.configuracion.MiConexion;
import edu.usta.entidades.Ciudad;
import edu.usta.entidades.Departamento;
import edu.usta.interfaces.Funcionalidad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCiudad extends MiConexion implements Funcionalidad<Ciudad> {

    @Override
    public Boolean registrar(Ciudad elObjeto) {
        try {
            miCadenaSQL = "INSERT INTO ciudades (nombre_ciudad, dane_ciudad, cod_departamento) VALUES (?, ?, ?)";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreCiudad());
            miConsulta.setString(2, elObjeto.getDaneCiudad());
            miConsulta.setInt(3, elObjeto.getCodDepartamento().getCodDepartamento());

            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Ciudad> consultar(String orden) {
        try {

            if (orden.isEmpty()) {
                orden = "cod_ciudad";
            }
            miCadenaSQL = "SELECT c.cod_ciudad, c.nombre_ciudad, c.dane_ciudad, "
                    + "(SELECT COUNT(cod_ciudad) FROM revistas WHERE cod_ciudad = c.cod_ciudad) AS cantRevistas, "
                    + "d.cod_departamento, d.nombre_departamento "
                    + "FROM ciudades c INNER JOIN departamentos d "
                    + "ON c.cod_departamento = d.cod_departamento "
                    + "ORDER BY " + orden;

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            List<Ciudad> arrCiudad = new ArrayList<>();

            while (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String dane = misRegistros.getString(3);
                Integer cant = misRegistros.getInt(4);

                int codDep = misRegistros.getInt(5);
                String nomDep = misRegistros.getString(6);

                Departamento objDepto = new Departamento(codDep, nomDep, "", 0);

                Ciudad objCiudad = new Ciudad(cod, nom, dane, objDepto, cant);

                arrCiudad.add(objCiudad);
            }

            miObjConexion.close();
            return arrCiudad;
        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Ciudad buscar(Integer llavePrimaria) {
        try {
            miCadenaSQL = "SELECT c.cod_ciudad, c.nombre_ciudad, c.dane_ciudad, "
                    + "(SELECT COUNT(cod_ciudad) FROM revistas WHERE cod_ciudad = c.cod_ciudad) AS cantRevistas, "
                    + "d.cod_departamento, d.nombre_departamento "
                    + "FROM ciudades c INNER JOIN departamentos d "
                    + "ON c.cod_departamento = d.cod_departamento "
                    + "WHERE c.cod_ciudad = ?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            miConsulta.setInt(1, llavePrimaria);
            misRegistros = miConsulta.executeQuery();

            Ciudad objEncontrado = null;
            if (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String dane = misRegistros.getString(3);
                Integer cant = misRegistros.getInt(4);

                int codDep = misRegistros.getInt(5);
                String nomDep = misRegistros.getString(6);

                Departamento objDepto = new Departamento(codDep, nomDep, "", 0);

                objEncontrado = new Ciudad(cod, nom, dane, objDepto, cant);
            }

            miObjConexion.close();
            return objEncontrado;

        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        try {

            miCadenaSQL = "DELETE FROM ciudades WHERE cod_ciudad = ?";
            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);
            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean actualizar(Ciudad elObjeto) {
        try {
            miCadenaSQL = "UPDATE ciudades SET nombre_ciudad=?, "
                    + "dane_ciudad=?, cod_departamento=? "
                    + "WHERE cod_ciudad=?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreCiudad());
            miConsulta.setString(2, elObjeto.getDaneCiudad());
            miConsulta.setInt(3, elObjeto.getCodDepartamento().getCodDepartamento());
            miConsulta.setInt(4, elObjeto.getCodCiudad());

            miCantidad = miConsulta.executeUpdate();

            miObjConexion.close();

            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Integer totalResgistros() {
        try {
            miCadenaSQL = "SELECT COUNT(cod_ciudad) FROM ciudades";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            while (misRegistros.next()) {
                miCantidad = misRegistros.getInt(1);
            }

            miObjConexion.close();
            return miCantidad;
        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public List<Ciudad> consultarPorDpto(String orden, Integer codDpto) {
        try {

            if (orden.isEmpty()) {
                orden = "c.cod_ciudad";
            }
            miCadenaSQL = "SELECT c.cod_ciudad, "
                    + "c.nombre_ciudad, c.dane_ciudad, c.cod_departamento "
                    + "FROM ciudades c "
                    + "WHERE c.cod_departamento = ? "
                    + "ORDER BY " + orden;

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, codDpto);

            misRegistros = miConsulta.executeQuery();

            List<Ciudad> arrCiudades = new ArrayList<>();

            while (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String dane = misRegistros.getString(3);

                Integer codDep = misRegistros.getInt(4);

                Departamento objDepartamento = new Departamento(codDep, "", "", 0);
                Ciudad objCiudad = new Ciudad(cod, nom, dane, objDepartamento, 0);

                arrCiudades.add(objCiudad);
            }
            miObjConexion.close();
            return arrCiudades;

        } catch (SQLException ex) {
            Logger.getLogger(DaoCiudad.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
