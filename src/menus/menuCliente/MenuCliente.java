package menus.menuCliente;

import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;

import java.util.Scanner;
import banco.Banco;
public class MenuCliente {

    public void mostrarMenuCliente(Cliente clienteEnSesion, Banco banco) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("** BIENVENIDO " + clienteEnSesion.getNombre() + " **");
            System.out.println("1. Realizar retiro");
            System.out.println("2. Realizar un depósito");
            System.out.println("3. Consultar movimientos");
            System.out.println("4. Consultar ultimos movimientos");
            System.out.println("5. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    System.out.println("CONSULTAR MOVIMIENTOS");
                    System.out.println("Ingresa la tarjeta de debito");
                    TarjetaDebito tarjetaDebito = clienteEnSesion.getTarjetaDebito();
                    banco.historialMovimientosTarjetaDeDebito(tarjetaDebito);
                    break;
                    case 4:
                        System.out.println("CONSULTAR ULTIMOS MOVIMIENTOS");
                        System.out.println("Ingresa la tarjeta de debito");
                        TarjetaDebito tarjetaDebito1 = clienteEnSesion.getTarjetaDebito();
                        banco.obtenerUltimoMovimiento(tarjetaDebito1);

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
