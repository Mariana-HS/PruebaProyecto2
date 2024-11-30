package menus.menuGerente;

import banco.Banco;
import menus.menuCliente.MenuCliente;
import usuarios.cliente.Cliente;
import usuarios.gerente.Gerente;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuGerente {

    public void mostrarMenuGerente(Gerente gerenteEnSesion) {
        MenuCliente menuCliente = new MenuCliente();
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 8) {
            System.out.println("\n*BIENVENIDO*");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Modificar información clientes");
            System.out.println("3. Consultar y actualizar el estado de las cuentas");
            System.out.println("4. Desactivar cuentas de clientes");
            System.out.println("5. Asignar productos y servicios");
            System.out.println("6. Contratar o despedir empleados ");
            System.out.println("7. Modificar información de los empleados");
            System.out.println("8. Salir");

            System.out.print("\nSeleccione una opción:\n");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Registrar un nuevo cliente si no tiene cuenta
                    System.out.println("Registrando cliente:");

                    System.out.println("Ingresa su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingresa sus apellidos: ");
                    String apellidos = scanner.nextLine();

                    String id = banco.generarIdCliente(nombre, apellidos);

                    System.out.println("Ingresa el año de nacimiento del cliente: ");
                    int anio = scanner.nextInt();
                    System.out.println("Ingresa su mes de nacimiento: ");
                    int mes = scanner.nextInt();
                    System.out.println("Ingresa su día de nacimiento: ");
                    int dia = scanner.nextInt();

                    LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
                    scanner.nextLine();

                    System.out.println("Ingresa su CURP: ");
                    String curpCliente = scanner.nextLine();

                    while(!banco.validacionCurp(curpCliente)){
                        System.out.println("Ingresa una CURP válida: ");
                        curpCliente = scanner.nextLine();
                        banco.validacionCurp(curpCliente);
                    }

                    System.out.println("Ingresa su dirección:");
                    String direccionCliente = scanner.nextLine();

                    // Generar RFC automáticamente
                    String rfcCliente = banco.generarRFCDesdeCURP(curpCliente);


                    Cliente cliente = new Cliente(id, nombre, apellidos, curpCliente,rfcCliente,direccionCliente,fechaNacimiento);
                    banco.registrarCliente(id, nombre,apellidos,curpCliente,rfcCliente,direccionCliente,fechaNacimiento);

                    System.out.println("Registro del cliente exitoso!");

                    // Acceder al menú del cliente después del registro
                    menuCliente.mostrarMenuCliente(cliente);

                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("MODIFICAR CLIENTES");
                    System.out.println("Ingrese el nombre del cliente a modificar:");
                    String nombre1 = scanner.nextLine();
                    banco.modificarCliente(nombre1);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Actualizar estado de cuenta");
                    banco.consultarYActualizarEstadoCuentas(banco, scanner);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Desactivar la cuenta");
                    banco.desactivarCuentaCliente(banco, scanner);
                    break;
                case 5:
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Gestionar empleados");
                    banco.gestionarEmpleados(banco, scanner);
                    break;
                case 7:
                    scanner.nextLine();
                    System.out.println("Modificar empleados");
                    banco.modificarEmpleado(banco, scanner);
                    break;
                case 8:
                    System.out.println("Saliendo del menu...");
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, elige nuevamente.");
            }
        }
    }

}
