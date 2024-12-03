package menus.menuCliente;

import tarjetas.TarjetaCredito;
import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;

import java.io.IOException;
import java.util.Scanner;
import banco.Banco;
public class MenuCliente {

    public void mostrarMenuCliente(Cliente clienteEnSesion, Banco banco) throws IOException {
        Scanner scanner = new Scanner(System.in);


        TarjetaCredito tarjetaCredito = banco.buscarTarjetaCredito(clienteEnSesion.getIdUsuario());
        tarjetaCredito.cargarMovimientos();
        TarjetaDebito tarjetaDebito = banco.buscarTarjetaDebitoParaEliminar(clienteEnSesion.getIdUsuario()); //Utilziamos ese metodo debido a que utiliza el id para buscar
        tarjetaDebito.cargarMovimientos();

        if (tarjetaDebito.getSaldo() < 30000) {
            int opcion = 0;
            while (opcion != 5) {
                System.out.println("** BIENVENIDO " + clienteEnSesion.getNombre() + " **");
                System.out.println("1. Realizar retiro o depósito");
                System.out.println("2. Consultar movimientos");
                System.out.println("3. Consultar ultimos movimientos");
                System.out.println("4. Salir");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Realizar retiro o déposito");
                        banco.consultarYActualizarEstadoCuentas(banco, scanner);
                        break;
                    case 2:
                        System.out.println("CONSULTAR MOVIMIENTOS");
                        System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                        System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));
                        banco.mostrarMovimientos(tarjetaDebito);
                        break;
                    case 3:
                        System.out.println("CONSULTAR ULTIMOS MOVIMIENTOS");
                        System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                        System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));
                        banco.obtenerUltimoMovimiento(tarjetaDebito);

                        break;
                    case 4:
                        System.out.println("Saliendo del menu...");
                        return;

                    default:
                        System.out.println("Opción no válida, por favor seleccione de nuevo.");
                }
            }
        } else {
            scanner.nextLine();
            int opcion = 0;
            while (opcion != 6) {
                System.out.println("** BIENVENIDO " + clienteEnSesion.getNombre() + " **");
                System.out.println("1. Realizar retiro o depósito DEBITO");
                System.out.println("2. Consultar movimientos DEBITO");
                System.out.println("3. Consultar ultimos movimientos DEBITO");
                System.out.println("4. Realizar retiro o depósito CREDITO");
                System.out.println("5. Consultar movimientos CREDITO");
                System.out.println("6. Salir");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Realizar retiro o déposito");
                        banco.consultarYActualizarEstadoCuentas(banco, scanner);
                        break;
                    case 2:
                        System.out.println("CONSULTAR MOVIMIENTOS");
                        System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                        System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));
                        banco.mostrarMovimientos(tarjetaDebito);
                        break;
                    case 3:
                        System.out.println("CONSULTAR ULTIMOS MOVIMIENTOS");
                        System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                        System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));
                        banco.obtenerUltimoMovimiento(tarjetaDebito);
                        break;
                    case 4:
                        System.out.println("Realizar retiro o déposito CREDITO");
                        banco.consultarYActualizarEstadoCuentasCredito(banco, scanner);
                        break;
                    case 5:
                        System.out.println("CONSULTAR MOVIMIENTOS CREDITO");
                        banco.mostrarMovimientosCredito(tarjetaCredito);
                        break;
                    case 6:
                        System.out.println("Saliendo del menu...");
                        return;

                    default:
                        System.out.println("Opción no válida, por favor seleccione de nuevo.");

                }
            }

        }
    }
}
