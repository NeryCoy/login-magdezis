package main.java.com.inversiones.magdezis.login.magdezis.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.java.com.inversiones.magdezis.login.magdezis.service.AuthService;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private TextField txtContrasena;
    private final AuthService authService = new AuthService();

    @FXML
    public void handleLogin() {
        String email = txtEmail.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        if (email.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor, complete todos los campos.");
            return;
        }

        AuthService.ResultadoLogin resultado = authService.autenticar(email, contrasena);

        if (resultado.isExitoso()) {
            // Requisito: Mostrar una alerta en pantalla indicando el rol del usuario que inició sesión
            mostrarAlerta(
                Alert.AlertType.INFORMATION, 
                "Inicio de Sesión Exitoso - Inversiones Magdezis", 
                "¡Bienvenido, " + resultado.getUsuario().getNombre() + "!\n" +
                "Has iniciado sesión con el rol: " + resultado.getRol()
            );

            // Aquí puedes redirigir a la vista principal (Dashboard) según el rol si lo deseas
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Acceso", "Correo o contraseña incorrectos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}