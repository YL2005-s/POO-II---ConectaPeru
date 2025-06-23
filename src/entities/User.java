package entities;

public abstract class User {
    private String dni;
    private String password;
    private String nombre;
    private Role rol;

    public User() {}

    public User(String dni, String password, String nombre, Role rol) {
        this.dni = dni;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
