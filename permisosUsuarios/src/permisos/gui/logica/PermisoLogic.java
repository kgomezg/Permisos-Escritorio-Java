/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import permisos.gui.logica.entidades.Permiso;
import static permisos.utilitario.Conexion.conectarse;
import permisos.utilitario.ErrorBD;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.22
 * @desc
 */
public class PermisoLogic {

    public AbstractTableModel DevolverTablaPermisos(int TipoConsulta, String parametro1) {

        DefaultTableModel dtm = new DefaultTableModel();

        try {
            ResultSet rs = ConsultarPermisos(TipoConsulta, parametro1);

            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            dtm.setDataVector(data, columnNames);

            return dtm;

        } catch (SQLException ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return dtm;
    }

    public ResultSet ConsultarPermisos(int TipoConsulta, String parametro1) {

        ResultSet rs = null;

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaPermisos ?, ?");

            s.setInt(1, TipoConsulta);
            s.setString(2, parametro1);

            rs = s.executeQuery();

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return rs;
    }

    public Permiso ConsultarPermiso(int IdPermiso) {
        Permiso p = new Permiso();

        if (IdPermiso > 0) {
            try {

                Connection con = conectarse();
                PreparedStatement s = con.prepareStatement("EXEC sp_consultaPermiso ?");

                s.setInt(1, IdPermiso);

                ResultSet rs = s.executeQuery();

                while (rs.next()) {
                    p.setId(rs.getInt("perId"));
                    p.setIdUsuario(rs.getInt("perIdUsuario"));
                    p.setTipoPermiso(rs.getInt("perTipoPermiso"));
                    p.setInicio(rs.getDate("perInicio"));
                    p.setFin(rs.getDate("perFin"));
                    p.setObsUsuario(rs.getString("perObsUsuario"));
                    p.setObsAdministrador(rs.getString("perObsAdministrador"));
                    p.setEstado(rs.getInt("perEstado"));
                    p.setFechaAccion(rs.getDate("perFechaAccion"));
                }

            } catch (Exception ex) {
                ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
            } catch (Throwable ex) {
                ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
            }
        }

        return p;
    }

    public int CrearEditarPermiso(int IdPerfilUsuarioLog, Permiso _Permiso) {
        int resultado = 0;

        try {

            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_creaEditaPermiso ?,?,?,?,?,?,?,?,?");

            s.setInt(1, IdPerfilUsuarioLog);
            s.setInt(2, _Permiso.getId());
            s.setInt(3, _Permiso.getIdUsuario());
            s.setInt(4, _Permiso.getTipoPermiso());
            s.setString(5, new SimpleDateFormat("dd/MM/yyyy").format(_Permiso.getInicio()));
            s.setString(6, new SimpleDateFormat("dd/MM/yyyy").format(_Permiso.getFin()));
            s.setString(7, _Permiso.getObsUsuario());
            s.setString(8, _Permiso.getObsAdministrador());
            s.setInt(9, _Permiso.getEstado());

            resultado = s.executeUpdate();

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }
        return resultado;
    }
}
