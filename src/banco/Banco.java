package banco;

import tarjetas.TarjetaCredito;
import tarjetas.TarjetaDebito;
import usuarios.cliente.Cliente;
import usuarios.Usuarios;
import usuarios.empleados.Empleados;
import usuarios.gerente.Gerente;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import resourses.Utils;

public class Banco {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Usuarios> usuariosRegistrados = new ArrayList<Usuarios>();
    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleados> listaEmpleados = new ArrayList<>();
    public ArrayList<TarjetaDebito> listaTarjetasDebito = new ArrayList<>();
    public ArrayList<TarjetaCredito> listaTarjetasCredito = new ArrayList<>();

//----------------------------------------------------------------------------------------------------------------------

    /////VALIDAR INICIO DE SESIÓN/////////
    public Usuarios validarInicioDeSesion(String usuario, String contrasenia) throws InicioSesionException {
        for (Usuarios u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
                if (u instanceof Gerente) {
                    System.out.println("¡Bienvenido, Gerente!");
                    return u;
                } else if (u instanceof Empleados) {
                    System.out.println("¡Bienvenido, Empleado!");
                    return u;
                } else if (u instanceof Cliente) {
                    System.out.println("Bienvenido, Cliente!");
                    return u;
                }
            }
        }
        throw new InicioSesionException("Usuario o contraseña incorrectos.");
    }

    public class InicioSesionException extends Exception {
        public InicioSesionException(String mensaje) {
            super(mensaje);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    ////GENERAR ID CLIENTE/////
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

    //----------------------------------------------------------------------------------------------------------------------

    //////GENERAR ID GERENTE////////
    public String generarIdGerente(String nombre, String apellido) {

        char letraUno = nombre.charAt(0);
        char letraDos = apellido.charAt(apellido.length() - 1);

        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 3000);

        int diaActual = fecha.getDayOfMonth();

        String idGerente = String.format("GER-%c%c-%d%d", letraUno, letraDos, numeroAleatorio, diaActual);

        while (idsGenerados.contains(idGerente)) {
            numeroAleatorio = ThreadLocalRandom.current().nextInt(1, 3000);
            idGerente = String.format("GER-%c%c-%d%d", letraUno, letraDos, numeroAleatorio, diaActual);
        }

        idsGenerados.add(idGerente);
        return idGerente;
    }

    //------------------------------------------------------------------------------------------------------

    /////VALIDACIÓN DE LA CURP///////
    public HashSet<String> curpSet = new HashSet<>();
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

        // Validación para evitar duplicados
        if (curpSet.contains(curp)) {
            System.out.println("Esta CURP ya está registrada, Porfavor ingrese una curp valida.");
            return false;
        }

        System.out.println("La CURP es válida.");
        return true;
    }

    //------------------------------------------------------------------------------------------------------

    //////CREACION DEL RFC///////
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

    //------------------------------------------------------------------------------------------------------

    ///////// METODOS CREDITO /////////////////////
    public TarjetaCredito crearTarjetaCredito(String idCliente) {
        String numeroTarjeta = generarNumeroTarjeta();
        int cvv = cvv();
        LocalDate fechaRegistro = fechaRegistro();
        LocalDate fechaVencimiento = fechaVencimiento();
        while (!validarExistenciaNumeroTarjeta(numeroTarjeta) || !validarExistenciaCVV(cvv)) {
            numeroTarjeta = generarNumeroTarjeta();
            cvv = cvv();
        }
        TarjetaCredito tarjetaCredito = new TarjetaCredito(0, numeroTarjeta, fechaRegistro, cvv, fechaVencimiento, idCliente);
        return tarjetaCredito;
    }

    public void registrarTarjetaCredito(TarjetaCredito tarjetaCredito) {
        listaTarjetasCredito.add(tarjetaCredito);

        try {
            guardarTarjetasCredito();
        } catch (IOException e) {
            System.out.println("Error al guardar las tarjetas: " + e.getMessage());
        }
    }

    public void guardarTarjetasCredito() throws IOException {
        FileWriter file = new FileWriter("tarjetasCredito.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (TarjetaCredito tarjetaCredito : listaTarjetasCredito) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(tarjetaCredito.toString());
            index++;
        }

        writer.close();
    }

    public void cargarTarjetasCredito() throws IOException {
        FileReader file = new FileReader("tarjetasCredito.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null) {
            Map<String, Object> data = Utils.convertStringToJsonObject(line);

            TarjetaCredito tarjetaCredito = new TarjetaCredito(
                    Double.parseDouble(String.valueOf(data.get("saldo"))),
                    String.valueOf((data.get("numeroTarjeta"))),
                    LocalDate.parse(String.valueOf(data.get("fechaRegistro"))),
                    Integer.parseInt(String.valueOf(data.get("cvv"))),
                    LocalDate.parse(String.valueOf(data.get("fechaVencimiento"))),
                    String.valueOf(data.get("idCliente"))

            );

            this.listaTarjetasCredito.add(tarjetaCredito);

        }

        reader.close();
    }

    public void obtenerUltimoMovimientoCredito(TarjetaCredito tarjetaCredito) throws IOException {
        if (!tarjetaCredito.getMovimientos().isEmpty()) {
            String ultimoMovimiento = tarjetaCredito.getMovimientos().get(tarjetaCredito.getMovimientos().size() - 1);
            System.out.println("Último movimiento: " + ultimoMovimiento);
        } else {
            System.out.println("No hay movimientos disponibles.");
        }
    }

    public void mostrarMovimientosCredito(TarjetaCredito tarjetaCredito) {
        if (!tarjetaCredito.getMovimientos().isEmpty()) {
            for (String movimiento : tarjetaCredito.getMovimientos()) {
                System.out.println(movimiento);
            }
        } else {
            System.out.println("No hay movimientos disponibles.");
        }
    }

    public TarjetaCredito buscarTarjetaCredito(String idUsuario) {
        for (TarjetaCredito tarjeta : listaTarjetasCredito) {
            if (tarjeta.getIdCliente().equals(idUsuario)) {
                System.out.println("Tarjeta: " + tarjeta.getNumeroTarjeta());
                return  tarjeta;
            }
        }
        return null;
    }
    /////////---------------------------------------------------------------------------------------

    //////// METODOS DE TARJETA DE DEBITO/////////
    public String generarNumeroTarjeta() {
        int primeraMitad = ThreadLocalRandom.current().nextInt(1111, 9999);
        int segundaMitad = ThreadLocalRandom.current().nextInt(1111, 9999);

        String numeroTarjeta = (Integer.toString(primeraMitad) + Integer.toString(segundaMitad));
        return numeroTarjeta;
    }


    public int cvv() {
        int cvv = ThreadLocalRandom.current().nextInt(1, 999);
        return cvv;
    }

    public LocalDate fechaRegistro() {
        LocalDate fechaActual = LocalDate.now();
        int year = fechaActual.getYear();
        int month = fechaActual.getMonthValue();
        int day = fechaActual.getDayOfMonth();
        LocalDate fecha = LocalDate.of(year, month, day);
        return fecha;
    }

    public LocalDate fechaVencimiento() {
        fechaRegistro();
        LocalDate fechaVencimiento = fechaRegistro().plusYears(5);
        return fechaVencimiento;
    }

    public void agregarTarjetaDebito(TarjetaDebito tarjetaDebito) {
        listaTarjetasDebito.add(tarjetaDebito);
    }

    public boolean validarExistenciaNumeroTarjeta(String numeroTarjeta) {
        for (TarjetaDebito tarjetaDebito : listaTarjetasDebito) {
            if (tarjetaDebito.getNumeroTarjeta().equals(numeroTarjeta)) {
                return false;
            }
        }
        return true;
    }

    public boolean validarExistenciaCVV(int cvv) {
        for (TarjetaDebito tarjetaDebito : listaTarjetasDebito) {
            if (tarjetaDebito.getCvv() == cvv) {
                return false;
            }
        }
        return true;
    }

    public TarjetaDebito crearTarjetaDebito(String idCliente) {
        String numeroTarjeta = generarNumeroTarjeta();
        int cvv = cvv();
        LocalDate fechaRegistro = fechaRegistro();
        LocalDate fechaVencimiento = fechaVencimiento();
        while (!validarExistenciaNumeroTarjeta(numeroTarjeta) || !validarExistenciaCVV(cvv)) {
            numeroTarjeta = generarNumeroTarjeta();
            cvv = cvv();
        }
        TarjetaDebito tarjetaDebito = new TarjetaDebito(0, numeroTarjeta, fechaRegistro, cvv, fechaVencimiento, idCliente);
        return tarjetaDebito;
    }

    public void registrarTarjetaDebito(TarjetaDebito tarjetaDebito) {
        listaTarjetasDebito.add(tarjetaDebito);

        try {
            guardarTarjetasDebito();
        } catch (IOException e) {
            System.out.println("Error al guardar las tarjetas: " + e.getMessage());
        }
    }

    public void guardarTarjetasDebito() throws IOException {
        FileWriter file = new FileWriter("tarjetasDebito.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (TarjetaDebito tarjetaDebito : listaTarjetasDebito) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(tarjetaDebito.toString());
            index++;
        }

        writer.close();
    }

    public void cargarTarjetasDebito() throws IOException {
        FileReader file = new FileReader("tarjetasDebito.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null) {
            Map<String, Object> data = Utils.convertStringToJsonObject(line);

            TarjetaDebito tarjetaDebito = new TarjetaDebito(
                    Double.parseDouble(String.valueOf(data.get("saldo"))),
                    String.valueOf((data.get("numeroTarjeta"))),
                    LocalDate.parse(String.valueOf(data.get("fechaRegistro"))),
                    Integer.parseInt(String.valueOf(data.get("cvv"))),
                    LocalDate.parse(String.valueOf(data.get("fechaVencimiento"))),
                    String.valueOf(data.get("idCliente"))

            );

            this.listaTarjetasDebito.add(tarjetaDebito);

        }

        reader.close();
    }


