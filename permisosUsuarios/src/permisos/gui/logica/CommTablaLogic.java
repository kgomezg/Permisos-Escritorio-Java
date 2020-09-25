/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui.logica;

import permisos.gui.logica.entidades.TipoPermiso;
import permisos.gui.logica.entidades.TipoDocumento;
import permisos.gui.logica.entidades.EstadoPermiso;
import permisos.gui.logica.entidades.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import permisos.utilitario.ErrorBD;
import java.util.List;
import static permisos.utilitario.Conexion.conectarse;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.20
 * @desc
 */
public class CommTablaLogic {

    public List<TipoDocumento> consultarTiposDocumento() {

        List<TipoDocumento> lista = new ArrayList<>();

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaCommTablas ?");
            s.setInt(1, 1);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                TipoDocumento tp = new TipoDocumento(rs.getInt("Id"), rs.getString("Descripcion"), rs.getString("DescripcionAbreviada"));
                lista.add(tp);
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return lista;
    }

    public List<Perfil> consultarPerfiles() {

        List<Perfil> lista = new ArrayList<>();

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaCommTablas ?");
            s.setInt(1, 2);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Perfil tp = new Perfil(rs.getInt("Id"), rs.getString("Perfil"), rs.getString("PerfilAbreviado"));
                lista.add(tp);
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return lista;
    }

    public List<TipoPermiso> consultarTiposPermiso() {

        List<TipoPermiso> lista = new ArrayList<>();

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaCommTablas ?");
            s.setInt(1, 3);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                TipoPermiso tp = new TipoPermiso(rs.getInt("Id"), rs.getString("TipoPermiso"));
                lista.add(tp);
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return lista;
    }

    public List<EstadoPermiso> consultarEstadosPermiso() {

        List<EstadoPermiso> lista = new ArrayList<>();

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaCommTablas ?");
            s.setInt(1, 4);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                EstadoPermiso tp = new EstadoPermiso(rs.getInt("Id"), rs.getString("Estado"));
                lista.add(tp);
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return lista;
    }
}
