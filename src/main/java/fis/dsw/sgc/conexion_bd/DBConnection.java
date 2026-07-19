package fis.dsw.sgc.conexion_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   private static final String URL = "jdbc:sqlite:database/condominio.db";
   private Connection connection;

   private DBConnection() {
      try {
         this.connection = DriverManager.getConnection(URL);
         System.out.println("Conexión exitosa a SQLite");
      } catch (SQLException e) {
         System.out.println("Error al conectar a SQLite");
      }
   }

   private static class SingletonHolder {
      private static final DBConnection INSTANCE = new DBConnection();
   }

   public static DBConnection getInstance() {
      return SingletonHolder.INSTANCE;
   }

   public Connection getConnection() {
      try {
         if (this.connection == null || this.connection.isClosed()) {
            this.connection = DriverManager.getConnection(URL);
         }
      } catch (SQLException e) {
         System.out.println("Error al reabrir la conexión: " + e.getMessage());
      }
      return this.connection;
   }

   public void closeConnection() {
      if (connection != null) {
         try {
            connection.close();
            System.out.println("Conexión a SQLite cerrada.");
         } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
         }
      }
   }
}
