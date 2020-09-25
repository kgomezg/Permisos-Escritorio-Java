/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.12
 * @desc
 */
public class Conexion {

    static Connection c;

    public static Connection conectarse() throws Throwable {
        PropiedadesConexion p = new PropiedadesConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connStsring = "jdbc:sqlserver://"
                    + p.getServer()
                    + ":"
                    + p.getPuerto()
                    + ";database="
                    + p.getBaseDatos()
                    + ";integratedSecurity=true;";

            c = DriverManager.getConnection(connStsring);

            System.out.println("Conexión Realizada Correctamente.");

        } catch (ClassNotFoundException | SQLException ex) {
            ErrorBD e = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }

        return c;
    }
}
