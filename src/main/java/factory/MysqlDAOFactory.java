package factory;

import daos.DepartamentoDAO;

import implementaciones.departamento.MysqlDepartamentoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hp
 */
public class MysqlDAOFactory extends DAOFactory {

    static Connection conexion = null;
    static String DRIVER = "";
    static String URLDB = "";
    static String USUARIO = "root";
    static String CLAVE = "";

    public MysqlDAOFactory() {
        DRIVER = "com.mysql.jdbc.Driver";
        URLDB = "jdbc:mysql://localhost:3308/fbaccesodatos";
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
                conexion = DriverManager.getConnection(URLDB, USUARIO, CLAVE);
            } catch (SQLException e) {
                System.out.println("Error " + e.getMessage() + "-" + e.getSQLState());
                conexion=null;
            }

        }
        return conexion;
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new MysqlDepartamentoImpl();
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
