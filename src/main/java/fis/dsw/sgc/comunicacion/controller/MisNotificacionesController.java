package fis.dsw.sgc.comunicacion.controller;

import fis.dsw.sgc.administracion.model.Usuario;
import fis.dsw.sgc.comunicacion.dao.ComunicacionDAOSQLite;
import fis.dsw.sgc.comunicacion.dto.NotificacionDTO;
import fis.dsw.sgc.comunicacion.exception.ComunicacionException;
import fis.dsw.sgc.comunicacion.service.ComunicacionServiceImpl;
import fis.dsw.sgc.comunicacion.service.IComunicacionService;
import fis.dsw.sgc.core.session.SesionUsuario;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MisNotificacionesController {

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblResumen;

    @FXML
    private Label lblMensaje;

    @FXML
    private ComboBox<String> cmbTipo;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableView<NotificacionDTO> tblNotificaciones;

    @FXML
    private TableColumn<NotificacionDTO, String> colFecha;

    @FXML
    private TableColumn<NotificacionDTO, String> colTipo;

    @FXML
    private TableColumn<NotificacionDTO, String> colAsunto;

    @FXML
    private TableColumn<NotificacionDTO, String> colEstado;

    @FXML
    private Button btnMarcarLeida;

    @FXML
    private Button btnEliminar;

    private final ObservableList<NotificacionDTO> datos =
            FXCollections.observableArrayList();

    /*
     * Este controlador crea sus propias dependencias.
     * No necesita inyección desde mainWindowController.
     */
    private final IComunicacionService service =
            new ComunicacionServiceImpl(
                    new ComunicacionDAOSQLite()
            );

    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        configurarTabla();
        configurarFiltros();
        cargarUsuarioActual();

        btnMarcarLeida.setDisable(true);
        btnEliminar.setDisable(true);

        if (usuarioActual != null) {
            cargarNotificaciones();
        }
    }

    private void configurarTabla() {
        colFecha.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().fecha()
                )
        );

        colTipo.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().tipo()
                )
        );

        colAsunto.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().asunto()
                )
        );

        colEstado.setCellValueFactory(
                dato -> new ReadOnlyStringWrapper(
                        dato.getValue().estado()
                )
        );

        tblNotificaciones.setItems(datos);

        tblNotificaciones.setPlaceholder(
                new Label("No tienes notificaciones.")
        );
    }

    private void configurarFiltros() {
        cmbTipo.setItems(
                FXCollections.observableArrayList(
                        "Todas",
                        "Mensaje",
                        "Anuncio",
                        "Alerta",
                        "Recordatorio",
                        "Sistema",
                        "Reserva",
                        "Deuda"
                )
        );

        cmbEstado.setItems(
                FXCollections.observableArrayList(
                        "Todos",
                        "Pendiente",
                        "Enviada",
                        "Leída",
                        "Fallida",
                        "Eliminada"
                )
        );

        cmbTipo.getSelectionModel().selectFirst();
        cmbEstado.getSelectionModel().selectFirst();
    }

    private void cargarUsuarioActual() {
        usuarioActual = SesionUsuario
                .obtenerInstancia()
                .getUsuarioActual();

        if (usuarioActual == null) {
            lblUsuario.setText("No existe un usuario autenticado.");

            mostrarMensaje(
                    "Debe iniciar sesión para consultar sus notificaciones.",
                    "message-error"
            );

            deshabilitarControles();
            return;
        }

        String nombreCompleto =
                textoSeguro(usuarioActual.getNombre())
                        + " "
                        + textoSeguro(usuarioActual.getApellido());

        lblUsuario.setText(
                "Notificaciones de: "
                        + nombreCompleto.trim()
        );
    }

    @FXML
    private void aplicarFiltros() {
        cargarNotificaciones();

        mostrarMensaje(
                "Filtros aplicados.",
                "message-info"
        );
    }

    @FXML
    private void limpiarFiltros() {
        cmbTipo.getSelectionModel().selectFirst();
        cmbEstado.getSelectionModel().selectFirst();
        txtBusqueda.clear();

        cargarNotificaciones();

        mostrarMensaje(
                "Filtros restablecidos.",
                "message-info"
        );
    }

    @FXML
    private void actualizarNotificaciones() {
        cargarNotificaciones();

        mostrarMensaje(
                "Notificaciones actualizadas.",
                "message-success"
        );
    }

    @FXML
    private void seleccionarNotificacion() {
        NotificacionDTO seleccionada =
                tblNotificaciones
                        .getSelectionModel()
                        .getSelectedItem();

        boolean existeSeleccion =
                seleccionada != null;

        btnMarcarLeida.setDisable(!existeSeleccion);
        btnEliminar.setDisable(!existeSeleccion);

        if (seleccionada != null) {
            mostrarMensaje(
                    "Seleccionada: "
                            + seleccionada.asunto(),
                    "message-info"
            );
        }
    }

    @FXML
    private void marcarLeida() {
        NotificacionDTO notificacion =
                obtenerSeleccionada();

        if (notificacion == null
                || usuarioActual == null) {
            return;
        }

        try {
            service.marcarNotificacionLeidaPorUsuario(
                    notificacion.id(),
                    usuarioActual.getIdUsuario()
            );

            cargarNotificaciones();

            mostrarMensaje(
                    "Notificación marcada como leída.",
                    "message-success"
            );

        } catch (ComunicacionException exception) {
            mostrarMensaje(
                    exception.getMessage(),
                    "message-error"
            );
        }
    }

    @FXML
    private void eliminar() {
        NotificacionDTO notificacion =
                obtenerSeleccionada();

        if (notificacion == null
                || usuarioActual == null) {
            return;
        }

        try {
            service.eliminarNotificacionPorUsuario(
                    notificacion.id(),
                    usuarioActual.getIdUsuario()
            );

            cargarNotificaciones();

            mostrarMensaje(
                    "Notificación eliminada.",
                    "message-success"
            );

        } catch (ComunicacionException exception) {
            mostrarMensaje(
                    exception.getMessage(),
                    "message-error"
            );
        }
    }

    private void cargarNotificaciones() {
        if (usuarioActual == null) {
            return;
        }

        try {
            datos.setAll(
                    service.buscarNotificacionesPorUsuario(
                            usuarioActual.getIdUsuario(),
                            cmbTipo.getValue(),
                            cmbEstado.getValue(),
                            txtBusqueda.getText()
                    )
            );

            actualizarResumen();

            tblNotificaciones
                    .getSelectionModel()
                    .clearSelection();

            btnMarcarLeida.setDisable(true);
            btnEliminar.setDisable(true);

        } catch (ComunicacionException exception) {
            datos.clear();
            actualizarResumen();

            mostrarMensaje(
                    exception.getMessage(),
                    "message-error"
            );
        }
    }

    private NotificacionDTO obtenerSeleccionada() {
        NotificacionDTO seleccionada =
                tblNotificaciones
                        .getSelectionModel()
                        .getSelectedItem();

        if (seleccionada == null) {
            mostrarMensaje(
                    "Seleccione una notificación.",
                    "message-error"
            );
        }

        return seleccionada;
    }

    private void actualizarResumen() {
        long pendientes = datos.stream()
                .filter(
                        dato -> "Pendiente".equalsIgnoreCase(
                                dato.estado()
                        )
                )
                .count();

        long leidas = datos.stream()
                .filter(
                        dato -> "Leída".equalsIgnoreCase(
                                dato.estado()
                        )
                )
                .count();

        lblResumen.setText(
                datos.size()
                        + " total · "
                        + pendientes
                        + " pendiente(s) · "
                        + leidas
                        + " leída(s)"
        );
    }

    private void deshabilitarControles() {
        cmbTipo.setDisable(true);
        cmbEstado.setDisable(true);
        txtBusqueda.setDisable(true);
        tblNotificaciones.setDisable(true);
        btnMarcarLeida.setDisable(true);
        btnEliminar.setDisable(true);
    }

    private void mostrarMensaje(
            String texto,
            String claseCss
    ) {
        lblMensaje.setText(
                texto == null ? "" : texto
        );

        lblMensaje.getStyleClass().setAll(
                "message-label",
                claseCss
        );
    }

    private String textoSeguro(String texto) {
        return texto == null ? "" : texto;
    }
}