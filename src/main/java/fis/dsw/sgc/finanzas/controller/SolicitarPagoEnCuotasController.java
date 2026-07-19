package fis.dsw.sgc.finanzas.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

// Controlador de la vista Solicitar pago en cuotas (caso de uso solicitarPagoEnCuotas, GRA)
public class SolicitarPagoEnCuotasController {

    private static final Pattern MESES_VALIDO = Pattern.compile("\\d+");
    private static final Pattern CEDULA_VALIDA = Pattern.compile("\\d{10}");
    private static final int MESES_MINIMOS = 3;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    // Simulación mientras no exista el Service/DAO de deudas
    private static final double VALOR_DEUDA_SIMULADA = 90.00;
    private static final String CEDULA_CON_DEUDAS_EN_MORA = "1111111111";
    private static final String CEDULA_CON_DEUDAS_PENDIENTES = "2222222222";
    private static final String ID_DEUDA_INEXISTENTE = "DEU-404";

    @FXML private TextField txtCedula;
    @FXML private Button btnConsultarDeudas;
    @FXML private TextField txtIdDeuda;
    @FXML private TextField txtNumeroMeses;
    @FXML private Button btnCalcular;
    @FXML private Button btnLimpiar;
    @FXML private Button btnSolicitar;
    @FXML private Label lblMensaje;

    @FXML private TableView<CuotaFila> tablaCuotas;
    @FXML private TableColumn<CuotaFila, String> colCuota;
    @FXML private TableColumn<CuotaFila, String> colFechaMax;
    @FXML private TableColumn<CuotaFila, String> colValor;

    private final ObservableList<CuotaFila> cuotas = FXCollections.observableArrayList();
    private Label placeholderTabla;

    @FXML
    public void initialize() {
        colCuota.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCuota()));
        colFechaMax.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFechaMaximaPago()));
        colValor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor()));
        tablaCuotas.setItems(cuotas);
        tablaCuotas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        placeholderTabla = new Label("Aún no se ha solicitado el diferimiento de una deuda.");
        placeholderTabla.getStyleClass().add("module-subtitle");
        placeholderTabla.setWrapText(true);
        placeholderTabla.setAlignment(Pos.CENTER);
        placeholderTabla.setMaxWidth(420);
        tablaCuotas.setPlaceholder(placeholderTabla);

        btnSolicitar.setDisable(true);
        bloquearDatosDeuda();
        setMensaje("Ingrese su número de cédula y pulse \"Consultar deudas\".", "message-info");
    }

    @FXML
    void consultarDeudas(ActionEvent event) {
        cuotas.clear();
        btnSolicitar.setDisable(true);
        bloquearDatosDeuda();
        String cedula = texto(txtCedula);

        if (cedula.isEmpty()) {
            setMensaje("Debe ingresar el número de cédula del residente.", "message-error");
            return;
        }
        if (!CEDULA_VALIDA.matcher(cedula).matches()) {
            setMensaje("Cédula inválida. Ingrese 10 dígitos.", "message-error");
            return;
        }

        // Caso de uso compartido consultarDeuda: el Sistema consulta las deudas del residente
        // TODO: reemplazar por servicioFinanzas.consultarDeudasPendientes(residente) cuando exista el Service/DAO
        // Solo las deudas en estado MORA bloquean el diferimiento (escenario alternativo 2)
        if (cedula.equals(CEDULA_CON_DEUDAS_EN_MORA)) {
            setMensaje("Tiene deudas pendientes de pago, no puede ser beneficiario para diferir una deuda hasta que pague todos sus valores",
                    "message-error");
            return;
        }

        desbloquearDatosDeuda();
        if (cedula.equals(CEDULA_CON_DEUDAS_PENDIENTES)) {
            setMensaje("El residente tiene deudas pendientes, pero ninguna en mora. Ingrese el ID de la deuda y el número de meses a diferirla.",
                    "message-success");
        } else {
            setMensaje("El residente no tiene deudas pendientes. Ingrese el ID de la deuda y el número de meses a diferirla.",
                    "message-success");
        }
    }

    @FXML
    void calcular(ActionEvent event) {
        cuotas.clear();
        btnSolicitar.setDisable(true);
        String idDeuda = texto(txtIdDeuda);
        String meses = texto(txtNumeroMeses);

        if (idDeuda.isEmpty()) {
            setMensaje("Debe ingresar el ID de la deuda que desea diferir.", "message-error");
            return;
        }
        if (idDeuda.equalsIgnoreCase(ID_DEUDA_INEXISTENTE)) {
            setMensaje("No existe una deuda con el identificador proporcionado.", "message-error");
            return;
        }
        if (meses.isEmpty() || !MESES_VALIDO.matcher(meses).matches()) {
            setMensaje("El número de meses ingresado no es válido, ingrese un número entero.", "message-error");
            return;
        }

        int numeroMeses = Integer.parseInt(meses);
        if (numeroMeses <= 2) {
            setMensaje("El numero de meses a diferir la deuda debe ser de almenos 3", "message-error");
            return;
        }

        double valorCuota = VALOR_DEUDA_SIMULADA / numeroMeses;
        LocalDate hoy = LocalDate.now();
        for (int i = 1; i <= numeroMeses; i++) {
            cuotas.add(new CuotaFila(
                    "Cuota " + i + "/" + numeroMeses,
                    hoy.plusMonths(i).format(FMT),
                    String.format(Locale.US, "$%.2f", valorCuota)
            ));
        }

        btnSolicitar.setDisable(false);
        setMensaje("", "message-info");
    }

    @FXML
    void solicitar(ActionEvent event) {
        setMensaje("Deuda diferida exitosamente", "message-success");
    }

    @FXML
    void limpiar(ActionEvent event) {
        txtCedula.clear();
        txtIdDeuda.clear();
        txtNumeroMeses.clear();
        cuotas.clear();
        bloquearDatosDeuda();
        btnSolicitar.setDisable(true);
        setMensaje("Ingrese su número de cédula y pulse \"Consultar deudas\".", "message-info");
    }

    private void bloquearDatosDeuda() {
        txtIdDeuda.clear();
        txtNumeroMeses.clear();
        txtIdDeuda.setDisable(true);
        txtNumeroMeses.setDisable(true);
        btnCalcular.setDisable(true);
    }

    private void desbloquearDatosDeuda() {
        txtIdDeuda.setDisable(false);
        txtNumeroMeses.setDisable(false);
        btnCalcular.setDisable(false);
    }

    private String texto(TextField campo) {
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

    // Fila de la tabla de cuotas generadas
    public static class CuotaFila {
        private final String cuota;
        private final String fechaMaximaPago;
        private final String valor;

        public CuotaFila(String cuota, String fechaMaximaPago, String valor) {
            this.cuota = cuota;
            this.fechaMaximaPago = fechaMaximaPago;
            this.valor = valor;
        }

        public String getCuota() {
            return cuota;
        }

        public String getFechaMaximaPago() {
            return fechaMaximaPago;
        }

        public String getValor() {
            return valor;
        }
    }
}
