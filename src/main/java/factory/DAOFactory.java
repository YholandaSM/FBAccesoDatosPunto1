 package factory;

import daos.DepartamentoDAO;
 
 

/**
 *
 * @author Hp
 */
public abstract class DAOFactory {

    //Bases de datos soportadas
    public static final int MYSQL = 1;
    public static final int NEODATIS = 2;
    public static final int ORACLE = 3;

    public abstract DepartamentoDAO getDepartamentoDAO();
    

  

    public static DAOFactory getDAOFactory(int bd) {
        switch (bd) {
            case MYSQL:
                return new MysqlDAOFactory();
            case NEODATIS:
                return new NeodatisDAOFactory();
            case ORACLE:
                return new OracleDAOFactory();
            default:
                return null;
        }
    }

}
