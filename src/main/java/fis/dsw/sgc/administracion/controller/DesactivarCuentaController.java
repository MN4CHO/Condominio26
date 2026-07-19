package fis.dsw.sgc.administracion.controller;

import fis.dsw.sgc.administracion.model.EstadoCuenta;
import fis.dsw.sgc.administracion.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DesactivarCuentaController {

    @FXML private VBox panelPrincipal;
    @FXML private TextField txtBuscarCorreo;
    @FXML private Button btnBuscar;
    @FXML private Label lblMensaje;

    @FXML private VBox panelInfoCuenta;
    @FXML private Label lblUsuarioEncontrado;
    @FXML private Label lblEstadoCuenta;
    @FXML private Button btnAccionEstado; // Modificado para servir como interruptor

    @FXML private VBox panelConfirmacion;
    @FXML private Label lblResumenConfirmacion;
    @FXML private Button btnConfirmarAccion;

    // Cuenta actualmente seleccionada
    private Usuario usuarioSeleccionado;

    @FXML
    void buscarCuenta(ActionEvent event) {
        String correo = valorSeguro(txtBuscarCorreo);
        ocultarInfoCuenta();

        if (correo.isEmpty()) {
            mostrarError("Ingrese un correo para buscar la cuenta.");
            return;
        }

        Usuario usuario = DatosDemoGRB.buscarPorCorreo(correo);

        if (usuario == null) {
            mostrarError("No se encontró ninguna cuenta con ese correo.");
            return;
        }

        this.usuarioSeleccionado = usuario;
        EstadoCuenta estado = usuario.getCuenta().getEstado();

        lblUsuarioEncontrado.setText(usuario.getNombre() + " " + usuario.getApellido()
                + " (" + usuario.getCorreo() + ")");

        actualizarBadgeEstado(estado);

        panelInfoCuenta.setVisible(true);
        panelInfoCuenta.setManaged(true);
        lblMensaje.setText("");

        // Configurar el botón según el estado actual
        if (estado == EstadoCuenta.DESACTIVADA) {
            btnAccionEstado.setText("Activar Cuenta");
            btnAccionEstado.getStyleClass().setAll("primary-button");
        } else {
            btnAccionEstado.setText("Desactivar Cuenta");
            btnAccionEstado.getStyleClass().setAll("danger-button");
        }
        btnAccionEstado.setDisable(false);
    }

    @FXML
    void solicitarCambioEstado(ActionEvent event) {
        if (usuarioSeleccionado == null) return;

        EstadoCuenta estado = usuarioSeleccionado.getCuenta().getEstado();

        // Cambiar el texto de confirmación dinámicamente
        if (estado == EstadoCuenta.ACTIVA) {
            lblResumenConfirmacion.setText(
                    "¿Está seguro de desactivar la cuenta de " + usuarioSeleccionado.getNombre() + " " + usuarioSeleccionado.getApellido() + "?\n"
                            + "El usuario perderá el acceso al sistema hasta que la cuenta sea reactivada.");
            btnConfirmarAccion.setText("Sí, desactivar cuenta");
            btnConfirmarAccion.getStyleClass().setAll("danger-button");
        } else {
            lblResumenConfirmacion.setText(
                    "¿Está seguro de reactivar la cuenta de " + usuarioSeleccionado.getNombre() + " " + usuarioSeleccionado.getApellido() + "?\n"
                            + "El usuario volverá a tener acceso al sistema inmediatamente.");
            btnConfirmarAccion.setText("Sí, activar cuenta");
            btnConfirmarAccion.getStyleClass().setAll("primary-button");
        }

        panelPrincipal.setVisible(false);
        panelPrincipal.setManaged(false);
        panelConfirmacion.setVisible(true);
        panelConfirmacion.setManaged(true);
    }

    @FXML
    void cancelarConfirmacion(ActionEvent event) {
        panelConfirmacion.setVisible(false);
        panelConfirmacion.setManaged(false);
        panelPrincipal.setVisible(true);
        panelPrincipal.setManaged(true);
        mostrarInfo("Operación cancelada.");
    }

    @FXML
    void confirmarCambioEstado(ActionEvent event) {
        if (usuarioSeleccionado != null) {
            EstadoCuenta estado = usuarioSeleccionado.getCuenta().getEstado();

            // Ejecutar la acción inversa al estado actual
            if (estado == EstadoCuenta.ACTIVA) {
                usuarioSeleccionado.getCuenta().desactivar();
                mostrarExito("Cuenta desactivada exitosamente.");
            } else {
                usuarioSeleccionado.getCuenta().activar();
                mostrarExito("Cuenta reactivada exitosamente.");
            }

            // Actualizar la interfaz (insignia y botón) con el nuevo estado
            EstadoCuenta nuevoEstado = usuarioSeleccionado.getCuenta().getEstado();
            actualizarBadgeEstado(nuevoEstado);

            if (nuevoEstado == EstadoCuenta.DESACTIVADA) {
                btnAccionEstado.setText("Activar Cuenta");
                btnAccionEstado.getStyleClass().setAll("primary-button");
            } else {
                btnAccionEstado.setText("Desactivar Cuenta");
                btnAccionEstado.getStyleClass().setAll("danger-button");
            }
        }

        panelConfirmacion.setVisible(false);
        panelConfirmacion.setManaged(false);
        panelPrincipal.setVisible(true);
        panelPrincipal.setManaged(true);
    }

    private void actualizarBadgeEstado(EstadoCuenta estado) {
        lblEstadoCuenta.setText(estado.name());
        lblEstadoCuenta.getStyleClass().setAll("badge-estado",
                estado == EstadoCuenta.ACTIVA ? "badge-activa" : "badge-desactivada");
    }

    private void ocultarInfoCuenta() {
        panelInfoCuenta.setVisible(false);
        panelInfoCuenta.setManaged(false);
        usuarioSeleccionado = null;
    }

    private String valorSeguro(TextField campo) {
        return campo.getText() == null ? "" : campo.getText().trim();
    }

    private void mostrarError(String texto) {
        lblMensaje.getStyleClass().setAll("message-label", "message-error");
        lblMensaje.setText(texto);
    }

    private void mostrarExito(String texto) {
        lblMensaje.getStyleClass().setAll("message-label", "message-success");
        lblMensaje.setText(texto);
    }

    private void mostrarInfo(String texto) {
        lblMensaje.getStyleClass().setAll("message-label", "message-info");
        lblMensaje.setText(texto);
    }
}