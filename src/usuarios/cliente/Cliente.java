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

    public Cliente(String id, String nombre, String apellido, String curp, String RFC, String direccion, LocalDate fechaRegistro) {
        super(id, nombre, apellido, curp, RFC, direccion, Rol.CLIENTE);
        this.fechaRegistro = fechaRegistro;
        this.tarjetaDebito = tarjetaDebito; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public TarjetaDebito getTarjetaDebito() { return tarjetaDebito; }
    public TarjetaCredito getTarjetaCredito() { return tarjetaCredito; }

    public void solicitarTarjetaCredito() { if (tarjetaDebito.getSaldo() >= 30000) {
        this.tarjetaCredito = new TarjetaCredito(100000); // Límite de crédito

         System.out.println("Tarjeta de crédito asignada a " + getNombre());
    } else { System.out.println("Saldo insuficiente para solicitar tarjeta de crédito."); } }
    public boolean puedeSolicitarTarjetaCredito() { return tarjetaDebito.getSaldo() >= 30000; } }