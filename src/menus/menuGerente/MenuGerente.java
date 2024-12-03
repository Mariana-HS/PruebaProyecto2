package menus.menuGerente;

import banco.Banco;
import menus.menuCliente.MenuCliente;
import tarjetas.TarjetaCredito;
import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;
import usuarios.gerente.Gerente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuGerente {

    public void mostrarMenuGerente(Gerente gerenteEnSesion, Banco banco) throws IOException {
        Scanner scanner = new Scanner(System.in);

        

        int opcion = 0;
        while (opcion != 7) {
            System.out.println("\n*BIENVENIDO*");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Modificar información clientes");
            System.out.println("3. Consultar y actualizar el estado de las cuentas");  //What is this????
            System.out.println("4. Desactivar cuentas de clientes");
            System.out.println("5. Contratar o despedir empleados ");
            System.out.println("6. Modificar información de los empleados");
            System.out.println("7. Salir");

            System.out.print("\nSeleccione una opción:\n");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Registrando cliente:");
                    scanner.nextLine();

                    System.out.println("Ingresa su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingresa sus apellidos: ");
                    String apellidos = scanner.nextLine();

                    String id = banco.generarIdCliente(nombre, apellidos);

                    System.out.println("Ingresa su CURP: ");
                    String curpCliente = scanner.nextLine();

                    while(!banco.validacionCurp(curpCliente)){
                        System.out.println("Ingresa una CURP válida: ");
                        curpCliente = scanner.nextLine();
                        banco.validacionCurp(curpCliente);
                    }
                    scanner.nextLine();

                    System.out.println("Ingresa su dirección:");
                    String direccionCliente = scanner.nextLine();

                    // Generar RFC automáticamente
                    String rfcCliente = banco.generarRFCDesdeCURP(curpCliente);

                    System.out.println("Ingrese el usuario: ");
                    String usuario = scanner.nextLine();

                    System.out.println("Ingresa su contrasenia: ");
                    String contrasenia = scanner.nextLine();

                    LocalDate fechaRegistro = LocalDate.now();




                    Cliente cliente = new Cliente(id, nombre, apellidos, curpCliente , rfcCliente , direccionCliente , fechaRegistro , contrasenia, usuario);
                    banco.registrarCliente(cliente);
                    TarjetaDebito tarjetaDebito = banco.crearTarjetaDebito(id);
                    TarjetaCredito tarjetaCredito = banco.crearTarjetaCredito(id);
                    banco.registrarTarjetaDebito(tarjetaDebito);
                    banco.registrarTarjetaCredito(tarjetaCredito);
                    System.out.println("Su numero de tarjeta es: " + tarjetaDebito.getNumeroTarjeta());
                    System.out.println("El CVV de su tarjeta es: " + tarjetaDebito.getCvv());

                    System.out.println("Registro del cliente exitoso!");

                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("MODIFICAR CLIENTES");
                    System.out.println("Ingrese el id del cliente a modificar:");
                    String idCliente = scanner.nextLine();
                    banco.modificarCliente(idCliente);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Actualizar estado de cuenta");
                    banco.consultarYActualizarEstadoCuentas(banco, scanner);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Desactivar la cuenta");
                    System.out.println("Ingresa el id del cliente a desactivar:");
                    String idClienteD = scanner.nextLine();
                    banco.desactivarCuentaCliente(idClienteD);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Gestionar empleados");
                    banco.gestionarEmpleados(banco, scanner);
                    break;
                case 6:
                    scanner.nextLine();
                    System.out.println("Modificar empleados");
                    banco.modificarEmpleado(banco, scanner);
                    break;
                case 7:
                    System.out.println("Saliendo del menu...");
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, elige nuevamente.");
            }
        }
    }


}
