package banco;

import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;
import usuarios.Usuarios;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Banco {
    Random random = new Random();

    //Generar id cliente
    LocalDateTime fecha = LocalDateTime.now();
    private Set<String> idsGenerados = new HashSet<>();
    public String generarIdCliente(String nombreCliente, String apellidoCliente) {
        char letraUno = nombreCliente.charAt(0);
        char letraDos = apellidoCliente.charAt(apellidoCliente.length() - 1);
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 3000);
        int diaActual = fecha.getDayOfMonth();

        String idCliente = String.format("C-%c%c-%d%d", letraUno, letraDos, numeroAleatorio, diaActual);

        // Validar si el ID ya fue generado
        while (idsGenerados.contains(idCliente)) {
            numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 3000);  // Generamos un nuevo número aleatorio
            idCliente = String.format("C-%c%c-%d%d", letraUno, letraDos, numeroAleatorio, diaActual);
        }

        idsGenerados.add(idCliente);  // Guardamos el nuevo ID
        return idCliente;
    }

    //Validación de la curp
    public boolean validacionCurp(String curp) {
        if (curp.length() != 18) {
            System.out.println("La CURP debe tener 18 caracteres.");
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (!Character.isUpperCase(curp.charAt(i))) {
                System.out.println("Los primeros 4 caracteres deben ser letras mayúsculas.");
                return false;
            }
        }
        for (int i = 4; i < 10; i++) {
            if (!Character.isDigit(curp.charAt(i))) {
                System.out.println("La fecha de nacimiento debe contener solo dígitos.");
                return false;
            }
        }
        char sexo = curp.charAt(10);
        if (sexo != 'H' && sexo != 'M') {
            System.out.println("El carácter de sexo debe ser 'H' para hombre o 'M' para mujer.");
            return false;
        }
        for (int i = 11; i < 13; i++) {
            if (!Character.isUpperCase(curp.charAt(i))) {
                System.out.println("El código de estado debe contener dos letras mayúsculas.");
                return false;
            }
        }
        for (int i = 13; i < 16; i++) {
            char c = curp.charAt(i);
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {
                System.out.println("Los caracteres del 13 al 15 deben ser letras mayúsculas o dígitos.");
                return false;
            }
        }
        if (!Character.isDigit(curp.charAt(17))) {
            System.out.println("El último carácter debe ser un dígito.");
            return false;
        }
        System.out.println("La CURP es válida.");
        return true;
    }

    //Validación del RFC

    public String generarRFCDesdeCURP(String curp) {
        if (!validacionCurp(curp)) {
            return null; // Si la CURP no es válida, no generamos el RFC
        }
        String apellidoPaterno = curp.substring(0, 2); // Primeras 2 letras del apellido paterno
        String apellidoMaterno = curp.substring(2, 3); // Primera letra del apellido materno
        String primerNombre = curp.substring(3, 4); // Primera letra del primer nombre

        // Fecha de nacimiento (año, mes, día)
        String fechaNacimiento = curp.substring(4, 10);

        String sexo = curp.substring(10, 11); // Letra que indica el sexo

        String estado = curp.substring(11, 13);

        String rfc = apellidoPaterno + apellidoMaterno + primerNombre + fechaNacimiento + sexo + estado;

        return rfc;
    }

    // Métodos Relacionados con Tarjeta de Debito
    public String generarNumeroTarjeta(){
        int primeraMitad = random.nextInt(1,99999999);
        int segundaMitad = random.nextInt(1,99999999);
        String numeroTarjeta = (Integer.toString(primeraMitad) + Integer.toString(segundaMitad));
        return numeroTarjeta;
    }

    public int cvv(){
        int cvv = random.nextInt(1,999);
        return cvv;
    }

    public LocalDate fechaRegistro(){
        LocalDate fechaActual = LocalDate.now();
        int year = fechaActual.getYear();
        int month = fechaActual.getMonthValue();
        int day = fechaActual.getDayOfMonth();
        LocalDate fecha = LocalDate.of(year, month, day);
        return fecha;
    }

    public LocalDate fechaVencimiento(){
        fechaRegistro();
        int year = fechaRegistro().getYear() + 5;
        LocalDate fechaVencimiento = fechaRegistro().plusYears(year);
        return fechaVencimiento;
    }

    public boolean validarNumeroDeTarjeta(TarjetaDebito tarjeta){
        for(tarjeta :)
    }

    //Registrar Cliente
    public static List<Cliente> cliente = new ArrayList<>();

    public static List<Cliente> leer() throws FileNotFoundException, IOException,ClassNotFoundException {
        File archivo = new File("datos.dat");
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream s = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Cliente>) s.readObject();
        }
    }
    public static void registrarCliente(String id,String nombre, String apellido, String curp, String RFC, String direccion, LocalDate fechaRegistro,double saldoInicialDebito) {
        for(Cliente p : cliente) {
            if(p.getNombre().equals(nombre) && p.getCurp() == curp) {
                System.out.println("Usuario existente. No se puede registrar.");
                return;
            }
        }
        cliente.add(new Cliente(id,nombre,apellido,curp, RFC,direccion,fechaRegistro,saldoInicialDebito));
        System.out.println("Persona agregada exitosamente.");
    }

    // Lista y métodos para manejar usuarios
    private static List<Usuarios> usuariosRegistrados = new ArrayList<>();

    // Metodo para registrar un usuario
    public static void registrarUsuario(Usuarios usuario) { usuariosRegistrados.add(usuario); }

    // Metodo para validar el inicio de sesion
    public Usuarios validarInicioDeSesion(String usuario, String contrasenia) throws InicioSesionException {
        for (Usuarios u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
                return u; } } throw new InicioSesionException("Usuario o contraseña incorrectos."); }

    // Metodo para guardar usuarios en archivo
    public static void guardarUsuarios() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(usuariosRegistrados); } }

    //Metodo para cargar usuarios desde archivo
    public static void cargarUsuarios() throws IOException, ClassNotFoundException {
        File archivo = new File("usuarios.dat"); if (!archivo.exists()) {
            usuariosRegistrados = new ArrayList<>(); return; }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            usuariosRegistrados = (List<Usuarios>) ois.readObject(); } }


    public class InicioSesionException extends Exception { public InicioSesionException(String mensaje) { super(mensaje); } }
}

