package tarjetas;
import resourses.Utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class TarjetaDebito {
    public double saldo;
    public final String numeroTarjeta;
    public LocalDate fechaDeRegistro;
    public final int cvv;
    public LocalDate fechaVencimiento;
    public ArrayList<String> movimientos = new ArrayList<>();
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

    public ArrayList<String> getMovimientos() {
        return movimientos;
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }


    public void guardarMovimientos() throws IOException {
        Set<String> movimientosExistentes = new HashSet<>();
        File archivo = new File("movimientos.txt");
        if (archivo.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                movimientosExistentes.add(linea);
            }
            reader.close();
        }

        // Escribir solo movimientos nuevos
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true)); // Modo apéndice
        for (String movimiento : movimientos) {
            if (!movimientosExistentes.contains(movimiento)) {
                writer.write(movimiento + "\n");
            }
        }
        writer.close();
    }

    public void cargarMovimientos() throws IOException {
        FileReader file = new FileReader("movimientos.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null) {

            movimientos.add(line);
        }
        reader.close();
    }

    public void registrarMovimientoDeposito(double monto) {
        String movimiento = "Deposito "+ monto + LocalDateTime.now().toString();
        movimientos.add(movimiento);

        try {
            guardarMovimientos();
        } catch (IOException e) {
            System.out.println("Error al guardar los movimientos: " + e.getMessage());
        }
    }

    public void registrarMovimientoRetiro(double retiro) {
        String movimiento = "Retiro "+ retiro + LocalDateTime.now().toString();
        movimientos.add(movimiento);

        try {
            guardarMovimientos();
        } catch (IOException e) {
            System.out.println("Error al guardar los movimientos: " + e.getMessage());
        }
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
