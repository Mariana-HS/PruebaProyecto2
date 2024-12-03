package tarjetas;

import usuarios.Usuarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private boolean estado;
    public String idCliente;

    public TarjetaDebito(double saldo, String numeroTarjeta, LocalDate fecha, int cvv, LocalDate fechaVencimiento, String idCliente) {
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaDeRegistro = fecha;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.movimientos = new ArrayList<>(movimientos);
        this.estado = true;
        this.idCliente = idCliente;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }



    public double getSaldo() {
        return saldo;
    }

    public String getIdCliente() {
        return idCliente;
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

    public void guardarMovimientos() throws IOException {
        FileWriter file = new FileWriter("movimientos.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (LocalDateTime movimiento : movimientos) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(movimiento.toString());
            index++;
        }

        writer.close();
    }



    public String toString(){
        return "{" +
                "\"saldo\":\"" + this.saldo + "\"," +
                "\"numeroTarjeta\":\"" + this.numeroTarjeta + "\"," +
                "\"fechaRegistro\":\"" + this.fechaDeRegistro + "\"," +
                "\"cvv\":\"" + this.cvv + "\"," +
                "\"fechaVencimiento\":\"" + this.fechaVencimiento + "\"," +
                "\"idCliente\":\"" + this.idCliente + "\"," +
                "}";
    }
}
