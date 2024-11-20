package Usuarios.gerente;

import Usuarios.Usuarios;

public class Gerente extends Usuarios {
    public double salario;

    public Gerente(String nombre, String apellido, String curp, String RFC, String direccion, double salario) {
        super(nombre, apellido, curp, RFC, direccion);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
