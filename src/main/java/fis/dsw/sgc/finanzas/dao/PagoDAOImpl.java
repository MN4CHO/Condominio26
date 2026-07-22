package fis.dsw.sgc.finanzas.dao;

import fis.dsw.sgc.conexion_bd.DBConnection;
import fis.dsw.sgc.finanzas.model.*;
import java.sql.*;

public class PagoDAOImpl implements IPagoDAO {

    @Override
    public void guardar(Pago pago) {
        String sql = "INSERT INTO pago (id_deuda, id_metodo_pago, valor_pagado_centavos, fecha_pago, " +
                "numero_transaccion, comprobante, estado, observaciones) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pago.getDeuda().getIdDeuda());

            // 1. Identificar el tipo de estrategia para mapearlo a la BD
            String codigoMetodoBD;
            String transaccion = null;
            String comprobante = null;

            if (pago.getModalidad() instanceof PagoEfectivo) {
                codigoMetodoBD = "EFECTIVO";
            } else if (pago.getModalidad() instanceof PagoTransferencia) {
                codigoMetodoBD = "TRANSFERENCIA";
                comprobante = ((PagoTransferencia) pago.getModalidad()).getComprobanteTransferencia();
            } else if (pago.getModalidad() instanceof PagoTarjeta) {
                codigoMetodoBD = "TARJETA";
                transaccion = ((PagoTarjeta) pago.getModalidad()).getCodigoTransaccion();
            } else {
                throw new IllegalArgumentException("Modalidad de pago no reconocida.");
            }

            pstmt.setInt(2, obtenerIdMetodoPago(conn, codigoMetodoBD));

            // 2. Conversión a centavos
            pstmt.setInt(3, (int) Math.round(pago.getValorPagado() * 100));
            pstmt.setString(4, pago.getFechaPago().toString());

            // 3. Manejo de nulos para campos opcionales
            if (transaccion != null) pstmt.setString(5, transaccion);
            else pstmt.setNull(5, Types.VARCHAR);

            if (comprobante != null) pstmt.setString(6, comprobante);
            else pstmt.setNull(6, Types.VARCHAR);

            pstmt.setString(7, pago.getEstado());
            pstmt.setString(8, "Pago gestionado desde módulo de Finanzas");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el pago en la base de datos.", e);
        }
    }

    private int obtenerIdMetodoPago(Connection conn, String codigo) throws SQLException {
        String sql = "SELECT id_metodo_pago FROM metodo_pago WHERE codigo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_metodo_pago");
            }
            throw new SQLException("El método de pago '" + codigo + "' no existe en el catálogo.");
        }
    }
}