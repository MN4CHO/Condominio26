package fis.dsw.sgc.finanzas.dao;

import fis.dsw.sgc.conexion_bd.DBConnection;
import fis.dsw.sgc.finanzas.model.Gasto;
import java.sql.*;

public class GastoDAOImpl implements IGastoDAO {

    @Override
    public void guardar(Gasto gasto, int idCondominio, int idUsuarioRegistra) {
        String sql = "INSERT INTO gasto (id_condominio, id_usuario_registra, tipo_gasto, descripcion, " +
                "valor_centavos, fecha_gasto, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCondominio);
            pstmt.setInt(2, idUsuarioRegistra);
            pstmt.setString(3, gasto.getTipoGasto());
            pstmt.setString(4, gasto.getDescripcion());

            // Conversión de double a INTEGER en centavos
            pstmt.setInt(5, (int) Math.round(gasto.getValor() * 100));
            pstmt.setString(6, gasto.getFechaGasto().toString());
            pstmt.setString(7, "REGISTRADO");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el gasto en la base de datos.", e);
        }
    }
}