/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.utilitario;

import java.sql.*;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.12
 * @desc
 */
public final class ErrorBD extends Conexion {

    private final String clase;
    private final String mensaje;
    private final String causa;

    public ErrorBD(String _cla, String _men, String _cau) {

        this.causa = _cau;
        this.mensaje = _men;
        this.clase = _cla;

        try {
            GuardarError();
        } catch (Throwable ex) {
            ErrorBD e = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }
    }

    private void GuardarError() throws Throwable {
        try ( Connection c = conectarse()) {
            PreparedStatement s = c.prepareStatement("EXEC sp_GuardarError ?,?,?");
            s.setString(1, this.clase);
            s.setString(2, this.causa);
            s.setString(3, this.mensaje);
            s.execute();
            System.out.println("Error Guardado Satisfactoriamente.");
        } catch (Exception ex) {

        }
    }
}
