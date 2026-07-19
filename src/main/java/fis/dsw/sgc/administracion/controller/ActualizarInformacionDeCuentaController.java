package fis.dsw.sgc.administracion.controller;

import fis.dsw.sgc.administracion.model.EstadoCuenta;
import fis.dsw.sgc.administracion.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ActualizarInformacionDeCuentaController {

    @FXML private TextField txtBuscarCorreo;
    @FXML private Button btnBuscar;
    @FXML private Label lblMensajeBusqueda;

    @FXML private VBox panelEdicion;
    @FXML private Label lblEstadoCuenta;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCorreo;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Label lblMensajeEdicion;

    private Usuario usuarioEnEdicion;
    private String correoOriginal;

    @FXML
    void buscarCuenta(ActionEvent event) {
        String correo = valorSeguro(txtBuscarCorreo);

        if (correo.isEmpty()) {
            mostrarErrorBusqueda("Ingrese un correo para buscar la cuenta.");
            ocultarPanelEdicion();
            return;
        }

        Usuario usuario = DatosDemoGRB.buscarPorCorreo(correo);

        if (usuario == null) {
            mostrarErrorBusqueda("No se encontró ninguna cuenta con ese correo.");
            ocultarPanelEdicion();
            return;
        }

        cargarEnPanelEdicion(usuario);
        lblMensajeBusqueda.setText("");
    }

    private void cargarEnPanelEdicion(Usuario usuario) {
        this.usuarioEnEdicion = usuario;
        this.correoOriginal = usuario.getCorreo();

        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        txtCorreo.setText(usuario.getCorreo());

        EstadoCuenta estado = usuario.getCuenta() != null ? usuario.getCuenta().getEstado() : EstadoCuenta.ACTIVA;
        lblEstadoCuenta.setText(estado.name());
        lblEstadoCuenta.getStyleClass().setAll("badge-estado",
                estado == EstadoCuenta.ACTIVA ? "badge-activa" : "badge-desactivada");

        panelEdicion.setVisible(true);
        panelEdicion.setManaged(true);
        lblMensajeEdicion.setText("");
    }

    @FXML
    void guardar(ActionEvent event) {
        if (usuarioEnEdicion == null) return;

        String nombre = valorSeguro(txtNombre);
        String apellido = valorSeguro(txtApellido);
        String correo = valorSeguro(txtCorreo);

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            mostrarErrorEdicion("Existen campos obligatorios vacíos.");
            return;
        }

        if (!correo.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            mostrarErrorEdicion("Los datos ingresados no son válidos.");
            return;
        }

        boolean correoCambio = !correo.equalsIgnoreCase(correoOriginal);
        if (correoCambio && DatosDemoGRB.buscarPorCorreo(correo) != null) {
            mostrarErrorEdicion("El correo electrónico ingresado ya está registrado por otro usuario.");
            return;
        }

        // Datos de ejemplo hasta conectar con el servicio/DAO real
        usuarioEnEdicion.setNombre(nombre);
        usuarioEnEdicion.setApellido(apellido);
        usuarioEnEdicion.setCorreo(correo);
        correoOriginal = correo;

        mostrarExitoEdicion("Información de cuenta actualizada exitosamente.");
    }

    @FXML
    void cancelar(ActionEvent event) {
        ocultarPanelEdicion();
        txtBuscarCorreo.clear();
        lblMensajeBusqueda.setText("");
    }

    private void ocultarPanelEdicion() {
        panelEdicion.setVisible(false);
        panelEdicion.setManaged(false);
        usuarioEnEdicion = null;
    }

    private String valorSeguro(TextField campo) {
        return campo.getText() == null ? "" : campo.getText().trim();
    }

    private void mostrarErrorBusqueda(String texto) {
        lblMensajeBusqueda.getStyleClass().setAll("message-label", "message-error");
        lblMensajeBusqueda.setText(texto);
    }

    private void mostrarErrorEdicion(String texto) {
        lblMensajeEdicion.getStyleClass().setAll("message-label", "message-error");
        lblMensajeEdicion.setText(texto);
    }

    private void mostrarExitoEdicion(String texto) {
        lblMensajeEdicion.getStyleClass().setAll("message-label", "message-success");
        lblMensajeEdicion.setText(texto);
    }
}