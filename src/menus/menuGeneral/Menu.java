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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private MenuGerente menuGerente = new MenuGerente();
    private MenuCliente menuCliente = new MenuCliente();
    private MenuEmpleados menuEmpleados = new MenuEmpleados();
    private Banco banco = new Banco();

    public void login() {
        this.cargarGerentes(banco);
        this.cargarEmpleados(banco);
        //this.cargarClientes(banco);
        this.generarGerente(banco);

        /*try {
            /Banco.cargarUsuarios();
            Cargar usuarios desde archivo
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
        }*/
        int intentosMaximos = 5;
        int intentosUsuario = 0;

        while (intentosUsuario < intentosMaximos) {

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
                    menuCliente.mostrarMenuCliente(clienteEnSesion, banco);
                } else if (usuarioEnSesion.getRol() == Rol.GERENTE) {
                    Gerente gerenteEnSesion = (Gerente) usuarioEnSesion;
                    menuGerente.mostrarMenuGerente(gerenteEnSesion, banco);
                } else if (usuarioEnSesion.getRol() == Rol.EMPLEADO) {
                    Empleados empleadoEnSesion = (Empleados) usuarioEnSesion;
                    menuEmpleados.mostrarMenuEmpleado(empleadoEnSesion, banco);
                }
                return;

        } catch(Banco.InicioSesionException e){
            intentosUsuario = mostrarErrorInicioSesion(intentosUsuario, e.getMessage());
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        }

        System.out.println("Intentos máximos permitidos alcanzados.");

}
    public int mostrarErrorInicioSesion(int intentosUsuario, String mensaje) {
        System.out.println(mensaje + ", Intenta de nuevo");
        return intentosUsuario + 1;
    }

    public void cargarEmpleados(Banco banco) {
        try {
            banco.cargarEmpleados();
            // System.out.println("Empleados cargados exitosamente.");
        } catch (IOException e) {
            // System.out.println("Error al cargar los empleados: " + e.getMessage());
        }
    }

    public void cargarGerentes(Banco banco){
        try {
            banco.cargarGerentes();
        } catch (IOException e) {

        }
    }

    /*
    public void cargarClientes(Banco banco){
        try {
            banco.cargarClientes();
        } catch (IOException e) {

        }
    }
    */

    public void generarGerente(Banco banco){
        try {
            banco.generarGerente();
        } catch (IOException e) {

        }
    }
    }



