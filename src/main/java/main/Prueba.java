package main;

import implementaciones.departamento.NeodatisDepartamentoImpl;
import pojos.Departamento;

public class Prueba {

    public static void main(String[] args) {

        Departamento dep1 = new Departamento(1, "FINANZAS", "ORENSE");
        Departamento dep2 = new Departamento(2, "VENTAS", "ZARAGOZA");
        Departamento dep3 = new Departamento(3, "VENTAS", "LOGROÑO");

        NeodatisDepartamentoImpl impl = new NeodatisDepartamentoImpl();
      //  impl.insertarDep(dep1);
       // impl.insertarDep(dep2);
       // impl.insertarDep(dep3);

    }

}
