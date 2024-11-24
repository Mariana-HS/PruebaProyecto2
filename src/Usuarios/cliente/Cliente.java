package usuarios.cliente;

import resourses.Rol;
import usuarios.Usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cliente extends Usuarios {
    public LocalDate fechaRegistro;

    public Cliente(String id,String nombre, String apellido, String curp, String RFC, String direccion, LocalDate fechaRegistro) {
        super(id,nombre, apellido, curp, RFC, direccion, Rol.CLIENTE);
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
