package factory;

import daos.DepartamentoDAO;
 
import implementaciones.departamento.NeodatisDepartamentoImpl;
 
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 *
 * @author Hp
 */
public class NeodatisDAOFactory extends DAOFactory{

   

    static ODB odb = null;

    public NeodatisDAOFactory() {

    }

    public static ODB crearConexion() {
        if (odb == null) {
            odb = ODBFactory.open("./src/bbdd/Departamentos.BD");
        }
        return odb;
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new  NeodatisDepartamentoImpl();
    }

   
    
}
