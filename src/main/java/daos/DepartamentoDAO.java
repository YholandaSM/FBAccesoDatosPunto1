package daos;

import java.util.ArrayList;
import pojos.Departamento;

/**
 *
 * @author Hp
 */
public interface DepartamentoDAO {

    public boolean insertarDep(Departamento dep);

    public boolean eliminarDep(int deptno);

    public boolean modificarDep(int deptno, Departamento dep);

    public Departamento consultarDep(int deptno);

    public ArrayList listarDep();
    
   

}
