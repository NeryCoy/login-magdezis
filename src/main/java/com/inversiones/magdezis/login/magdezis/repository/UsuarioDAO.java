package main.java.com.inversiones.magdezis.login.magdezis.repository;

import main.java.com.inversiones.magdezis.login.magdezis.config.DataBaseConnection;
import main.java.com.inversiones.magdezis.login.magdezis.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para registrar un nuevo usuario en la base de datos
    public boolean registrar(Usuario usuario, String rol) {
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena, rol_id) " +
                     "VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE nombre = ?))";
        
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, ""); // Apellido vacío o ajusta si tu modelo lo requiere
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, rol); // 'USER' o 'ADMIN'
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar un usuario por su correo electrónico (para el Login)
    public Usuario obtenerPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Usuario(
                    String.valueOf(rs.getInt("id_usuario")), // Asegúrate de que tu columna se llame así en la BD
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para consultar el rol del usuario a partir de su correo (para mostrar la alerta requerida)
    public String obtenerRolPorEmail(String email) {
        String sql = "SELECT r.nombre FROM roles r JOIN usuarios u ON u.rol_id = r.id WHERE u.correo = ?";
        
        try (Connection conn = DataBaseConnection.getConnectionDataBase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nombre"); // Retornará 'USER' o 'ADMIN'
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "USER"; // Rol por defecto en caso de fallo
    }
}