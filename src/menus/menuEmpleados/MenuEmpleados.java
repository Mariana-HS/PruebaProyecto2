package menus.menuEmpleados;

import usuarios.cliente.Cliente;
import usuarios.empleados.Empleados;
import menus.menuCliente.MenuCliente;
import banco.Banco;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.RecursiveTask;

public class MenuEmpleados {
    Banco banco = new Banco();
    MenuCliente menuCliente= new MenuCliente();

    public void mostrarMenuEmpleado(Empleados empleadosEnSesion) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        this.cargarEmpleados(banco);

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
                    Banco.registrarCliente(id, nombre,apellidos,curpCliente,rfcCliente,direccionCliente,fechaNacimiento);

                    System.out.println("Registro del cliente exitoso!");

                    // Acceder al menú del cliente después del registro
                    menuCliente.mostrarMenuCliente(cliente);
                case 2:
                    System.out.println("LISTAR CLIENTES");
                   banco.obtenerClientes();
                    break;
                    case 3:
                        System.out.println("ELIMINAR CLIENTE");
                        System.out.println("Ingrese el id del cliente a eliminar");
                        String idcliente= scanner.nextLine();
                        banco.eliminarCliente(idcliente);
                        break;
                        case 4:

                            System.out.println("AUTORIZAR SOLICITUD DE TARJETA DE CREDITO");
                            break;
                            case 5:
                                System.out.println("CONSULTAR SALDO DE CLIENTE");
                                banco.consultarYActualizarEstadoCuentas(banco, scanner);
                                break;
                                case 6:
                                    System.out.println("ACTIVAR O DESACTIVAR TARJETAS");
                                    banco.desactivarCuentaCliente(banco, scanner);
                                    break;
                                    case 7:
                                        return;
            }

        }
    }

    public void cargarEmpleados(Banco banco) {
        try {
            banco.cargarEmpleados();
            // System.out.println("Empleados cargados exitosamente.");
        } catch (IOException e) {
            // System.out.println("Error al cargar los empleados: " + e.getMessage());
        }
    }
}
