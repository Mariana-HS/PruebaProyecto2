package Usuarios.empleados;

import resourses.Rol;
import Usuarios.Usuarios;

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
}
