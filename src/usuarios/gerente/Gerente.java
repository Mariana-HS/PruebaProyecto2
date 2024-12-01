package usuarios.gerente;

import resourses.Rol;
import usuarios.Usuarios;

public class Gerente extends Usuarios {
    public double salario;

    public Gerente(String id, String nombre, String apellido, String curp, String RFC, String direccion, double salario, String contrasena, String usuario) {
        super(id, nombre, apellido, curp, RFC, direccion, Rol.GERENTE, contrasena, usuario);
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
                "\"usuario\":\"" + this.usuario + "\"," +
                "\"contrasenia\":\"" + this.contrasenia + "\"," +
                "\"salario\":\"" + this.salario + "\"" +
                "}";
    }
}
