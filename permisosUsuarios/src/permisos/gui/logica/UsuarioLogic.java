/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package permisos.gui.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import permisos.gui.logica.entidades.Usuario;
import static permisos.utilitario.Conexion.conectarse;
import permisos.utilitario.ErrorBD;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.20
 * @desc 
 */
public class UsuarioLogic {

    public Usuario consultaUsuario(int _IdUsuario) {
        Usuario u = new Usuario();

        try {
            Connection con = conectarse();

            PreparedStatement s = con.prepareStatement("EXEC sp_consultaUsuario ?");
            s.setInt(1, _IdUsuario);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                u.setUsuId(rs.getInt("usuId"));
                u.setUsuLogin(rs.getString("usuLogin"));
                u.setUsuIden(rs.getString("usuIden"));
                u.setUsuIdenTipo(rs.getInt("usuIdenTipo"));
                u.setUsuPrimerNombre(rs.getString("usuPrimerNombre"));
                u.setUsuSegundoNombre(rs.getString("usuSegundoNombre"));
                u.setUsuPrimerApellido(rs.getString("usuPrimerApellido"));
                u.setUsuSegundoApellido(rs.getString("usuSegundoApellido"));
                u.setUsuDireccion(rs.getString("usuDireccion"));
                u.setUsuTelefono(rs.getString("usuTelefono"));
                u.setUsuEmail(rs.getString("usuEmail"));
                u.setUsuPerfil(rs.getInt("usuPerfil"));
                u.setUsuFechaRegistro(rs.getDate("usuFechaRegistro"));
                u.setUsuClave(rs.getString("usuClave"));
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return u;
    }

    public void GuardaEditaUsuario(int IdPerfilLogeado, Usuario u) {

        try {
            Connection con = conectarse();

            PreparedStatement s = con.prepareStatement("EXEC sp_creaEditaUsuario ?,?,?,?,?,?,?,?,?,?,?,?,?");
            s.setInt(1, IdPerfilLogeado);
            s.setInt(2, u.getUsuPerfil());
            s.setString(3, u.getUsuLogin());
            s.setString(4, u.getUsuClave());
            s.setInt(5, u.getUsuIdenTipo());
            s.setString(6, u.getUsuIden());
            s.setString(7, u.getUsuPrimerNombre());
            s.setString(8, u.getUsuSegundoNombre());
            s.setString(9, u.getUsuPrimerApellido());
            s.setString(10, u.getUsuSegundoApellido());
            s.setString(11, u.getUsuDireccion());
            s.setString(12, u.getUsuTelefono());
            s.setString(13, u.getUsuEmail());

            s.execute();

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

    }
}
