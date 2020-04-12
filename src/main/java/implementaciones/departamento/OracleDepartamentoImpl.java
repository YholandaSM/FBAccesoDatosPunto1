package implementaciones.departamento;

import daos.DepartamentoDAO;
import factory.OracleDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojos.Departamento;

/**
 *
 * @author Hp
 */
public class OracleDepartamentoImpl implements DepartamentoDAO {

    Connection conexion;

    public OracleDepartamentoImpl() {
        conexion = OracleDAOFactory.crearConexion();
    }

    @Override
    public boolean insertarDep(Departamento dep) {
        boolean valor = false;
        String sql = "INSERT INTO departamentos VALUES(?, ?, ?)";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, dep.getDeptno());
            sentencia.setString(2, dep.getDnombre());
            sentencia.setString(3, dep.getLoc());
            int filas = sentencia.executeUpdate();
            //System.out.printf("Filas insertadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Departamento %d insertado%n", dep.getDeptno());
            }
            sentencia.close();

        } catch (SQLException e) {
            mensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public boolean eliminarDep(int deptno) {
        boolean valor = false;
        String sql = "DELETE FROM departamentos WHERE dept_no = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, deptno);
            int filas = sentencia.executeUpdate();
            //System.out.printf("Filas eliminadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Departamento %d eliminado%n", deptno);
            }
            sentencia.close();
        } catch (SQLException e) {
            mensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public boolean modificarDep(int num, Departamento dep) {
        boolean valor = false;
        String sql = "UPDATE departamentos SET dnombre= ?, loc = ? WHERE dept_no = ? ";
        PreparedStatement sentencia;
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(3, num);
            sentencia.setString(1, dep.getDnombre());
            sentencia.setString(2, dep.getLoc());
            int filas = sentencia.executeUpdate();
            //System.out.printf("Filas modificadas: %d%n", filas);
            if (filas > 0) {
                valor = true;
                System.out.printf("Departamento %d modificado%n", num);
            }
            sentencia.close();
        } catch (SQLException e) {
            mensajeExcepcion(e);
        }
        return valor;
    }

    @Override
    public Departamento consultarDep(int deptno) {
        String sql = "SELECT dept_no, dnombre, loc FROM departamentos WHERE dept_no =  ?";
        PreparedStatement sentencia;
        Departamento dep = new Departamento();
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, deptno);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                dep.setDeptno(rs.getInt("dept_no"));
                dep.setDnombre(rs.getString("dnombre"));
                dep.setLoc(rs.getString("loc"));
            } else {
                System.out.printf("Departamento: %d No existe%n", deptno);
            }

            rs.close();// liberar recursos
            sentencia.close();

        } catch (SQLException e) {
            mensajeExcepcion(e);
        }
        return dep;
    }

    public ArrayList<Departamento> listarDep() {
        String sql = "SELECT dept_no, dnombre, loc FROM departamentos";
        Statement sentencia;
        ArrayList<Departamento> listado = new ArrayList();

        try {
            sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setDeptno(rs.getInt("dept_no"));
                dep.setDnombre(rs.getString("dnombre"));
                dep.setLoc(rs.getString("loc"));
                listado.add(dep);
            }

            rs.close();// liberar recursos
            sentencia.close();

        } catch (SQLException e) {
            mensajeExcepcion(e);
        }
        return listado;
    }

    private void mensajeExcepcion(SQLException e) {
        System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
        System.out.printf("Mensaje   : %s %n", e.getMessage());
        System.out.printf("SQL estado: %s %n", e.getSQLState());
        System.out.printf("Cód error : %s %n", e.getErrorCode());
    }

}
