package usuarios;

import resourses.Rol;

public class Usuarios {
    public String idUsuario;
    public String nombre;
    public String apellido;
    public String curp;
    public String RFC;
    public String direccion;
    public Rol rol;
    public String contrasenia;
    public String usuario;

    public Usuarios(String idUsuario, String nombre, String apellido, String curp, String RFC, String direccion, Rol rol, String contrasenia, String usuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curp = curp;
        this.RFC = RFC;
        this.direccion = direccion;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.usuario= usuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

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

    public Rol getRol() {
        return rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setUsuario(String usuario) {
        usuario = usuario;
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

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String toString(){
        return "{" +
                "\"idUsuario\":\"" + this.idUsuario + "\"," +
                "\"nombre\":\"" + this.nombre + "\"," +
                "\"apellido\":\"" + this.apellido + "\"," +
                "\"curp\":\"" + this.curp + "\"," +
                "\"RFC\":\"" + this.RFC + "\"," +
                "\"direccion\":\"" + this.direccion + "\"," +
                "\"rol\":\"" + this.rol + "\"," +
                "\"usuario\":\"" + this.usuario + "\"," +
                "\"contrasenia\":\"" + this.contrasenia + "\"" +
                "}";
    }
}