///ULTIMO MOVIMIENTO DE LA TARJETA DE DEBITO////
    public void obtenerUltimoMovimiento(TarjetaDebito tarjetaDebito) {
        if (!tarjetaDebito.getMovimientos().isEmpty()) {
            String ultimoMovimiento = tarjetaDebito.getMovimientos().get(tarjetaDebito.getMovimientos().size() - 1);
            System.out.println("Último movimiento: " + ultimoMovimiento);
        } else {
            System.out.println("No hay movimientos disponibles.");
        }
    }

    public void mostrarMovimientos(TarjetaDebito tarjetaDebito) {
        if (!tarjetaDebito.getMovimientos().isEmpty()) {
            for (String movimiento : tarjetaDebito.getMovimientos()) {
                System.out.println(movimiento);
            }
        } else {
            System.out.println("No hay movimientos disponibles.");
        }
    }

    public TarjetaDebito buscarTarjetaDebito(String numeroTarjeta) {
        for (TarjetaDebito tarjeta : listaTarjetasDebito) {
            if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) {
                System.out.println("Tarjeta: " + tarjeta.getNumeroTarjeta());
                return tarjeta;
            }
        }
        return null;
    }

    public TarjetaDebito buscarTarjetaDebitoParaEliminar(String idCliente) {
        for (TarjetaDebito tarjeta : listaTarjetasDebito) {
            if (tarjeta.getIdCliente().equals(idCliente)) {
                return tarjeta;
            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------

    //////REGISTRAR USUARIOS////////
    public void registrarUsuario(Usuarios usuario) {
        usuariosRegistrados.add(usuario);
    }

    //------------------------------------------------------------------------------------------------------

    //////CRUD DE CLIENTE/////////
    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
        usuariosRegistrados.add(cliente);

        try {
            guardarClientes();
        } catch (IOException e) {
            System.out.println("Error al guardar los clientes: " + e.getMessage());
        }
    }

    public void guardarClientes() throws IOException {
        FileWriter file = new FileWriter("clientes.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (Cliente cliente : clientes) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(cliente.toString());
            index++;
        }

        writer.close();
    }


    public void cargarClientes() throws IOException {
        FileReader file = new FileReader("clientes.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null) {
            Map<String, Object> data = Utils.convertStringToJsonObject(line);

            Cliente cliente = new Cliente(
                    String.valueOf(data.get("idUsuario")),
                    String.valueOf(data.get("nombre")),
                    String.valueOf(data.get("apellido")),
                    String.valueOf(data.get("curp")),
                    String.valueOf(data.get("RFC")),
                    String.valueOf(data.get("direccion")),
                    LocalDate.parse(String.valueOf(data.get("fechaRegistro"))),
                    String.valueOf(data.get("contrasenia")),
                    String.valueOf(data.get("usuario"))
            );

            this.clientes.add(cliente);
            this.usuariosRegistrados.add(cliente);
        }

        reader.close();
    }

    public void modificarCliente(String idCliente) throws IOException {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        } else {
            System.out.println("Ingrese el nuevo nombre del cliente");
            String nombre = scanner.nextLine();
            cliente.setNombre(nombre);

            System.out.println("Ingrese el nuevo apellido del cliente");
            String apellido = scanner.nextLine();
            cliente.setApellido(apellido);

            System.out.println("Ingrese el nuevo usuario");
            String usuario = scanner.nextLine();
            cliente.setUsuario(usuario);

            System.out.println("Ingrese la nueva contrasenia");
            String contrasenia = scanner.nextLine();
            cliente.setContrasenia(contrasenia);

            guardarClientes();
            System.out.println("Se ha cambiado los datos correctamente");

        }
    }

    public Cliente buscarCliente(String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdUsuario().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.datos());
        }
    }

    //------------------------------------------------------------------------------------------------------

    /////METODOS DEL GERENTE (CRUD)//////
    public void guardarGerentes() throws IOException {
        FileWriter file = new FileWriter("gerentes.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (Usuarios usuariosRegistrado : usuariosRegistrados) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(usuariosRegistrado.toString());
            index++;
        }

        writer.close();
    }

    public void cargarGerentes() throws IOException {
        FileReader file = new FileReader("gerentes.txt");
        BufferedReader reader = new BufferedReader(file);
        String linea;

        while ((linea = reader.readLine()) != null) {
            Map<String, Object> data = Utils.convertStringToJsonObject(linea);

            Gerente gerente = new Gerente(
                    String.valueOf(data.get("idUsuario")),
                    String.valueOf(data.get("nombre")),
                    String.valueOf(data.get("apellido")),
                    String.valueOf(data.get("curp")),
                    String.valueOf(data.get("RFC")),
                    String.valueOf(data.get("direccion")),
                    Double.parseDouble(String.valueOf(data.get("salario"))),
                    String.valueOf(data.get("contrasenia")),
                    String.valueOf(data.get("usuario"))
            );

            this.usuariosRegistrados.add(gerente);
        }
        reader.close();
    }

    public void generarGerente() throws IOException {
        boolean seDebeGenerarGerente = true;

        try {
            FileReader file = new FileReader("gerentes.txt");
            BufferedReader reader = new BufferedReader(file);
            String linea;

            if (reader.readLine() != null) {
                seDebeGenerarGerente = false;
            }
        } catch (IOException e) {
        }

        if (seDebeGenerarGerente) {
            String cId = this.generarIdGerente("Victor", "Lopez");
            Gerente cGerente = new Gerente(cId, "Victor", "Lopez", "RACW050729MMCSHNA2", "ihjghfhgj", "ghfgfhjhj", 85789.900, "123", "vic123");
            this.registrarUsuario(cGerente);

            this.guardarGerentes();
        }
    }

    //------------------------------------------------------------------------------------------------------

    /////METODOS DE EMPLEADOS (CRUD)//////////
    public void guardarEmpleados() throws IOException {
        FileWriter file = new FileWriter("empleados.txt");
        BufferedWriter writer = new BufferedWriter(file);

        int index = 0;
        for (Empleados empleado : listaEmpleados) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(empleado.toString());
            index++;
        }

        writer.close();
    }

    public void cargarEmpleados() throws IOException {
        FileReader file = new FileReader("empleados.txt");
        BufferedReader reader = new BufferedReader(file);
        String line;

        while ((line = reader.readLine()) != null) {
            Map<String, Object> data = Utils.convertStringToJsonObject(line);

            Empleados empleado = new Empleados(
                    String.valueOf(data.get("idUsuario")),
                    String.valueOf(data.get("nombre")),
                    String.valueOf(data.get("apellido")),
                    String.valueOf(data.get("curp")),
                    String.valueOf(data.get("RFC")),
                    String.valueOf(data.get("direccion")),
                    Double.parseDouble(String.valueOf(data.get("salario"))),
                    String.valueOf(data.get("contrasenia")),
                    String.valueOf(data.get("usuario"))
            );

            this.listaEmpleados.add(empleado);
            this.usuariosRegistrados.add(empleado);
        }

        reader.close();
    }

    public void gestionarEmpleados(Banco banco, Scanner scanner) throws IOException {
        System.out.println("\n1. Contratar empleado");
        System.out.println("2. Despedir empleado");
        System.out.print("Seleccione una opción:\n");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                registrarEmpleado(banco, scanner);
                break;
            case 2:
                opcionDespedirEmpleado(banco, scanner);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void registrarEmpleado(Banco banco, Scanner scanner) throws IOException {
        System.out.println("\nContratando nuevo empleado...");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el CURP: ");
        String curp = scanner.nextLine();

        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        String id = banco.generarIdEmpleado(nombre, apellido);

        Empleados nuevoEmpleado = new Empleados(id, nombre, apellido, curp, generarRFCDesdeCURP(curp), direccion, salario, contrasena, usuario);
        banco.contratarEmpleado(nuevoEmpleado);

        System.out.println("¡Empleado contratado exitosamente!");
    }

    private void opcionDespedirEmpleado(Banco banco, Scanner scanner) throws IOException {
        System.out.print("\nIngrese el ID  del empleado a despedir: ");
        String id = scanner.nextLine();

        Empleados empleado = banco.buscarEmpleado(id);

        if (empleado == null) {
            System.out.println("Empleado no encontrado");
        } else {
            listaEmpleados.remove(empleado);
            guardarEmpleados();
            System.out.println("Empleado depedido :c");
        }

    }


    public void contratarEmpleado(Empleados empleado) {
        listaEmpleados.add(empleado);
        usuariosRegistrados.add(empleado);

        try {
            guardarEmpleados();
        } catch (IOException e) {
            System.out.println("Error al guardar los empleados: " + e.getMessage());
        }
    }

    public Empleados buscarEmpleado(String idEmpleado) {
        for (Empleados empleado : listaEmpleados) {
            if (empleado.getIdUsuario().equals(idEmpleado)) {
                return empleado;
            }
        }
        return null;
    }

    public String generarIdEmpleado(String nombre, String apellido) {
        return "EMP" + nombre.substring(0, 2).toUpperCase() + apellido.substring(0, 2).toUpperCase() + (listaEmpleados.size() + 1);
    }

    public void modificarEmpleado(Banco banco, Scanner scanner) throws IOException {
        System.out.print("\nIngrese el ID o nombre del empleado a modificar: ");
        String id = scanner.nextLine();

        Empleados empleado = banco.buscarEmpleado(id);

        if (empleado != null) {
            System.out.println("\nEmpleado encontrado: " + empleado.getNombre() + " " + empleado.getApellido());
            System.out.println("¿Qué información deseas modificar?");
            System.out.println("1. Modificar salario");
            System.out.println("2. Modificar dirección");
            System.out.println("3. Modificar usuario y contraseña");
            System.out.println("4. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            while (opcion !=4) {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nuevo salario: ");
                        double nuevoSalario = scanner.nextDouble();
                        empleado.setSalario(nuevoSalario);
                        guardarEmpleados();
                        System.out.println("Salario actualizado exitosamente.");

                        break;
                    case 2:
                        System.out.print("Ingrese la nueva dirección: ");
                        String nuevaDireccion = scanner.nextLine();
                        empleado.setDireccion(nuevaDireccion);
                        guardarEmpleados();
                        System.out.println("Dirección actualizada exitosamente.");
                        break;
                    case 3:
                        System.out.print("Ingrese el nuevo nombre de usuario: ");
                        String nuevoUsuario = scanner.nextLine();
                        System.out.print("Ingrese la nueva contraseña: ");
                        String nuevaContrasena = scanner.nextLine();
                        empleado.setUsuario(nuevoUsuario);
                        empleado.setContrasenia(nuevaContrasena);
                        guardarEmpleados();
                        System.out.println("Usuario y contraseña actualizados exitosamente.");
                        break;
                    case 4:
                        System.out.println("Volviendo al menú principal...");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    //------------------------------------------------------------------------------------------------------

    ///////METODOS DE CUENTAS/////////
    public void consultarYActualizarEstadoCuentas(Banco banco, Scanner scanner) throws IOException {
        scanner.nextLine();
        System.out.println("Ingrese el número de tarjeta de débito: ");
        String numeroTarjeta = scanner.nextLine();

        TarjetaDebito tarjetaDebito = buscarTarjetaDebito(numeroTarjeta);


        while (tarjetaDebito == null) {
            System.out.println("Tarjeta no encontrada");
            System.out.println("Ingrese el numero de tarjeta de debito");
            numeroTarjeta = scanner.nextLine();
            tarjetaDebito = buscarTarjetaDebito(numeroTarjeta);
        }
        tarjetaDebito.cargarMovimientos();

            System.out.println("Saldo actual: $" + tarjetaDebito.getSaldo());

            System.out.println("\n¿Qué acción desea realizar?");
            System.out.println("1. Realizar un depósito");
            System.out.println("2. Realizar un retiro");
            System.out.println("3. Regresar al menú");

            int accion = scanner.nextInt();
            scanner.nextLine();

            switch (accion) {
                case 1:
                    System.out.println("Ingrese el monto a depositar:");
                    double montoDeposito = scanner.nextDouble();
                    tarjetaDebito.depositar(montoDeposito);
                    tarjetaDebito.registrarMovimientoDeposito(montoDeposito);
                    System.out.println("Depósito exitoso. Saldo actual: $" + tarjetaDebito.getSaldo());
                    guardarTarjetasDebito();

                    return;
                case 2:
                    System.out.println("Ingrese el monto a retirar:");
                    double montoRetiro = scanner.nextDouble();
                    tarjetaDebito.retirar(montoRetiro);
                    tarjetaDebito.registrarMovimientoRetiro(montoRetiro);
                    System.out.println("Retiro realizado. Saldo actual: $" + tarjetaDebito.getSaldo());
                    guardarTarjetasDebito();

                    return;
                case 3:
                    System.out.println("Regresando al menú...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    public void consultarYActualizarEstadoCuentasCredito(Banco banco, Scanner scanner) throws IOException {
        scanner.nextLine();
        System.out.println("Ingrese el número de tarjeta de crédito: ");
        String numeroTarjeta = scanner.nextLine();

        TarjetaCredito tarjetaCredito = buscarTarjetaCredito(numeroTarjeta);


        while (tarjetaCredito == null) {
            System.out.println("Tarjeta no encontrada");
            System.out.println("Ingrese el numero de tarjeta de debito");
            numeroTarjeta = scanner.nextLine();
            tarjetaCredito = buscarTarjetaCredito(numeroTarjeta);
        }
        tarjetaCredito.cargarMovimientos();

        System.out.println("Saldo actual: $" + tarjetaCredito.getSaldo());

        System.out.println("\n¿Qué acción desea realizar?");
        System.out.println("1. Realizar un depósito");
        System.out.println("2. Realizar un retiro");
        System.out.println("3. Regresar al menú");

        int accion = scanner.nextInt();
        scanner.nextLine();

        switch (accion) {
            case 1:
                System.out.println("Ingrese el monto a depositar:");
                double montoDeposito = scanner.nextDouble();
                tarjetaCredito.depositar(montoDeposito);
                tarjetaCredito.registrarMovimientoDeposito(montoDeposito);
                System.out.println("Depósito exitoso. Saldo actual: $" + tarjetaCredito.getSaldo());
                guardarTarjetasCredito();

                return;
            case 2:
                System.out.println("Ingrese el monto a retirar:");
                double montoRetiro = scanner.nextDouble();
                tarjetaCredito.retirar(montoRetiro);
                tarjetaCredito.registrarMovimientoRetiro(montoRetiro);
                System.out.println("Retiro realizado. Saldo actual: $" + tarjetaCredito.getSaldo());
                guardarTarjetasCredito();

                return;
            case 3:
                System.out.println("Regresando al menú...");
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }
    public void desactivarCuentaCliente(String idCliente) throws IOException {
        Cliente cliente = buscarCliente(idCliente);
        TarjetaDebito tarjetaDebito = buscarTarjetaDebitoParaEliminar(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        } else {
            clientes.remove(cliente);
            listaTarjetasDebito.remove(tarjetaDebito);
            guardarClientes();
            guardarTarjetasDebito();
            System.out.println("Cuenta eliminada");
        }
    }
}












