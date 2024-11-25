package menus.menuGeneral;

import Banco.Banco;
import menus.menuEmpleados.MenuEmpleados;
import resourses.Rol;
import Usuarios.Usuarios;
import Usuarios.cliente.Cliente;
import Usuarios.empleados.Empleados;
import Usuarios.gerente.Gerente;
import menus.menuCliente.MenuCliente;
import menus.menuGerente.MenuGerente;

import java.io.IOException;
import java.util.Scanner;

public class menu {
    private Scanner scanner = new Scanner(System.in);
    private MenuGerente menuGerente = new MenuGerente();
    private MenuCliente menuCliente = new MenuCliente();
    private MenuEmpleados menuEmpleados = new MenuEmpleados();
    private Banco banco = new Banco();

    public void login() {
        try {
            Banco.cargarUsuarios();
            //Cargar usuarios desde archivo
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
        }
        int intentosMaximos = 5;
        int intentosUsuario = 0;

        while (intentosUsuario < intentosMaximos) {
            scanner.nextLine();

            System.out.println("\n¡BIENVENIDO!\n");
        System.out.println("¿Ya cuentas con tu cuenta de banco? Si/No");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("si")) {
            System.out.println("Inicia sesión para continuar");
            System.out.println("Ingresa tu usuario: ");
            String usuario = scanner.nextLine();
            System.out.println("Ingresa tu contraseña: ");
            String contrasenia = scanner.nextLine();
            try {
                Usuarios usuarioEnSesion = banco.validarInicioDeSesion(usuario, contrasenia);
                if (usuarioEnSesion.getRol() == Rol.CLIENTE) {
                    Cliente clienteEnSesion = (Cliente) usuarioEnSesion;
                    menuCliente.mostrarMenuCliente(clienteEnSesion);
                } else if (usuarioEnSesion.getRol() == Rol.GERENTE) {
                    Gerente gerenteEnSesion = (Gerente) usuarioEnSesion;
                    menuGerente.mostrarMenuGerente(gerenteEnSesion);
                } else if (usuarioEnSesion.getRol() == Rol.EMPLEADO) {
                    Empleados empleadoEnSesion = (Empleados) usuarioEnSesion;
                    menuEmpleados.mostrarMenuEmpleado(empleadoEnSesion);
                }
                return;

        } catch(Banco.Banco.InicioSesionException e){
            intentosUsuario = mostrarErrorInicioSesion(intentosUsuario, e.getMessage());
        }
    }

        }

        System.out.println("Intentos máximos permitidos alcanzados.");

}
    public int mostrarErrorInicioSesion(int intentosUsuario, String mensaje) {
        System.out.println(mensaje + ", Intenta de nuevo");
        return intentosUsuario + 1;
    }

    public void guardarUsuarios() {
            try {
                Banco.guardarUsuarios();

                // Guardar usuarios en archivo
                System.out.println("Usuarios guardados exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar los usuarios: " + e.getMessage());
            }
        }
    }



