package fis.dsw.sgc.finanzas.dao;

import fis.dsw.sgc.conexion_bd.DBConnection;
import fis.dsw.sgc.finanzas.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeudaDAOImpl implements IDeudaDAO {

    @Override
    public void guardar(Deuda deuda) {
        String sql = "INSERT INTO deuda (id_usuario, id_inmueble, id_tipo_deuda, id_reserva, descripcion, " +
                "valor_base_centavos, mora_centavos, total_centavos, saldo_centavos, fecha_emision, fecha_vencimiento, estado, observaciones) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, deuda.getIdUsuario());

            // Si el inmueble es nulo, mandamos NULL a la BD
            if (deuda.getIdInmueble() != null) pstmt.setInt(2, deuda.getIdInmueble());
            else pstmt.setNull(2, Types.INTEGER);

            // Obtenemos el ID del tipo de deuda consultando la tabla de catálogo
            pstmt.setInt(3, obtenerIdTipoDeuda(conn, deuda.getTipoDeuda().getMotivo()));

            pstmt.setNull(4, Types.INTEGER); // Para reservas, ajustar cuando conectes ese módulo
            pstmt.setString(5, deuda.getDescripcion());

            // MAGIA: Conversión de double (Modelo) a INTEGER en centavos (Base de Datos)
            pstmt.setInt(6, (int) Math.round(deuda.getValorBase() * 100));
            pstmt.setInt(7, 0); // Mora inicial es 0
            pstmt.setInt(8, (int) Math.round(deuda.getSaldo() * 100)); // Total
            pstmt.setInt(9, (int) Math.round(deuda.getSaldo() * 100)); // Saldo

            pstmt.setString(10, LocalDate.now().toString());
            pstmt.setString(11, deuda.getFechaVencimiento().toString());

            // MAGIA: El estado en formato BD (ej: "EN_MORA", "ANULADA")
            pstmt.setString(12, deuda.getEstado().getNombreEstadoBD());
            pstmt.setString(13, "Generada por el módulo de Finanzas");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la deuda en la base de datos.", e);
        }
    }

    @Override
    public void actualizar(Deuda deuda) {
        String sql = "UPDATE deuda SET saldo_centavos = ?, mora_centavos = ?, total_centavos = ?, " +
                "fecha_vencimiento = ?, estado = ? WHERE id_deuda = ?";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Convertimos nuevamente a centavos para actualizar pagos o mora
            pstmt.setInt(1, (int) Math.round(deuda.getSaldo() * 100));
            pstmt.setInt(2, (int) Math.round((deuda.getValorBase() * 0.15) * 100)); // Mora (si aplica)
            pstmt.setInt(3, (int) Math.round((deuda.getValorBase() + (deuda.getValorBase() * 0.15)) * 100)); // Total
            pstmt.setString(4, deuda.getFechaVencimiento().toString());
            pstmt.setString(5, deuda.getEstado().getNombreEstadoBD());
            pstmt.setInt(6, deuda.getIdDeuda());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la deuda en la base de datos.", e);
        }
    }

    @Override
    public Deuda buscarPorId(int idDeuda) {
        String sql = "SELECT d.*, td.codigo as tipo_motivo FROM deuda d " +
                "JOIN tipo_deuda td ON d.id_tipo_deuda = td.id_tipo_deuda WHERE d.id_deuda = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDeuda);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return mapearDeuda(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la deuda.", e);
        }
        return null;
    }

    @Override
    public List<Deuda> buscarDeudasActivasPorUsuario(int idUsuario) {
        List<Deuda> deudas = new ArrayList<>();
        String sql = "SELECT d.*, td.codigo as tipo_motivo FROM deuda d " +
                "JOIN tipo_deuda td ON d.id_tipo_deuda = td.id_tipo_deuda " +
                "WHERE d.id_usuario = ? AND d.estado IN ('PENDIENTE', 'EN_PROCESO', 'EN_MORA')";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                deudas.add(mapearDeuda(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar deudas por usuario.", e);
        }
        return deudas;
    }

    @Override
    public boolean verificarDeudasEnMoraPorUsuario(int idUsuario) {
        String sql = "SELECT COUNT(id_deuda) FROM deuda WHERE id_usuario = ? AND estado = 'EN_MORA'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar mora.", e);
        }
        return false;
    }

    // --- MÉTODOS PRIVADOS DE MAPEO ---

    private int obtenerIdTipoDeuda(Connection conn, String motivo) throws SQLException {
        String sql = "SELECT id_tipo_deuda FROM tipo_deuda WHERE codigo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, motivo.toUpperCase());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("id_tipo_deuda");
            throw new SQLException("El tipo de deuda '" + motivo + "' no existe en el catálogo.");
        }
    }

    private Deuda mapearDeuda(ResultSet rs) throws SQLException {
        Deuda deuda = new Deuda();
        deuda.setIdDeuda(rs.getInt("id_deuda"));
        deuda.setIdUsuario(rs.getInt("id_usuario"));
        deuda.setDescripcion(rs.getString("descripcion"));

        // Convertimos de centavos enteros a double para tu modelo rico
        deuda.setValorBase(rs.getInt("valor_base_centavos") / 100.0);
        deuda.setSaldo(rs.getInt("saldo_centavos") / 100.0);

        deuda.setFechaVencimiento(LocalDate.parse(rs.getString("fecha_vencimiento")));

        // Reconstruimos el Strategy
        String motivo = rs.getString("tipo_motivo");
        if ("ALICUOTA".equals(motivo)) deuda.setTipoDeuda(new DeudaAlicuota(0, 0)); // El cálculo ya se hizo al crearla
        else if ("MULTA".equals(motivo)) deuda.setTipoDeuda(new DeudaMulta());
        else if ("RESERVA".equals(motivo)) deuda.setTipoDeuda(new DeudaReserva());

        // Reconstruimos el State
        String estado = rs.getString("estado");
        switch (estado) {
            case "PENDIENTE": deuda.setEstado(new EstadoPendiente()); break;
            case "EN_PROCESO": deuda.setEstado(new EstadoEnProceso()); break;
            case "PAGADA": deuda.setEstado(new EstadoPagada()); break;
            case "EN_MORA": deuda.setEstado(new EstadoMora()); break;
            case "ANULADA": deuda.setEstado(new EstadoEliminada()); break;
        }

        return deuda;
    }
}