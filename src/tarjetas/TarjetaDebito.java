package tarjetas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TarjetaDebito {
    private double saldo;
    public final String numeroTarjeta;
    public LocalDate fechaDeRegistro;
    private final int cvv;
    public LocalDate fechaVencimiento;
    private ArrayList<LocalDateTime> movimientos = new ArrayList<>();

    public TarjetaDebito(double saldo, String numeroTarjeta, LocalDate fecha, int cvv, LocalDate fechaVencimiento) {
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaDeRegistro = fecha;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.movimientos = new ArrayList<>(movimientos);
    }


    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public LocalDate getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public ArrayList<LocalDateTime> getMovimientos() {
        return movimientos;
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
        } else {
            for (LocalDateTime movimiento : movimientos) {
                System.out.println(movimiento);
            }
        }
    }
}
