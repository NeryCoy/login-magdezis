package main.java.com.inversiones.magdezis.login.magdezis.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//clase con patrón de diseño Singleton
public class DataBaseConnection {
    //atributos
    private static Connection connection;
    
    //constructor privado para evitar instancias
    private DataBaseConnection(){}
    
    
    //metodoa -  tiene que ser public para acceder a él
    public static Connection getConnectionDataBase() throws SQLException{
        
     if(connection == null || connection.isClosed()){
     //si la conexion es nula o esta cerrada la creamos:
     
        connection = DriverManager.getConnection(Credentials.URL_DB, Credentials.USER_DB, Credentials.PASS_DB);
     }
        return connection;
    }
    //para acceder a un atributo estático usamos el nombre de la clase.
}
