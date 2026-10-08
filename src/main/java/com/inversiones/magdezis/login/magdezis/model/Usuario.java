package main.java.com.inversiones.magdezis.login.magdezis.model;

public class Usuario {
    //atributos
    private String id_usuario;
    private String nombre;
    private String email;
    private String contrasena;
    
    //constructor
    public Usuario(String id_usuario, String nombre, String email, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
    
    
    //metodos
    //getters
    public String getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    
    //setters
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}
