package menus.menuGeneral;

import banco.Banco;
import menus.menuEmpleados.MenuEmpleados;
import resourses.Rol;
import usuarios.Usuarios;
import usuarios.cliente.Cliente;
import usuarios.empleados.Empleados;
import usuarios.gerente.Gerente;
import menus.menuCliente.MenuCliente;
import menus.menuGerente.MenuGerente;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private MenuGerente menuGerente = new MenuGerente();
    private MenuCliente menuCliente = new MenuCliente();
    private MenuEmpleados menuEmpleados = new MenuEmpleados();
    private Banco banco = new Banco();
    public void login() {
        int intentosMaximos = 5;
        int intentosUsuario = 0;
        //
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

                Usuarios usuarioEnSesion = banco.validarInicioDeSesion(usuario, contrasenia);

                if (usuarioEnSesion != null) {
                    if (usuarioEnSesion.getRol() == Rol.CLIENTE) {
                        Empleados empleadoEnSesion = (Empleados) usuarioEnSesion;
                        menuEmpleados.mostrarMenuEmpleado(empleadoEnSesion);
                        intentosUsuario = 0;
                    } else {
                        Gerente gerenteEnSesion = (Gerente) usuarioEnSesion;
                        menuGerente.mostrarMenuGerente(gerenteEnSesion);
                        intentosUsuario = 0;
                    }
                }

                intentosUsuario = mostrarErrorInicioSesion(intentosUsuario);
            }
        }
        System.out.println("Intentos máximos permitidos alcanzados.");
    }

    private int mostrarErrorInicioSesion(int intentosUsuario) {
        System.out.println("Usuario o contraseña incorrectos, Intenta de nuevo");
        return intentosUsuario + 1;
    }

}
