package menus.menuCliente;

import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;

import java.io.IOException;
import java.util.Scanner;
import banco.Banco;
public class MenuCliente {

    public void mostrarMenuCliente(Cliente clienteEnSesion, Banco banco)  throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        TarjetaDebito tarjetaDebito = banco.buscarTarjetaDebitoParaEliminar(clienteEnSesion.getIdUsuario()); //Utilziamos ese metodo debido a que utiliza el id para buscar

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

                    break;
                case 3:
                    System.out.println("CONSULTAR MOVIMIENTOS");

                    System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                    System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));

                    banco.historialMovimientosTarjetaDeDebito(tarjetaDebito);
                    break;
                    case 4:
                        System.out.println("CONSULTAR ULTIMOS MOVIMIENTOS");
                        System.out.println("Su tarjeta tiene numero: " + (tarjetaDebito.getNumeroTarjeta()));
                        System.out.println("Con CVV: " + (tarjetaDebito.getCvv()));

                        break;
                        case 5:
                            System.out.println("Saliendo del menu...");
                            return;

                default:
                    System.out.println("Opción no válida, por favor seleccione de nuevo.");
            }
        }
    }
}
