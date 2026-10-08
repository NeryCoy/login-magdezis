package main.java.com.inversiones.magdezis.login.magdezis.model;

public class Rol {
    //atributos
    private String id_rol;
    private String nombre;

    //constructor
    public Rol(String id_rol, String nombre) {
        this.id_rol = id_rol;
        this.nombre = nombre;
    }
    
    //metodos
    //getters
    public String getId_rol() {
        return id_rol;
    }

    public String getNombre() {
        return nombre;
    }
    
    //setters
    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
