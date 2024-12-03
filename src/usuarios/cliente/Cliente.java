package usuarios.cliente;
import banco.Banco;
import tarjetas.TarjetaCredito;
import tarjetas.TarjetaDebito;
import resourses.Rol;
import usuarios.Usuarios;
import java.time.LocalDate;

public class Cliente extends Usuarios {
    Banco banco = new Banco();
    public LocalDate fechaRegistro;
    public TarjetaDebito tarjetaDebito;
    public TarjetaCredito tarjetaCredito;


    public Cliente(String id, String nombre, String apellido, String curp, String RFC, String direccion, LocalDate fechaRegistro, String contrasenia, String usuario) {
        super(id, nombre, apellido, curp, RFC, direccion, Rol.CLIENTE, contrasenia, usuario );
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

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
                "\"fechaRegistro\":\"" + this.fechaRegistro + "\"" +
                "}";
    }

    public String datos(){
        String datos = String.format("Nombre: %s    Apellido: %s    CURP: %s    RFC: %s     Direccion: %s       Fecha de Registro: %s       Usuario: %s",nombre,apellido,curp,RFC,direccion,fechaRegistro.toString(),usuario);
        return datos;
    }
}