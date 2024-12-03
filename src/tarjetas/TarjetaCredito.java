package tarjetas;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TarjetaCredito extends TarjetaDebito{
    public ArrayList<String> movimientosCredito = new ArrayList<>();

    public TarjetaCredito(double saldo, String numeroTarjeta, LocalDate fecha, int cvv, LocalDate fechaVencimiento, String idCliente) {
        super(saldo, numeroTarjeta, fecha, cvv, fechaVencimiento, idCliente);
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
