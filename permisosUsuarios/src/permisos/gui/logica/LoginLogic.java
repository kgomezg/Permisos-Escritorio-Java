/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static permisos.utilitario.Conexion.conectarse;
import permisos.utilitario.Encriptar;
import permisos.utilitario.ErrorBD;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 */
public class LoginLogic {

    private final Encriptar e = new Encriptar();

    public LoginLogic() {

    }

    public int existeLogin(String _usuario, String _clave) {
        int IdUsuario = 0;

        try {
            Connection con = conectarse();
            PreparedStatement s = con.prepareStatement("EXEC sp_consultaLogin ?,?");
            s.setString(1, _usuario);
            s.setString(2, e.encriptarMD5(_clave));
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                IdUsuario = rs.getInt("IdUsuario");
            }

        } catch (Exception ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return IdUsuario;
    }

}
