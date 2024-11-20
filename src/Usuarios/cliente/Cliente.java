package Usuarios.cliente;

import Usuarios.Usuarios;

import java.time.LocalDateTime;

public class Cliente extends Usuarios {
    public LocalDateTime fechaRegistro;

    public Cliente(String nombre, String apellido, String curp, String RFC, String direccion, LocalDateTime fechaRegistro) {
        super(nombre, apellido, curp, RFC, direccion);
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
