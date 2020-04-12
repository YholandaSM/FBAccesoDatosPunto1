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
        bd = NeodatisDAOFactory.crearConexion();
    }

    @Override
    public boolean insertarDep(Departamento dep) {
        bd.store(dep);
        bd.commit();
        System.out.printf("Departamento: %d Insertado %n", dep.getDeptno());
        return true;
    }

    @Override
    public boolean eliminarDep(int deptno) {
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
        }

        return valor;
    }

    @Override
    public boolean modificarDep(int deptno, Departamento dep) {
        boolean valor = false;
        IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
        Objects<Departamento> objetos = bd.getObjects(query);
        try {
            Departamento depart = (Departamento) objetos.getFirst();
            depart.setDnombre(dep.getDnombre());
            depart.setLoc(dep.getLoc());
            bd.store(depart); // actualiza el objeto 
            valor = true;
            bd.commit();
        } catch (IndexOutOfBoundsException i) {
            System.out.printf("Departamento: %d No existe%n", deptno);
        }

        return valor;
    }

    @Override
    public Departamento consultarDep(int deptno) {
        IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
        Objects<Departamento> objetos = bd.getObjects(query);
        Departamento dep = new Departamento();
        if (objetos != null) {
            try {
                dep = (Departamento) objetos.getFirst();
            } catch (IndexOutOfBoundsException i) {
                System.out.printf("Departamento: %d No existe%n", deptno);
                
            }
        }
        return dep;
    }
    
    @Override
    public ArrayList<Departamento> listarDep() {
        IQuery query = new CriteriaQuery(Departamento.class);
        Objects<Departamento> objetos = bd.getObjects(query);
        ArrayList<Departamento>listado = new ArrayList();
        Departamento dep = new Departamento();
        if (objetos != null) {
            while(objetos.hasNext()){
                listado.add(objetos.next());
            }
        }
        return listado;
    }
}
