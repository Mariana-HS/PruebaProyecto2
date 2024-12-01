package usuarios.empleados;

import resourses.Rol;
import usuarios.Usuarios;

public class Empleados extends Usuarios {
    private double salario;

    public Empleados(String id, String nombre, String apellido, String curp, String RFC, String direccion, double salario, String usuario, String contrasena) {
        super(id, nombre, apellido, curp, RFC, direccion, Rol.EMPLEADO);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String toString(){
        return "{" +
                "\"idUsuario\":\"" + this.idUsuario + "\"," +
                "\"nombre\":\"" + this.nombre + "\"," +
                "\"apellido\":\"" + this.apellido + "\"," +
                "\"curp\":\"" + this.curp + "\"," +
                "\"RFC\":\"" + this.RFC + "\"," +
                "\"direccion\":\"" + this.direccion + "\"," +
                "\"rol\":\"" + this.rol + "\"," +
                "\"usuario\":\"" + this.Usuario + "\"," +
                "\"contrasenia\":\"" + this.contrasenia + "\"," +
                "\"salario\":\"" + this.salario + "\"" +
                "}";
    }
}
