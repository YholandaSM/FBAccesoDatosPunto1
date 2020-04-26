/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import daos.DepartamentoDAO;

import implementaciones.departamento.OracleDepartamentoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class OracleDAOFactory extends DAOFactory {

    static Connection conexion = null;
    static String DRIVER = "";
    static String URLDB = "";
    static String USUARIO = "unidad6";
    static String CLAVE = "unidad6";

    public OracleDAOFactory() {
        DRIVER = "oracle.jdbc.driver.OracleDriver";
        //    URLDB = "jdbc:mysql//localhost/unidad6";
    }

    //Crear la conexión
    public static Connection crearConexion() {
        if (conexion == null) {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            try {
                conexion = DriverManager.getConnection("jdbc:oracle:thin:1521:XE", "ejemplo", "ejemplo");

            } catch (SQLException e) {

                System.out.println("Error " + e.getMessage() + "-" + e.getSQLState());
                conexion = null;

            }

        }
        return conexion;
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new OracleDepartamentoImpl();
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
    }

}
