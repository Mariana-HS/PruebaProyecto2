package Usuarios;

public class Usuarios {
    public String nombre;
    public String apellido;
    public String curp;
    public String RFC;
    public String direccion;
//Constructor
    public Usuarios(String nombre, String apellido, String curp, String RFC, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curp = curp;
        this.RFC = RFC;
        this.direccion = direccion;
    }
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCurp() {
        return curp;
    }

    public String getRFC() {
        return RFC;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
