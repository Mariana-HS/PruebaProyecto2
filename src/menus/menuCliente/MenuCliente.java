package menus.menuCliente;

import usuarios.cliente.Cliente;

import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenuCliente(Cliente clienteEnSesion) {
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

                    break;
                    case 4:

                        break;
                        case 5:

                            break;
                default:
                    System.out.println("Opción no válida, por favor seleccione de nuevo.");
            }
        }
    }
}
