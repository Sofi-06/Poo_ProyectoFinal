package edu.usta.daos;

import edu.usta.configuracion.MiConexion;
import edu.usta.entidades.Articulo;
import edu.usta.entidades.Revista;
import edu.usta.interfaces.Funcionalidad;

import java.sql.SQLException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoArticulo extends MiConexion implements Funcionalidad<Articulo> {

    @Override
    public Boolean registrar(Articulo elObjeto) {
        try {
            miCadenaSQL = "INSERT INTO articulos "
                    + "(nombre_articulo, fecha_publicacion_articulo, tipo_articulo, cantidad_paginas_articulo, cod_revista) "
                    + "VALUES (?, ?, ?, ?, ?)";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreArticulo());

            //FECHA
            long milisegundos = elObjeto.getFechaPublicacionArticulo().getTime();
            Date fechaLista = new Date(milisegundos);
            miConsulta.setDate(2, fechaLista);
            //****

            miConsulta.setShort(3, elObjeto.getTipoArticulo());
            miConsulta.setShort(4, elObjeto.getCantidadPaginasArticulo());
            miConsulta.setInt(5, elObjeto.getCodRevista().getCodRevista());

            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Articulo> consultar(String orden) {
        try {

            if (orden.isEmpty()) {
                orden = "cod_articulo";
            }
            miCadenaSQL = "SELECT a.cod_articulo, a.nombre_articulo, a.fecha_publicacion_articulo, "
                    + "a.tipo_articulo, a.cantidad_paginas_articulo, r.cod_revista, r.nombre_revista "
                    + "FROM articulos a INNER JOIN revistas r "
                    + "ON a.cod_revista = r.cod_revista "
                    + "ORDER BY " + orden;

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            misRegistros = miConsulta.executeQuery();

            List<Articulo> arrArticulo = new ArrayList<>();

            while (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                Date fecha = misRegistros.getDate(3);
                Short tipo = misRegistros.getShort(4);
                Short cant = misRegistros.getShort(5);

                Integer codRev = misRegistros.getInt(6);
                String nomRev = misRegistros.getString(7);
                Revista objRevista = new Revista(codRev, nomRev, "", "", null, 0);

                Articulo objArticulo = new Articulo(cod, nom, fecha, tipo, cant, objRevista);

                arrArticulo.add(objArticulo);
            }

            miObjConexion.close();
            return arrArticulo;
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Articulo buscar(Integer llavePrimaria) {

        try {

            miCadenaSQL = "SELECT a.cod_articulo, a.nombre_articulo, a.fecha_publicacion_articulo, "
                    + "a.tipo_articulo, a.cantidad_paginas_articulo, r.cod_revista, r.nombre_revista "
                    + "FROM articulos a INNER JOIN revistas r "
                    + "ON a.cod_revista = r.cod_revista "
                    + "WHERE a.cod_articulo = ?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            miConsulta.setInt(1, llavePrimaria);
            misRegistros = miConsulta.executeQuery();

            Articulo objEncontrado = null;
            if (misRegistros.next()) {
                int cod = misRegistros.getInt(1);
                String nom = misRegistros.getString(2);
                Date fecha = misRegistros.getDate(3);
                Short tipo = misRegistros.getShort(4);
                Short cant = misRegistros.getShort(5);

                Integer codRev = misRegistros.getInt(6);
                String nomRev = misRegistros.getString(7);
                Revista objRevista = new Revista(codRev, nomRev, "", "", null, 0);

                objEncontrado = new Articulo(cod, nom, fecha, tipo, cant, objRevista);

            }

            miObjConexion.close();
            return objEncontrado;

        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    @Override
    public Boolean eliminar(Integer llavePrimaria) {
        try {

            miCadenaSQL = "DELETE FROM articulos WHERE cod_articulo = ?";
            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setInt(1, llavePrimaria);
            miCantidad = miConsulta.executeUpdate();
            miObjConexion.close();
            return miCantidad > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean actualizar(Articulo elObjeto) {
        try {
            miCadenaSQL = "UPDATE articulos SET nombre_articulo=?, "
                    + "fecha_publicacion_articulo=?, tipo_articulo=?, cantidad_paginas_articulo=?, cod_revista=? "
                    + "WHERE cod_articulo=?";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);

            miConsulta.setString(1, elObjeto.getNombreArticulo());

            //FECHA
            long milisegundos = elObjeto.getFechaPublicacionArticulo().getTime();
            Date fechaLista = new Date(milisegundos);
            miConsulta.setDate(2, fechaLista);
            //****

            miConsulta.setShort(3, elObjeto.getTipoArticulo());
            miConsulta.setShort(4, elObjeto.getCantidadPaginasArticulo());
            miConsulta.setInt(5, elObjeto.getCodRevista().getCodRevista());
            miConsulta.setInt(6, elObjeto.getCodArticulo());

            miCantidad = miConsulta.executeUpdate();

            miObjConexion.close();

            return miCantidad > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Integer totalResgistros() {
        try {
            miCadenaSQL = "SELECT COUNT(cod_articulo) FROM articulos";

            miConsulta = miObjConexion.prepareStatement(miCadenaSQL);
            misRegistros = miConsulta.executeQuery();

            while (misRegistros.next()) {
                miCantidad = misRegistros.getInt(1);
            }

            miObjConexion.close();
            return miCantidad;
        } catch (SQLException ex) {
            Logger.getLogger(DaoArticulo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
