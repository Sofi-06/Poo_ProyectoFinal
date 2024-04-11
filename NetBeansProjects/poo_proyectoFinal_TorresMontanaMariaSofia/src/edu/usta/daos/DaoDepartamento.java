package edu.usta.daos;

import edu.usta.configuracion.MiConexion;
import edu.usta.entidades.Departamento;
import edu.usta.interfaces.Funcionalidad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoDepartamento extends MiConexion implements Funcionalidad<Departamento> {

    @Override
    public Boolean registrar(Departamento elObjeto) {
        try {
            miCadenaSQL = "INSERT INTO departamentos (nombre_departamento, dane_departamento) VALUES (?, ?)";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreDepartamento());
            miConsulta.setString(2, elObjeto.getDaneDepartamento());

            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Departamento> consultar(String orden) {
        try {

            if (orden.isEmpty()) {
                orden = "cod_departamento";
            }
            miCadenaSQL = "SELECT d.cod_departamento, "
                    + "d.nombre_departamento, d.dane_departamento, "
                    + "(SELECT COUNT(cod_departamento) FROM ciudades WHERE cod_departamento = d.cod_departamento) AS cantCiudades "
                    + " FROM departamentos d ORDER BY " + orden;

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            misRegistros = miConsulta.executeQuery();

            List<Departamento> arrDepartamento = new ArrayList<>();

            while (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String dane = misRegistros.getString(3);
                Integer cant = misRegistros.getInt(4);

                Departamento objDepartamento = new Departamento(cod, nom, dane, cant);

                arrDepartamento.add(objDepartamento);
            }
            miObjConexion.close();
            return arrDepartamento;

        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Departamento buscar(Integer llavePrimaria) {
        try {
            miCadenaSQL = "SELECT d.cod_departamento, "
                    + "d.nombre_departamento, d.dane_departamento, "
                    + "(SELECT COUNT(cod_departamento) FROM ciudades WHERE cod_departamento = d.cod_departamento) AS cantCiudades "
                    + " FROM departamentos d WHERE d.cod_departamento = ?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);

            misRegistros = miConsulta.executeQuery();

            Departamento objEncontrada = null;
            if (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                String dane = misRegistros.getString(3);
                Integer cant = misRegistros.getInt(4);

                objEncontrada = new Departamento(cod, nom, dane, cant);
            }

            miObjConexion.close();
            return objEncontrada;

        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        try {
            miCadenaSQL = "DELETE FROM departamentos WHERE cod_departamento=? ";
            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);

            miCantidad = miConsulta.executeUpdate();

            miObjConexion.close();
            return miCantidad > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean actualizar(Departamento elObjeto) {
        try {
            miCadenaSQL = "UPDATE departamentos SET nombre_departamento=?, "
                    + "dane_departamento=? "
                    + "WHERE cod_departamento=?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreDepartamento());
            miConsulta.setString(2, elObjeto.getDaneDepartamento());
            miConsulta.setInt(3, elObjeto.getCodDepartamento());

            miCantidad = miConsulta.executeUpdate();

            miObjConexion.close();

            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Integer totalResgistros() {
        try {
            miCadenaSQL = "SELECT COUNT(cod_departamento) FROM departamentos";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            while (misRegistros.next()) {
                miCantidad = misRegistros.getInt(1);
            }

            miObjConexion.close();
            return miCantidad;
        } catch (SQLException ex) {
            Logger.getLogger(DaoDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
