package menus.menuEmpleados;

import usuarios.empleados.Empleados;

import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class MenuEmpleados {

    public void mostrarMenuEmpleado(Empleados empleadosEnSesion) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("1. Registrar cliente.");
            System.out.println("2. Listar clientes.");
            System.out.println("3. Eliminar cliente.");
            System.out.println("4. Autorizar solicitud de tarjeta de crédito.");
            System.out.println("5. Consultar saldo cliente.");
            System.out.println("6. Activación o desactivación de tarjetas:");
            System.out.println("7. Salir");

            switch (opcion) {
                case 1:
                    System.out.println("REGISTRAR CLIENTE");
                case 2:
                    System.out.println("LISTAR CLIENTES");
                    break;
                    case 3:
                        System.out.println("ELIMINAR CLIENTE");
                        break;
                        case 4:
                            System.out.println("AUTORIZAR SOLICITUD DE TARJETA DE CREDITO");
                            break;
                            case 5:
                                System.out.println("CONSULTAR SALDO DE CLIENTE");
                                break;
                                case 6:
                                    System.out.println("ACTIVAR O DESACTIVAR TARJETAS");
                                    break;
                                    case 7:
                                        return;
            }

        }
    }
}
