package implementaciones.departamento;

import daos.DepartamentoDAO;
import factory.NeodatisDAOFactory;
import java.util.ArrayList;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import pojos.Departamento;

public class NeodatisDepartamentoImpl implements DepartamentoDAO {

    static ODB bd;

    public NeodatisDepartamentoImpl() {

    }

    @Override
    public boolean insertarDep(Departamento dep) {
        bd = NeodatisDAOFactory.crearConexion();
        try {
            bd.store(dep);
            bd.commit();
            System.out.printf("Departamento: %d Insertado %n", dep.getDeptno());
        } finally {
            NeodatisDAOFactory.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean eliminarDep(int deptno) {
        bd = NeodatisDAOFactory.crearConexion();
        boolean valor = false;
        IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
        Objects<Departamento> objetos = bd.getObjects(query);
        try {
            Departamento depart = (Departamento) objetos.getFirst();
            bd.delete(depart);
            bd.commit();
            valor = true;
        } catch (IndexOutOfBoundsException i) {
            System.out.printf("Departamento a eliminar: %d No existe%n", deptno);
        } finally {
            NeodatisDAOFactory.cerrarConexion();
        }
        // bd.close();
        return valor;
    }

    @Override
    public boolean modificarDep(int deptno, Departamento dep) {
        bd = NeodatisDAOFactory.crearConexion();
        boolean valor = false;
        IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
        Objects<Departamento> objetos = bd.getObjects(query);
        try {
            Departamento depart = (Departamento) objetos.getFirst();
            depart.setDnombre(dep.getDnombre());
            depart.setLoc(dep.getLoc());
            bd.store(depart); // actualiza el objeto 
            valor = true;

        } catch (IndexOutOfBoundsException i) {
            System.out.printf("Departamento: %d No existe%n", deptno);
        } finally {
            NeodatisDAOFactory.cerrarConexion();
        }

        return valor;
    }

    @Override
    public Departamento consultarDep(int deptno) {
        bd = NeodatisDAOFactory.crearConexion();
        IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
        Objects<Departamento> objetos = bd.getObjects(query);
        Departamento dep = new Departamento();
        if (objetos != null) {
            try {
                dep = (Departamento) objetos.getFirst();
                System.out.println(dep);
            } catch (IndexOutOfBoundsException i) {
                System.out.printf("Departamento: %d No existe%n", deptno);

            } finally {
                NeodatisDAOFactory.cerrarConexion();
            }
        }

        return dep;
    }

    @Override
    public ArrayList<Departamento> listarDep() {
        bd = NeodatisDAOFactory.crearConexion();
        ArrayList<Departamento> listado;
        try {
            IQuery query = new CriteriaQuery(Departamento.class);
            Objects<Departamento> objetos = bd.getObjects(query);
            listado = new ArrayList();
            Departamento dep = new Departamento();
            if (objetos != null) {
                while (objetos.hasNext()) {
                    listado.add(objetos.next());
                }
            }

        } finally {

            NeodatisDAOFactory.cerrarConexion();
        }

        return listado;
    }

}
