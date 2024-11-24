package usuarios.empleados;

import resourses.Rol;
import usuarios.Usuarios;

public class Empleados extends Usuarios {
    public double salario;
    public  String contrasena;

    public Empleados(String id,String nombre, String apellido, String curp, String RFC, String direccion, double salario, String contrasena) {
        super(id,nombre, apellido, curp, RFC, direccion, Rol.EMPLEADO);
        this.salario = salario;
        this.contrasena = contrasena;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
