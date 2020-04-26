package pojos;

/**
 *
 * @author Hp
 */
 
import java.io.Serializable;
public class Departamento implements Serializable{
    int deptno;
    String dnombre;
    String loc;

    public Departamento() {
    }

    public Departamento(int deptno, String dnombre, String loc) {
        this.deptno = deptno;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "DEPARTAMENTO-->" + "deptno=" + deptno + ", dnombre=" + dnombre + ", loc=" + loc + '}';
    }
    
    
}
