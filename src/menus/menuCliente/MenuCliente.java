package menus.menuCliente;

import Usuarios.cliente.Cliente;

import java.util.Scanner;

public class MenuCliente {

    public void mostrarMenuCliente(Cliente clienteEnSesion) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("** BIENVENID@ A CINEPOLIS **");
            System.out.println("1. Comprar boletos");
            System.out.println("2. Ver películas de cartelera");
            System.out.println("3. Ver mis reservas");
            System.out.println("4. Comprar alimentos");
            System.out.println("5. Salir");
            System.out.println("Selecciona una opción");
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
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor seleccione de nuevo.");
            }
        }
    }
}
