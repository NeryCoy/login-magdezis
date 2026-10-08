package main.java.com.inversiones.magdezis.login.magdezis.service;

import main.java.com.inversiones.magdezis.login.magdezis.repository.UsuarioDAO;
import main.java.com.inversiones.magdezis.login.magdezis.model.Usuario;
import main.java.com.inversiones.magdezis.login.magdezis.security.jbcrypt.BCrypt;

public class AuthService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Método para registrar un nuevo usuario con contraseña hasheada
    public boolean registrarUsuario(String nombre, String email, String contrasenaPlana, String rol) {
        // Encriptar la contraseña con BCrypt
        String hashedPassword = BCrypt.hashpw(contrasenaPlana, BCrypt.gensalt());
        
        // Creamos el usuario usando tu constructor (id_usuario va en null o vacío porque es autoincrementable)
        Usuario usuario = new Usuario(null, nombre, email, hashedPassword);
        
        return usuarioDAO.registrar(usuario, rol);
    }

    // Método para autenticar al usuario en el Login
    public ResultadoLogin autenticar(String email, String contrasenaPlana) {
        Usuario usuario = usuarioDAO.obtenerPorEmail(email);
        
        if (usuario != null) {
            // Verificar si la contraseña plana coincide con el hash almacenado en la BD
            if (BCrypt.checkpw(contrasenaPlana, usuario.getContrasena())) {
                // Obtenemos el rol correspondiente desde la base de datos
                String rol = usuarioDAO.obtenerRolPorEmail(email);
                return new ResultadoLogin(true, rol, usuario);
            }
        }
        return new ResultadoLogin(false, null, null);
    }

    // Clase auxiliar interna para transportar el resultado del inicio de sesión
    public static class ResultadoLogin {
        private final boolean exitoso;
        private final String rol;
        private final Usuario usuario;

        public ResultadoLogin(boolean exitoso, String rol, Usuario usuario) {
            this.exitoso = exitoso;
            this.rol = rol;
            this.usuario = usuario;
        }

        public boolean isExitoso() {
            return exitoso;
        }

        public String getRol() {
            return rol;
        }

        public Usuario getUsuario() {
            return usuario;
        }
    }
}