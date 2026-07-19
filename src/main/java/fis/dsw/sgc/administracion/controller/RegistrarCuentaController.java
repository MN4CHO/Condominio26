package fis.dsw.sgc.administracion.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class RegistrarCuentaController {

    private static final String MSG_INICIAL =
            "Complete los datos del nuevo usuario y pulse Registrar cuenta.";

    // Correos de ejemplo ya registrados, hasta conectar con el servicio/DAO real
    private static final List<String> CORREOS_YA_REGISTRADOS = List.of(
            "admin@condominio.com",
            "presidente@condominio.com"
    );

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private PasswordField txtConfirmarContrasena;
    @FXML private Button btnRegistrar;
    @FXML private Button btnLimpiar;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        setMensaje(MSG_INICIAL, "message-info");
    }

    @FXML
    void registrar(ActionEvent event) {
        String nombre = valorSeguro(txtNombre);
        String apellido = valorSeguro(txtApellido);
        String correo = valorSeguro(txtCorreo);
        String contrasena = txtContrasena.getText() == null ? "" : txtContrasena.getText();
        String confirmacion = txtConfirmarContrasena.getText() == null ? "" : txtConfirmarContrasena.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            setMensaje("Todos los campos son obligatorios.", "message-error");
            return;
        }

        if (!correo.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            setMensaje("El correo ingresado no tiene un formato válido.", "message-error");
            return;
        }

        if (CORREOS_YA_REGISTRADOS.contains(correo.toLowerCase())) {
            setMensaje("Ya existe una cuenta registrada con ese correo.", "message-error");
            return;
        }

        if (!contrasena.equals(confirmacion)) {
            setMensaje("Las contraseñas no coinciden.", "message-error");
            return;
        }

        if (contrasena.length() < 6) {
            setMensaje("La contraseña debe tener al menos 6 caracteres.", "message-error");
            return;
        }

        // Datos de ejemplo hasta conectar con el servicio/DAO real
        setMensaje("Cuenta registrada correctamente para " + nombre + " " + apellido + ".", "message-success");
        limpiarCampos();
    }

    @FXML
    void limpiar(ActionEvent event) {
        limpiarCampos();
        setMensaje(MSG_INICIAL, "message-info");
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtContrasena.clear();
        txtConfirmarContrasena.clear();
    }

    private String valorSeguro(TextField campo) {
        return campo.getText() == null ? "" : campo.getText().trim();
    }

    private void setMensaje(String texto, String estilo) {
        lblMensaje.getStyleClass().removeAll("message-info", "message-success", "message-error");
        if (!lblMensaje.getStyleClass().contains("message-label")) {
            lblMensaje.getStyleClass().add("message-label");
        }
        lblMensaje.getStyleClass().add(estilo);
        lblMensaje.setText(texto);
    }
}