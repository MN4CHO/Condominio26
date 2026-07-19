package fis.dsw.sgc.conexion_bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main_test_bd {
 public static void main(String[] args) {
        // Obtener la conexión única
        Connection conn = DBConnection.getInstance().getConnection();

        // Ejemplo de consulta segura usando try-with-resources
        String sql = "SELECT * FROM usuario";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                System.out.println("Usuario: " + rs.getString("nombres"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error en la consulta: " + e.getMessage());
        }

        DBConnection.getInstance().closeConnection();
    }
}
